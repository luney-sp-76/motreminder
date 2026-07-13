package com.motbookingreminder.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.awscore.exception.AwsErrorDetails;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import software.amazon.awssdk.services.ses.model.SesException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    private EmailService emailService;

    @Mock
    private SesClient sesClient;

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
        // Replace the internally-built SesClient with a mock
        ReflectionTestUtils.setField(emailService, "sesClient", sesClient);
    }

    @Test
    void sendEmail_success_invokesClientSendEmail() {
        when(sesClient.sendEmail(any(SendEmailRequest.class)))
                .thenReturn(SendEmailResponse.builder().build());

        emailService.sendEmail("from@test.com", "to@test.com", "Subject", "Body");

        verify(sesClient).sendEmail(any(SendEmailRequest.class));
    }

    @Test
    void sendEmail_sesException_doesNotThrow() {
        SesException ex = (SesException) SesException.builder()
                .awsErrorDetails(AwsErrorDetails.builder()
                        .errorCode("MessageRejected")
                        .errorMessage("Email address is not verified.")
                        .build())
                .build();
        when(sesClient.sendEmail(any(SendEmailRequest.class))).thenThrow(ex);

        // EmailService catches SesException internally; must not propagate
        assertThatNoException().isThrownBy(() ->
                emailService.sendEmail("from@test.com", "to@test.com", "Subject", "Body"));
    }
}
