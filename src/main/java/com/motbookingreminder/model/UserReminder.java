package com.motbookingreminder.model;

import java.time.LocalDate;

/**
 * Represents a user reminder for MOT (Ministry of Transport) expiry date.
 */
public class UserReminder {
    private String userId;
    private String email;
    private LocalDate motExpiryDate;
    private LocalDate reminderDate;

    // Constructor, getters, and setters

    /**
     * Constructs a new UserReminder object with the specified user ID, email, MOT
     * expiry date, and reminder date.
     *
     * @param userId        the user ID
     * @param email         the user's email address
     * @param motExpiryDate the MOT expiry date
     * @param reminderDate  the reminder date
     */
    public UserReminder(String userId, String email, LocalDate motExpiryDate, LocalDate reminderDate) {
        this.userId = userId;
        this.email = email;
        this.motExpiryDate = motExpiryDate;
        this.reminderDate = reminderDate;
    }

    /**
     * Returns the user ID.
     *
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the user's email address.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the MOT expiry date.
     *
     * @return the MOT expiry date
     */
    public LocalDate getMotExpiryDate() {
        return motExpiryDate;
    }

    /**
     * Sets the MOT expiry date.
     *
     * @param motExpiryDate the MOT expiry date to set
     */
    public void setMotExpiryDate(LocalDate motExpiryDate) {
        this.motExpiryDate = motExpiryDate;
    }

    /**
     * Returns the reminder date.
     *
     * @return the reminder date
     */
    public LocalDate getReminderDate() {
        return reminderDate;
    }

    /**
     * Sets the reminder date.
     *
     * @param reminderDate the reminder date to set
     */
    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    /**
     * Returns a string representation of the UserReminder object.
     *
     * @return a string representation of the UserReminder object
     */
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
