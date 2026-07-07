package com.tecsup.app.micro.course.domain.model;
import lombok.*;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Course {
    private Long id;
    private String title;
    private String description;
    private Boolean published;
    private LocalDateTime createdAt;
}
