package org.example.DTO.Request;

import lombok.Getter;
import lombok.Setter;
import org.example.DTO.PaymentMethod;

import java.math.BigDecimal;
@Getter
@Setter
public class DepositRequest {
    private String accountNumber;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
}
