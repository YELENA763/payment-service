package az.aladdin.paymentservice.model;

import az.aladdin.paymentservice.dao.entity.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PaymentResponse {
    private Long id;
    private String name;
    private BigDecimal amount;
    private LocalDate date;
    private PaymentStatus status;
}
