package com.tecsup.app.micro.enrollment.application.service;
import com.tecsup.app.micro.enrollment.application.usecase.*;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @RequiredArgsConstructor
public class EnrollmentApplicationService {
    private final CreateEnrollmentUseCase createEnrollmentUseCase;
    private final GetEnrollmentByIdUseCase getEnrollmentByIdUseCase;
    private final GetEnrollmentsByUserIdUseCase getEnrollmentsByUserIdUseCase;
    @Transactional public Enrollment createEnrollment(Long userId, Long courseId) { return createEnrollmentUseCase.execute(userId, courseId); }
    @Transactional(readOnly=true) public Enrollment getById(Long id) { return getEnrollmentByIdUseCase.execute(id); }
    @Transactional(readOnly=true) public List<Enrollment> getByUserId(Long userId) { return getEnrollmentsByUserIdUseCase.execute(userId); }
}
