package com.motbookingreminder.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReminderServiceTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ReminderService reminderService;

    @Test
    void sendReminderEmail_delegatesToEmailServiceWithFixedParameters() {
        reminderService.sendReminderEmail();

        verify(emailService).sendEmail(
                "from@example.com",
                "to@example.com",
                "Reminder",
                "This is a reminder email."
        );
    }
}
