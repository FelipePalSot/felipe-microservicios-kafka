package com.tecsup.app.micro.enrollment.infrastructure.client;
import com.tecsup.app.micro.enrollment.domain.exception.CourseServiceException;
import com.tecsup.app.micro.enrollment.infrastructure.client.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component @RequiredArgsConstructor @Slf4j
public class CourseClient {
    private final RestTemplate restTemplate;
    @Value("${course.service.url}") private String courseServiceUrl;
    public CourseDTO getCourseById(Long courseId) {
        String url = courseServiceUrl + "/courses/" + courseId;
        log.info("[ENROLLMENT-SERVICE] RestTemplate -> GET {} courseId={}", url, courseId);
        try {
            CourseDTO course = restTemplate.getForObject(url, CourseDTO.class);
            log.info("[ENROLLMENT-SERVICE] Course validated OK: id={}, title={}", course.getId(), course.getTitle());
            return course;
        } catch (Exception e) {
            log.error("[ENROLLMENT-SERVICE] course-service error: {}", e.getMessage());
            throw new CourseServiceException("Course id=" + courseId + " not found or course-service unavailable");
        }
    }
}
