package com.motbookingreminder.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class UserReminderTest {

    @Test
    void constructor_setsAllFields() {
        LocalDate motExpiry = LocalDate.of(2026, 12, 31);
        LocalDate reminderDate = LocalDate.of(2026, 9, 30);

        UserReminder reminder = new UserReminder("user123", "user@example.com", motExpiry, reminderDate);

        assertThat(reminder.getUserId()).isEqualTo("user123");
        assertThat(reminder.getEmail()).isEqualTo("user@example.com");
        assertThat(reminder.getMotExpiryDate()).isEqualTo(motExpiry);
        assertThat(reminder.getReminderDate()).isEqualTo(reminderDate);
    }

    @Test
    void setters_updateFields() {
        UserReminder reminder = new UserReminder(null, null, null, null);
        LocalDate newMotExpiry = LocalDate.of(2027, 6, 30);
        LocalDate newReminderDate = LocalDate.of(2027, 3, 30);

        reminder.setUserId("newUser");
        reminder.setEmail("new@example.com");
        reminder.setMotExpiryDate(newMotExpiry);
        reminder.setReminderDate(newReminderDate);

        assertThat(reminder.getUserId()).isEqualTo("newUser");
        assertThat(reminder.getEmail()).isEqualTo("new@example.com");
        assertThat(reminder.getMotExpiryDate()).isEqualTo(newMotExpiry);
        assertThat(reminder.getReminderDate()).isEqualTo(newReminderDate);
    }

    @Test
    void toString_containsAllFields() {
        LocalDate motExpiry = LocalDate.of(2026, 12, 31);
        LocalDate reminderDate = LocalDate.of(2026, 9, 30);

        UserReminder reminder = new UserReminder("user123", "user@example.com", motExpiry, reminderDate);
        String result = reminder.toString();

        assertThat(result).contains("user123");
        assertThat(result).contains("user@example.com");
        assertThat(result).contains("2026-12-31");
        assertThat(result).contains("2026-09-30");
    }
}
