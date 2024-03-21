package com.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

    private final EmailService emailService;

    @Autowired
    public ReminderService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendReminderEmail() {
        // Example usage of the emailService
        emailService.sendEmail(
                "from@example.com", // Your verified sender email
                "to@example.com", // The recipient's email address
                "Reminder", // Email subject
                "This is a reminder email." // Email body
        );
    }
}
