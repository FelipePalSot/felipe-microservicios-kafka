package com.tecsup.app.micro.enrollment.domain.repository;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import java.util.List;
import java.util.Optional;
public interface EnrollmentRepository {
    List<Enrollment> findAll();
    Optional<Enrollment> findById(Long id);
    List<Enrollment> findByUserId(Long userId);
    Enrollment save(Enrollment enrollment);
    void deleteById(Long id);
}
