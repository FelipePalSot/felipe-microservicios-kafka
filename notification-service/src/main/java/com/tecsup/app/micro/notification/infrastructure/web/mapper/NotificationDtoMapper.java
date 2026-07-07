package com.tecsup.app.micro.notification.infrastructure.web.mapper;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.infrastructure.web.dto.NotificationResponse;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface NotificationDtoMapper {
    NotificationResponse toResponse(Notification notification);
}
