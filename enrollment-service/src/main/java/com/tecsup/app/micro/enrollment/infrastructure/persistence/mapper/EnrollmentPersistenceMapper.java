package com.tecsup.app.micro.enrollment.infrastructure.persistence.mapper;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface EnrollmentPersistenceMapper {
    Enrollment toDomain(EnrollmentEntity entity);
    EnrollmentEntity toEntity(Enrollment domain);
}
