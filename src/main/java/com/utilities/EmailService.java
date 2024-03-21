package com.utilities;

import org.springframework.stereotype.Service;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.model.Destination;

@Service
public class EmailService {

    private final SesClient sesClient;

    public EmailService() {
        // Initialize SES client
        this.sesClient = SesClient.builder()
                .region(Region.AWS_REGION) // Specify the AWS region, e.g., Region.US_EAST_1
                .build();
    }

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
