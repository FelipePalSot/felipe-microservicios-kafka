package com.tecsup.app.micro.payment.infrastructure.web.controller;
import com.tecsup.app.micro.payment.application.service.PaymentApplicationService;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.model.PaymentStatus;
import com.tecsup.app.micro.payment.infrastructure.web.dto.CreatePaymentRequest;
import com.tecsup.app.micro.payment.infrastructure.web.dto.PaymentResponse;
import com.tecsup.app.micro.payment.infrastructure.web.mapper.PaymentDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/payments") @RequiredArgsConstructor @Slf4j
public class PaymentController {
    private final PaymentApplicationService service;
    private final PaymentDtoMapper mapper;

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody CreatePaymentRequest req) {
        log.info("[PAYMENT-SERVICE] POST /payments enrollmentId={}, amount={}", req.getEnrollmentId(), req.getAmount());
        PaymentStatus status = "REJECTED".equalsIgnoreCase(req.getStatus()) ? PaymentStatus.REJECTED : PaymentStatus.APPROVED;
        Payment payment = service.createPayment(req.getEnrollmentId(), req.getAmount(), status, req.getRejectionReason());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(payment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.getById(id)));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() { return ResponseEntity.ok("Payment Service OK"); }
}
