package com.tecsup.app.micro.notification.application.usecase;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
@Component @RequiredArgsConstructor
public class GetAllNotificationsUseCase {
    private final NotificationRepository notificationRepository;
    public List<Notification> execute() { return notificationRepository.findAll(); }
}
