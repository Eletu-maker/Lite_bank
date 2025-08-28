package org.example.Services;

import org.assertj.core.api.AssertionsForClassTypes;
import org.example.DTO.Request.EmailNotificationRequest;
import org.example.DTO.Respone.EmailNotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NotificationServiceImplTest {
    @Autowired
    private NotificationService notificationService;

    @Test
    void testSendEmailNotification() throws IOException{
        EmailNotificationRequest notificationRequest = new EmailNotificationRequest();
        notificationRequest.setRecipient("becaw65821@chaublog.com");
        notificationRequest.setSubject("Test Subject");
        notificationRequest.setMailBody("Test Body");

        EmailNotificationResponse response = notificationService.notifyBY(notificationRequest);

        assertThat(response).isNotNull();
        AssertionsForClassTypes.assertThat(response.isStatus()).isTrue();
    }


}