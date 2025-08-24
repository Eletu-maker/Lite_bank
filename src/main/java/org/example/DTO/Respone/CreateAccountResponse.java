package org.example.DTO.Respone;

import lombok.Getter;
import lombok.Setter;
import org.example.DTO.AccountType;
@Getter
@Setter
public class CreateAccountResponse {
    private String accountNumber;
    private String username;
    private AccountType accountType;
}
