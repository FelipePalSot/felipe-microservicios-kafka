package com.tecsup.app.micro.enrollment.infrastructure.web.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CreateEnrollmentRequest {
    @NotNull(message = "userId is required") private Long userId;
    @NotNull(message = "courseId is required") private Long courseId;
}
