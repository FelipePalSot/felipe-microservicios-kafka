package com.tecsup.app.micro.payment.infrastructure.persistence.mapper;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.infrastructure.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface PaymentPersistenceMapper {
    Payment toDomain(PaymentEntity entity);
    PaymentEntity toEntity(Payment domain);
}
