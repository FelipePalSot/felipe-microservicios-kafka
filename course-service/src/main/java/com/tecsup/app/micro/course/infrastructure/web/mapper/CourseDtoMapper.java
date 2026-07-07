package com.tecsup.app.micro.course.infrastructure.web.mapper;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.infrastructure.web.dto.CreateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.CourseResponse;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CourseDtoMapper {
    CourseResponse toResponse(Course course);
    Course toDomain(CreateCourseRequest req);
}
