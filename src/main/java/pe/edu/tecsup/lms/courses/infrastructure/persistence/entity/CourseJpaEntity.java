package pe.edu.tecsup.lms.courses.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.tecsup.lms.courses.domain.model.Course;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String instructor;

    @Enumerated(EnumType.STRING)
    private Course.CourseStatus status;

    private LocalDateTime createdAt;
}
