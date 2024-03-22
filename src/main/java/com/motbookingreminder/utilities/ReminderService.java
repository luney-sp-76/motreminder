package com.motbookingreminder.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class represents a service for sending reminder emails.
 */
@Service
public class ReminderService {

    private final EmailService emailService;

    /**
     * Constructs a new ReminderService with the specified EmailService.
     *
     * @param emailService the EmailService to use for sending emails
     */
    @Autowired
    public ReminderService(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Sends a reminder email using the configured EmailService.
     * Example usage of the emailService.
     */
    public void sendReminderEmail() {
        emailService.sendEmail(
                "from@example.com", // Your verified sender email
                "to@example.com", // The recipient's email address
                "Reminder", // Email subject
                "This is a reminder email." // Email body
        );
    }
}
