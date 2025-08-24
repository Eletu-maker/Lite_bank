package org.example.Services;

import org.example.DTO.Request.CreateTransactionRequest;
import org.example.DTO.Respone.CreateTransactionResponse;
import org.example.DTO.Respone.TransactionResponse;

import java.util.List;

public interface TransactionService {
    CreateTransactionResponse create (CreateTransactionRequest request);
    TransactionResponse getTransactionById(String id);
    List<TransactionResponse> getTransactionsFor(String accountNumber);
}
