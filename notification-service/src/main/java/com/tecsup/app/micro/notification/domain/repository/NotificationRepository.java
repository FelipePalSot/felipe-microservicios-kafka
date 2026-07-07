package com.tecsup.app.micro.notification.domain.repository;
import com.tecsup.app.micro.notification.domain.model.Notification;
import java.util.List;
public interface NotificationRepository {
    List<Notification> findAll();
    Notification save(Notification notification);
}
