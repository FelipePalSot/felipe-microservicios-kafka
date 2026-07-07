package com.tecsup.app.micro.enrollment.infrastructure.web.mapper;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.EnrollmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface EnrollmentDtoMapper {
    @Mapping(target = "status", expression = "java(enrollment.getStatus().name())")
    EnrollmentResponse toResponse(Enrollment enrollment);
}
