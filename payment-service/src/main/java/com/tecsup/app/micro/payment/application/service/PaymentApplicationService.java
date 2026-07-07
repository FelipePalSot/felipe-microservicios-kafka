package com.tecsup.app.micro.payment.application.service;
import com.tecsup.app.micro.payment.application.usecase.*;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
@Service @RequiredArgsConstructor
public class PaymentApplicationService {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final GetPaymentByIdUseCase getPaymentByIdUseCase;
    @Transactional public Payment createPayment(Long enrollmentId, BigDecimal amount, PaymentStatus status, String reason) {
        return createPaymentUseCase.execute(enrollmentId, amount, status, reason);
    }
    @Transactional(readOnly=true) public Payment getById(Long id) { return getPaymentByIdUseCase.execute(id); }
}
