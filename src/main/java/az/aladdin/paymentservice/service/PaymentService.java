package az.aladdin.paymentservice.service;

import az.aladdin.paymentservice.dao.entity.PaymentEntity;
import az.aladdin.paymentservice.dao.entity.PaymentStatus;
import az.aladdin.paymentservice.mapper.PaymentMapper;
import az.aladdin.paymentservice.model.PaymentRequest;
import az.aladdin.paymentservice.model.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private List<PaymentEntity> paymentTransactions = new ArrayList<>();

    private final AtomicLong idGenerator = new AtomicLong(0);

    public PaymentResponse createPaymentTransaction(PaymentRequest request) {
        var transactionEntity = paymentMapper.buildPaymentEntity(request);
        transactionEntity.setId(idGenerator.incrementAndGet());
        paymentTransactions.add(transactionEntity);
        return paymentMapper.buildPaymentResponse(transactionEntity);
    }

    public List<PaymentResponse> transactions(){
        return paymentMapper.buildPaymentResponse(paymentTransactions);
    }

    public PaymentResponse completePayment(Long id) {
        return changeStatus(id, PaymentStatus.SUCCESS);
    }

    public PaymentResponse failPayment(Long id) {
        return changeStatus(id, PaymentStatus.FAILED);
    }

    public PaymentResponse cancelPayment(Long id) {
        return changeStatus(id, PaymentStatus.CANCELLED);
    }

    private PaymentResponse changeStatus(Long id, PaymentStatus newStatus) {
        PaymentEntity payment = paymentTransactions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Payment not found"));

        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only pending payments can change status");
        }

        payment.setStatus(newStatus);

        return paymentMapper.buildPaymentResponse(payment);
    }
}
