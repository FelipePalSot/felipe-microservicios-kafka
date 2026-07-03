package pe.edu.tecsup.lms.courses.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.courses.domain.repository.CourseRepository;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.entity.CourseJpaEntity;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.mapper.CourseJpaMapper;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.repository.JpaCourseRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseRepositoryAdapter implements CourseRepository {

    private final JpaCourseRepository jpaRepository;
    private final CourseJpaMapper mapper;

    @Override
    public Course save(Course course) {
        CourseJpaEntity entity = mapper.toEntity(course);
        CourseJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
