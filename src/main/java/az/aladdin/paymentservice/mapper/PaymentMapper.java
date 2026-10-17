package az.aladdin.paymentservice.mapper;

import az.aladdin.paymentservice.dao.entity.PaymentEntity;
import az.aladdin.paymentservice.model.PaymentRequest;
import az.aladdin.paymentservice.model.PaymentResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper {

    public PaymentResponse buildPaymentResponse(PaymentEntity entity){
        return PaymentResponse.builder()
                .amount(entity.getAmount())
                .id(entity.getId())
                .name(entity.getName())
                .date(entity.getDate())
                .status(entity.getStatus())
                .build();
    }

    public PaymentEntity buildPaymentEntity(PaymentRequest request){
        return PaymentEntity.builder()
                .name(request.getName())
                .amount(request.getAmount())
                .build();
    }

    public List<PaymentResponse> buildPaymentResponse(List<PaymentEntity> entities){
        return entities.stream().map(this::buildPaymentResponse).toList();
    }
}
