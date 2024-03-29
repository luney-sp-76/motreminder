package com.motbookingreminder.controller;

import org.springframework.beans.factory.annotation.Value;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.motbookingreminder.utilities.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * This class represents a cron job for sending scheduled reminders.
 */
@Service
public class ReminderCronJob {

    @Value("${email}")
    private String senderMail;

    @Autowired
    private EmailService emailService;

    /**
     * This method is scheduled to run at 02:00 every day and sends reminders for
     * MOT expiry.
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void sendScheduledReminders() {
        Firestore db = FirestoreClient.getFirestore();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ApiFuture<QuerySnapshot> future = db.collection("reminders").get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                String reminderDate = document.getString("reminderDate");
                if (reminderDate != null && LocalDate.parse(reminderDate, formatter).isEqual(today)) {
                    String email = document.getString("email");
                    String regNumber = document.getString("regNumber");
                    String motExpiryDate = document.getString("motExpiryDate");
                    // Construct the email body
                    String emailBody = String.format("This is a reminder that the MOT for %s expires on %s.", regNumber,
                            motExpiryDate);
                    // Send the email
                    emailService.sendEmail(senderMail, email, "MOT Expiry Reminder", emailBody);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
