package org.example.Services;

import org.example.DTO.Request.EmailNotificationRequest;
import org.example.DTO.Respone.EmailNotificationResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface EmailNotificationService {
    EmailNotificationResponse notifyBy(EmailNotificationRequest notificationRequest) throws IOException; 
}
