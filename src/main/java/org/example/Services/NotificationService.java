package org.example.Services;

import org.example.DTO.Request.EmailNotificationRequest;
import org.example.DTO.Respone.EmailNotificationResponse;

import java.io.IOException;

public interface NotificationService {
    EmailNotificationResponse notifyBY(EmailNotificationRequest notificationRequest) throws IOException;
}
