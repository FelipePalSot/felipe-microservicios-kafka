package com.tecsup.app.micro.course.infrastructure.web.controller;
import com.tecsup.app.micro.course.application.service.CourseApplicationService;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.infrastructure.web.dto.CreateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.CourseResponse;
import com.tecsup.app.micro.course.infrastructure.web.mapper.CourseDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor @Slf4j
public class CourseController {
    private final CourseApplicationService service;
    private final CourseDtoMapper mapper;
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAll() {
        log.info("[COURSE-SERVICE] GET /courses");
        return ResponseEntity.ok(service.getAllCourses().stream().map(mapper::toResponse).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable Long id) {
        log.info("[COURSE-SERVICE] GET /courses/{}", id);
        return ResponseEntity.ok(mapper.toResponse(service.getCourseById(id)));
    }
    @PostMapping
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CreateCourseRequest req) {
        log.info("[COURSE-SERVICE] POST /courses - title={}", req.getTitle());
        Course course = mapper.toDomain(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(service.createCourse(course)));
    }
    @PostMapping("/{id}/publish")
    public ResponseEntity<CourseResponse> publish(@PathVariable Long id) {
        log.info("[COURSE-SERVICE] POST /courses/{}/publish", id);
        return ResponseEntity.ok(mapper.toResponse(service.publishCourse(id)));
    }
    @GetMapping("/health")
    public ResponseEntity<String> health() { return ResponseEntity.ok("Course Service OK"); }
}
