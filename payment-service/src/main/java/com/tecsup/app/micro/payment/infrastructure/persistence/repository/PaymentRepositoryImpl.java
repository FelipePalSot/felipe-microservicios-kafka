package com.tecsup.app.micro.payment.infrastructure.persistence.repository;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import com.tecsup.app.micro.payment.infrastructure.persistence.mapper.PaymentPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository @RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final JpaPaymentRepository jpa;
    private final PaymentPersistenceMapper mapper;
    @Override public Optional<Payment> findById(Long id) { return jpa.findById(id).map(mapper::toDomain); }
    @Override public Payment save(Payment p) { return mapper.toDomain(jpa.save(mapper.toEntity(p))); }
}
