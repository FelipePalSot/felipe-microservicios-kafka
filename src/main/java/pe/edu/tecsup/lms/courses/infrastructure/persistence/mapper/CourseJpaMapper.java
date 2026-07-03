package pe.edu.tecsup.lms.courses.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.entity.CourseJpaEntity;

@Mapper(componentModel = "spring")
public interface CourseJpaMapper {

    CourseJpaEntity toEntity(Course course);

    Course toDomain(CourseJpaEntity entity);
}
