package com.tecsup.app.micro.enrollment.infrastructure.web.controller;
import com.tecsup.app.micro.enrollment.application.service.EnrollmentApplicationService;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.CreateEnrollmentRequest;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.EnrollmentResponse;
import com.tecsup.app.micro.enrollment.infrastructure.web.mapper.EnrollmentDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/enrollments") @RequiredArgsConstructor @Slf4j
public class EnrollmentController {
    private final EnrollmentApplicationService service;
    private final EnrollmentDtoMapper mapper;
    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@Valid @RequestBody CreateEnrollmentRequest req) {
        log.info("[ENROLLMENT-SERVICE] POST /enrollments userId={}, courseId={}", req.getUserId(), req.getCourseId());
        Enrollment e = service.createEnrollment(req.getUserId(), req.getCourseId());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(e));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.getById(id)));
    }
    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId).stream().map(mapper::toResponse).toList());
    }
    @GetMapping("/health")
    public ResponseEntity<String> health() { return ResponseEntity.ok("Enrollment Service OK"); }
}
