package com.tecsup.app.micro.payment.application.usecase;
import com.tecsup.app.micro.payment.domain.exception.PaymentNotFoundException;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor
public class GetPaymentByIdUseCase {
    private final PaymentRepository paymentRepository;
    public Payment execute(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
    }
}
