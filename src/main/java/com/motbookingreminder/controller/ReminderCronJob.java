package com.motbookingreminder.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.motbookingreminder.utilities.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class ReminderCronJob {

    @Value("${email}")
    private String senderMail;

    @Value("${app.url}")
    private String appUrl;

    @Autowired
    private EmailService emailService;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Parse a date string that may be ISO-8601 ("2026-04-15T...") or plain "yyyy-MM-dd". */
    private LocalDate parseDate(Object raw) {
        if (raw == null) return null;
        String s = raw.toString();
        if (s.contains("T")) s = s.substring(0, 10);
        try {
            return LocalDate.parse(s, DATE_FMT);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Runs at 02:00 every day.
     * Sends MOT and tax reminder emails for any vehicle whose reminder date is today.
     * Supports both the legacy single-vehicle structure and the current multi-vehicle structure.
     * Emails include a link back to the app to record the booked MOT date.
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void sendScheduledReminders() {
        Firestore db = FirestoreClient.getFirestore();
        LocalDate today = LocalDate.now();

        try {
            List<QueryDocumentSnapshot> documents =
                    db.collection("reminders").get().get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                String email = document.getString("email");
                if (email == null) continue;

                // ── Legacy single-vehicle structure ──────────────────────────
                String legacyReminderDate = document.getString("reminderDate");
                if (legacyReminderDate != null) {
                    LocalDate rd = parseDate(legacyReminderDate);
                    if (rd != null && rd.isEqual(today)) {
                        String regNumber   = document.getString("regNumber");
                        String motExpiry   = document.getString("motExpiryDate");
                        emailService.sendEmail(senderMail, email,
                                "MOT Expiry Reminder – " + regNumber,
                                buildMotReminderBody(regNumber, motExpiry, legacyReminderDate));
                    }
                }

                // ── Multi-vehicle structure ───────────────────────────────────
                @SuppressWarnings("unchecked")
                Map<String, Object> vehicles = (Map<String, Object>) document.get("vehicles");
                if (vehicles == null) continue;

                for (Map.Entry<String, Object> entry : vehicles.entrySet()) {
                    String regNumber = entry.getKey();
                    @SuppressWarnings("unchecked")
                    Map<String, Object> v = (Map<String, Object>) entry.getValue();
                    if (v == null) continue;

                    // MOT reminder
                    LocalDate motReminder = parseDate(v.get("motReminderDate"));
                    if (motReminder != null && motReminder.isEqual(today)) {
                        String motExpiry = v.get("motExpiryDate") != null
                                ? parseDate(v.get("motExpiryDate")).format(DATE_FMT) : "unknown";
                        emailService.sendEmail(senderMail, email,
                                "MOT Expiry Reminder – " + regNumber,
                                buildMotReminderBody(regNumber, motExpiry, today.format(DATE_FMT)));
                    }

                    // Tax reminder
                    LocalDate taxReminder = parseDate(v.get("taxReminderDate"));
                    if (taxReminder != null && taxReminder.isEqual(today)) {
                        String taxDue = v.get("taxDueDate") != null
                                ? parseDate(v.get("taxDueDate")).format(DATE_FMT) : "unknown";
                        emailService.sendEmail(senderMail, email,
                                "Vehicle Tax Reminder – " + regNumber,
                                buildTaxReminderBody(regNumber, taxDue, today.format(DATE_FMT)));
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /**
     * Runs at 02:30 every day.
     * Checks whether any booked MOT date has passed.
     * If so, sends a follow-up email prompting the user to either set a reminder
     * for next year (if the MOT passed) or book a new date (if it failed).
     * Clears motBookingDate after sending so the email is only sent once.
     */
    @Scheduled(cron = "0 30 2 * * ?")
    public void checkPassedMotBookings() {
        Firestore db = FirestoreClient.getFirestore();
        LocalDate today = LocalDate.now();

        try {
            List<QueryDocumentSnapshot> documents =
                    db.collection("reminders").get().get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                String email = document.getString("email");
                if (email == null) continue;

                @SuppressWarnings("unchecked")
                Map<String, Object> vehicles = (Map<String, Object>) document.get("vehicles");
                if (vehicles == null) continue;

                for (Map.Entry<String, Object> entry : vehicles.entrySet()) {
                    String regNumber = entry.getKey();
                    @SuppressWarnings("unchecked")
                    Map<String, Object> v = (Map<String, Object>) entry.getValue();
                    if (v == null) continue;

                    LocalDate bookingDate = parseDate(v.get("motBookingDate"));
                    if (bookingDate == null || !bookingDate.isBefore(today)) continue;

                    // Booking date has passed — send the follow-up email
                    emailService.sendEmail(senderMail, email,
                            "How did your MOT go? – " + regNumber,
                            buildMotFollowUpBody(regNumber, bookingDate.format(DATE_FMT)));

                    // Clear the booking date so the email is only sent once
                    db.collection("reminders")
                            .document(document.getId())
                            .update("vehicles." + regNumber + ".motBookingDate", FieldValue.delete());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    // ── Email body builders ───────────────────────────────────────────────────

    private String buildMotReminderBody(String regNumber, String motExpiry, String reminderDate) {
        return "This is a reminder that the MOT for " + regNumber + " expires on " + motExpiry + ".\n"
             + "Your reminder date is today, " + reminderDate + ".\n\n"
             + "Please book your MOT at the nearest testing centre.\n\n"
             + "Once you have booked, visit the link below to record your booking date.\n"
             + "We will then send you a follow-up reminder after your appointment to help you\n"
             + "set next year\'s reminder or rebook if needed.\n\n"
             + "Record your booked MOT date: " + appUrl + "/setdate\n\n"
             + "Thank you for using MOT Booking Reminder.\n"
             + appUrl;
    }

    private String buildTaxReminderBody(String regNumber, String taxDueDate, String reminderDate) {
        return "This is a reminder that the vehicle tax for " + regNumber + " is due on " + taxDueDate + ".\n"
             + "Your reminder date is today, " + reminderDate + ".\n\n"
             + "Please renew your vehicle tax at https://www.gov.uk/renew-vehicle-tax\n\n"
             + "Thank you for using MOT Booking Reminder.\n"
             + appUrl;
    }

    private String buildMotFollowUpBody(String regNumber, String bookingDate) {
        return "Your MOT for " + regNumber + " was booked for " + bookingDate + ". We hope it went well!\n\n"
             + "If your vehicle passed its MOT:\n"
             + "  Visit " + appUrl + " to set a reminder for next year.\n\n"
             + "If you need to rebook:\n"
             + "  Visit " + appUrl + "/setdate to update your booking date and try again.\n\n"
             + "Thank you for using MOT Booking Reminder.\n"
             + appUrl;
    }
}
