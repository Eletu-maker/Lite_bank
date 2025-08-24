package org.example.DTO.Respone;



import lombok.Getter;
import lombok.Setter;
import org.example.DTO.TransactionStatus;

import java.math.BigDecimal;
@Getter
@Setter
public class DepositResponse {
    private TransactionStatus transactionStatus;
    private BigDecimal amount;
    private String transactionId;
}
