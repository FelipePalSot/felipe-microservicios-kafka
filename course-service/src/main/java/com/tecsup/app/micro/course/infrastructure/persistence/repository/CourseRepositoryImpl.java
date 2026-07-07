package com.tecsup.app.micro.course.infrastructure.persistence.repository;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import com.tecsup.app.micro.course.infrastructure.persistence.mapper.CoursePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository @RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {
    private final JpaCourseRepository jpa;
    private final CoursePersistenceMapper mapper;
    @Override public List<Course> findAll() { return jpa.findAll().stream().map(mapper::toDomain).toList(); }
    @Override public Optional<Course> findById(Long id) { return jpa.findById(id).map(mapper::toDomain); }
    @Override public Course save(Course course) { return mapper.toDomain(jpa.save(mapper.toEntity(course))); }
    @Override public void deleteById(Long id) { jpa.deleteById(id); }
}
