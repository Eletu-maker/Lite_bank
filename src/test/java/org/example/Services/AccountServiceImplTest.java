package org.example.Services;

import org.example.DTO.AccountType;
import org.example.DTO.PaymentMethod;
import org.example.DTO.Request.CreateAccountRequest;
import org.example.DTO.Request.DepositRequest;
import org.example.DTO.Respone.CreateAccountResponse;
import org.example.DTO.Respone.DepositResponse;
import org.example.DTO.Respone.ViewAccountResponse;
import org.example.DTO.TransactionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    void testCanCreateAccount(){
        CreateAccountResponse response = accountService.createAccount(createAccountRequest());
        assertNotNull(response);
        assertEquals(AccountType.CURRENT,response.getAccountType());
        assertEquals("magic",response.getUsername());
        assertEquals(10,response.getAccountNumber().length());
        System.out.println(response.getAccountNumber());

    }

    private CreateAccountRequest createAccountRequest(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setName("usman");
        request.setUsername("magic");
        request.setPassword("magic123");
        request.setAccountType(AccountType.CURRENT);
        return  request;
    }

    @Test
    void testCanDeposit(){
        DepositResponse response = accountService.deposit(createDepositRequest());
        assertNotNull(response);
        assertEquals(TransactionStatus.SUCCESS,response.getTransactionStatus());

    }

    private DepositRequest createDepositRequest(){
        DepositRequest request = new DepositRequest();
        request.setAccountNumber("2619081179");
        request.setAmount(new BigDecimal(20000));
        request.setPaymentMethod(PaymentMethod.CASH);
        return request;
    }

    @Test
    void testThatViewDetailsFor(){
        ViewAccountResponse response =
                accountService.viewDetailsFor("2619081179");
        assertThat(response).isNotNull();
        assertThat(new BigDecimal(response.getBalance()))
                .isEqualByComparingTo("200000");

    }
}