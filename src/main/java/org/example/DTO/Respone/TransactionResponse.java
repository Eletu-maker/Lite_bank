package org.example.DTO.Respone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.Model.TransactionType;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TransactionResponse {
    private String id;
    private BigDecimal amount;
    private TransactionType transactionType;
}
