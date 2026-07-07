package com.tecsup.app.micro.enrollment.infrastructure.persistence.repository;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import com.tecsup.app.micro.enrollment.infrastructure.persistence.mapper.EnrollmentPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository @RequiredArgsConstructor
public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    private final JpaEnrollmentRepository jpa;
    private final EnrollmentPersistenceMapper mapper;
    @Override public List<Enrollment> findAll() { return jpa.findAll().stream().map(mapper::toDomain).toList(); }
    @Override public Optional<Enrollment> findById(Long id) { return jpa.findById(id).map(mapper::toDomain); }
    @Override public List<Enrollment> findByUserId(Long userId) { return jpa.findByUserId(userId).stream().map(mapper::toDomain).toList(); }
    @Override public Enrollment save(Enrollment e) { return mapper.toDomain(jpa.save(mapper.toEntity(e))); }
    @Override public void deleteById(Long id) { jpa.deleteById(id); }
}
