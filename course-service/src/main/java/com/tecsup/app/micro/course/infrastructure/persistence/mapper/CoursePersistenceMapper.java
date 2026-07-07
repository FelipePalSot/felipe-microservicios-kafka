package com.tecsup.app.micro.course.infrastructure.persistence.mapper;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.infrastructure.persistence.entity.CourseEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CoursePersistenceMapper {
    Course toDomain(CourseEntity entity);
    CourseEntity toEntity(Course domain);
}
