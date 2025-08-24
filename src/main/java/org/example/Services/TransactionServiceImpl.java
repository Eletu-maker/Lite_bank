package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTO.Request.CreateTransactionRequest;
import org.example.DTO.Respone.CreateTransactionResponse;
import org.example.DTO.Respone.TransactionResponse;
import org.example.Exception.AccountNotFoundException;
import org.example.Model.Transaction;
import org.example.Repositories.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TransactionServiceImpl implements  TransactionService{
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;


    @Override
    public CreateTransactionResponse create(CreateTransactionRequest request) {
        Transaction transaction = createTransactionForm(request);
        transactionRepository.save(transaction);
        return buildTransactionFrom(transaction);
    }

    @Override
    public TransactionResponse getTransactionById(String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        return modelMapper.map(transaction,TransactionResponse.class);
    }

    @Override
    public List<TransactionResponse> getTransactionsFor(String accountNumber) {
    List<Transaction> transactions = transactionRepository.findTransactionByAccountNumber(accountNumber);
        Type listType = new TypeToken<List<TransactionResponse>>() {}.getType();
        List<TransactionResponse> transactionResponses =  modelMapper.map(transactions,listType);

        return transactionResponses;
    }

    private CreateTransactionResponse  buildTransactionFrom(Transaction transaction){
        if(!transactionRepository.existsByAccountNumber(transaction.getAccountNumber())) throw new AccountNotFoundException("Account doesn't exist");
        return modelMapper.map(transaction,CreateTransactionResponse.class);
    }

    private Transaction createTransactionForm (CreateTransactionRequest request){
        if(!transactionRepository.existsByAccountNumber(request.getAccountNumber()))throw new AccountNotFoundException("Account doesn't exist");
        return modelMapper.map(request,Transaction.class);
    }


}
