package org.example.DTO.Request;


import lombok.Getter;
import lombok.Setter;
import org.example.DTO.AccountType;

@Getter
@Setter
public class CreateAccountRequest {
    private String name;
    private String username;
    private String password;
    private AccountType accountType;
}
