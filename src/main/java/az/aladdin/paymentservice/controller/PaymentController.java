package az.aladdin.paymentservice.controller;

import az.aladdin.paymentservice.model.PaymentRequest;
import az.aladdin.paymentservice.model.PaymentResponse;
import az.aladdin.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse createPaymentTransaction(@RequestBody PaymentRequest request) {
        return paymentService.createPaymentTransaction(request);
    }

    @GetMapping
    public List<PaymentResponse> transactions() {
        return paymentService.transactions();
    }

    @PostMapping("/{id}/complete")
    public PaymentResponse completePayment(@PathVariable Long id) {
        return paymentService.completePayment(id);
    }

    @PostMapping("/{id}/fail")
    public PaymentResponse failPayment(@PathVariable Long id) {
        return paymentService.failPayment(id);
    }

    @PostMapping("/{id}/cancel")
    public PaymentResponse cancelPayment(@PathVariable Long id) {
        return paymentService.cancelPayment(id);
    }

}

