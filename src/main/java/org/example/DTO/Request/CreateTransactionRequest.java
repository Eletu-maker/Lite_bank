package org.example.DTO.Request;

import lombok.Getter;
import lombok.Setter;
import org.example.Model.TransactionType;

import java.math.BigDecimal;
@Getter
@Setter
public class CreateTransactionRequest {
    private TransactionType transactionType;
    private BigDecimal amount;
    private String accountNumber;
}
