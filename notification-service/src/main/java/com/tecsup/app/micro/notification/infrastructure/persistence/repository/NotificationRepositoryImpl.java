package com.tecsup.app.micro.notification.infrastructure.persistence.repository;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import com.tecsup.app.micro.notification.infrastructure.persistence.mapper.NotificationPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository @RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {
    private final JpaNotificationRepository jpa;
    private final NotificationPersistenceMapper mapper;
    @Override public List<Notification> findAll() { return jpa.findAll().stream().map(mapper::toDomain).toList(); }
    @Override public Notification save(Notification n) { return mapper.toDomain(jpa.save(mapper.toEntity(n))); }
}
