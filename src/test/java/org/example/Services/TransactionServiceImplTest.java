package org.example.Services;

import lombok.extern.slf4j.Slf4j;
import org.example.DTO.Request.CreateTransactionRequest;
import org.example.DTO.Respone.CreateTransactionResponse;
import org.example.DTO.Respone.TransactionResponse;
import org.example.Model.TransactionType;
import org.example.Repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
public class TransactionServiceImplTest {


    @Autowired
    private TransactionService transactionService;

    @Test
    void testCreateTransaction(){
        CreateTransactionResponse response = transactionService.create(transactionRequest());
        assertNotNull(response);
        TransactionResponse result = transactionService.getTransactionById(response.getId());
        assertNotNull(result);
        log.info("transaction response---> {}", result);
        assertThat(result.getAmount().compareTo(transactionRequest().getAmount()));

    }

    private CreateTransactionRequest transactionRequest(){
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setAccountNumber("2619081179");
        request.setAmount(new BigDecimal(20000));
        request.setTransactionType(TransactionType.CREDIT);
        return request;

    }

    @Test
    void  testGetTransactionsFor(){
        List<TransactionResponse> listOfTransaction = transactionService.getTransactionsFor("2619081179");
        assertThat(listOfTransaction).isNotNull();
        assertThat(listOfTransaction.size()).isEqualTo(10);
    }

}