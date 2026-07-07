package com.tecsup.app.micro.notification.infrastructure.persistence.mapper;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.infrastructure.persistence.entity.NotificationEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface NotificationPersistenceMapper {
    Notification toDomain(NotificationEntity entity);
    NotificationEntity toEntity(Notification domain);
}
