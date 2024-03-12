package com.motbookingreminder.model;

import java.time.LocalDate;

public class UserReminder {
    private String userId;
    private String email;
    private LocalDate motExpiryDate;
    private LocalDate reminderDate;

    // Constructor, getters, and setters
    public UserReminder(String userId, String email, LocalDate motExpiryDate, LocalDate reminderDate) {
        this.userId = userId;
        this.email = email;
        this.motExpiryDate = motExpiryDate;
        this.reminderDate = reminderDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getMotExpiryDate() {
        return motExpiryDate;
    }

    public void setMotExpiryDate(LocalDate motExpiryDate) {
        this.motExpiryDate = motExpiryDate;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    @Override
    public String toString() {
        return "UserReminder{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", motExpiryDate=" + motExpiryDate +
                ", reminderDate=" + reminderDate +
                '}';
    }

}
