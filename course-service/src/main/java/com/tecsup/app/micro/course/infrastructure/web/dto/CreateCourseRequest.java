package com.tecsup.app.micro.course.infrastructure.web.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CreateCourseRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
}
