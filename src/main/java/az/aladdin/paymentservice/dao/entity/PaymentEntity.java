package az.aladdin.paymentservice.dao.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@ToString
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class PaymentEntity {


    private Long id;
    private String name;
    private BigDecimal amount;

    @Builder.Default
    private LocalDate date = LocalDate.now();

    @Builder.Default
    private PaymentStatus status = PaymentStatus.PENDING;

}
