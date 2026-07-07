package com.tecsup.app.micro.payment.infrastructure.web.mapper;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.infrastructure.web.dto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface PaymentDtoMapper {
    @Mapping(target = "status", expression = "java(payment.getStatus().name())")
    PaymentResponse toResponse(Payment payment);
}
