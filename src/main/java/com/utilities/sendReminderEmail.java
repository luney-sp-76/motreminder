package com.utilities;

import org.springframework.beans.factory.annotation.Autowired;

@Autowired private EmailService emailService;

// Example usage
public void sendReminderEmail(){emailService.sendEmail("from@example.com", // Your verified sender email
"to@example.com", // The recipient's email address
"Reminder", // Email subject
"This is a reminder email." // Email body
);}
