package org.example.DTO.Respone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionResponse {
    private String id;
    private String transactionType;
    private String amount;
}
