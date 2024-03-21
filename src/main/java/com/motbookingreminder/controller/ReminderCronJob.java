package com.motbookingreminder.controller;

import org.springframework.scheduling.annotation.Scheduled;

public class ReminderCronJob {

    @Scheduled(cron = "0 0 2 * * ?") // 2 AM every day
    public void checkAndSendReminders() {
        // Logic to check Firebase for reminders due today

        // Logic to send emails to users
    }

}
