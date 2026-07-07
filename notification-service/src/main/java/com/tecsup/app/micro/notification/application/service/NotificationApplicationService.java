package com.tecsup.app.micro.notification.application.service;
import com.tecsup.app.micro.notification.application.usecase.GetAllNotificationsUseCase;
import com.tecsup.app.micro.notification.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @RequiredArgsConstructor
public class NotificationApplicationService {
    private final GetAllNotificationsUseCase getAllNotificationsUseCase;
    @Transactional(readOnly=true) public List<Notification> getAll() { return getAllNotificationsUseCase.execute(); }
}
