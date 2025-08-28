package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTO.Request.EmailNotificationRequest;
import org.example.DTO.Respone.EmailNotificationResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final EmailNotificationService emailNotificationService;

    @Override
    public EmailNotificationResponse notifyBY(EmailNotificationRequest notificationRequest) throws IOException {
        return emailNotificationService.notifyBy(notificationRequest);
    }
}
