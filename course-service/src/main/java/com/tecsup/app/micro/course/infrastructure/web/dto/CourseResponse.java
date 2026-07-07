package com.tecsup.app.micro.course.infrastructure.web.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private Boolean published;
    private LocalDateTime createdAt;
}
