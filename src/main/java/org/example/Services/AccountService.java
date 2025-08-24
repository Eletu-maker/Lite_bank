package org.example.Services;

import org.example.DTO.Request.CreateAccountRequest;
import org.example.DTO.Request.DepositRequest;
import org.example.DTO.Respone.CreateAccountResponse;
import org.example.DTO.Respone.DepositResponse;
import org.example.DTO.Respone.ViewAccountResponse;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest request);
    DepositResponse deposit (DepositRequest request);
    ViewAccountResponse viewDetailsFor(String number);

}
