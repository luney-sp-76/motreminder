package com.motbookingreminder.utilities;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;

/**
 * This class provides functionality to send emails using the Amazon Simple
 * Email Service (SES).
 */
@Service
public class EmailService {

    private final SesClient sesClient;

    /**
     * Constructs an instance of the EmailService class and initializes the SES
     * client.
     */
    public EmailService() {
        // Initialize SES client
        this.sesClient = SesClient.builder()
                .region(Region.EU_CENTRAL_1) // Specify the AWS region, e.g., Region.US_EAST_1
                .build();
    }

    /**
     * Sends an email using the specified parameters.
     *
     * @param from    The email address of the sender.
     * @param to      The email address of the recipient.
     * @param subject The subject of the email.
     * @param body    The body of the email.
     */
    public void sendEmail(String from, String to, String subject, String body) {
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .source(from)
                .destination(Destination.builder().toAddresses(to).build())
                .message(Message.builder()
                        .subject(Content.builder().data(subject).build())
                        .body(Body.builder()
                                .text(Content.builder().data(body).build())
                                .build())
                        .build())
                .build();

        try {
            sesClient.sendEmail(sendEmailRequest);
            System.out.println("Email sent successfully!");
        } catch (SesException e) {
            System.err.println("Error sending email: " + e.awsErrorDetails().errorMessage());
        }
    }
}
