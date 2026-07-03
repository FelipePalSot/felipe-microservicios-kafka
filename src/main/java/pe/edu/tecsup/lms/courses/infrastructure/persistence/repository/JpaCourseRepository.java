package pe.edu.tecsup.lms.courses.infrastructure.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.entity.CourseJpaEntity;

public interface JpaCourseRepository extends JpaRepository<CourseJpaEntity, Long> {
}
