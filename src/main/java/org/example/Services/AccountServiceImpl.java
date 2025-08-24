package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTO.Request.CreateAccountRequest;
import org.example.DTO.Request.CreateTransactionRequest;
import org.example.DTO.Request.DepositRequest;
import org.example.DTO.Respone.*;
import org.example.DTO.TransactionStatus;
import org.example.Exception.AccountNotFoundException;
import org.example.Model.Account;
import org.example.Model.Transaction;
import org.example.Model.TransactionType;
import org.example.Repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static java.math.BigDecimal.ZERO;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;


    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        Account creatingAccount = createAccountFrom(request);
        Account creatingAccountNumber = createAccountNumber(creatingAccount);
        accountRepository.save(creatingAccountNumber);
        return modelMapper.map(creatingAccountNumber,CreateAccountResponse.class);
    }

    @Override
    public DepositResponse deposit(DepositRequest request) {
        if(!accountRepository.existsByAccountNumber(request.getAccountNumber())) throw new AccountNotFoundException("Account not found");
        CreateTransactionRequest createTransactionRequest = getCreateTransactionRequest(request);
        var transaction = transactionService.create(createTransactionRequest);
        return buildDepositResponse(transaction);
    }

    @Override
    public ViewAccountResponse viewDetailsFor(String number) {
        if(!accountRepository.existsByAccountNumber(number)) throw new AccountNotFoundException("Account not found");
        List<TransactionResponse>  transactions = transactionService.getTransactionsFor(number);
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(BigDecimal.ZERO);
        TransactionResponse response = transactions.stream()
                .reduce(transactionResponse,(a,b)->
                        computeAccountBalanceFrom(a,b,transactionResponse)
                        );
        ViewAccountResponse viewAccountResponse = new ViewAccountResponse();
        viewAccountResponse.setBalance(response.getAmount().toString());
        return viewAccountResponse;
    }

    private static TransactionResponse computeAccountBalanceFrom(TransactionResponse a, TransactionResponse b, TransactionResponse transactionResponse) {
        BigDecimal total = ZERO;
        if (b.getTransactionType() == TransactionType.DEPOSIT ||b.getTransactionType() == TransactionType.CREDIT)
            total = total.add(b.getAmount());

        else
            total = total.subtract(b.getAmount());
        transactionResponse.setAmount(a.getAmount().add(total));

        return transactionResponse;
    }


    private static CreateTransactionRequest getCreateTransactionRequest (DepositRequest request){
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setAmount(request.getAmount());
        createTransactionRequest.setAccountNumber(request.getAccountNumber());
        createTransactionRequest.setTransactionType(TransactionType.CREDIT);
        return createTransactionRequest;
    }

    private static DepositResponse buildDepositResponse(CreateTransactionResponse transactionResponse){
        var depositResponse = new DepositResponse();
        depositResponse.setAmount(new BigDecimal(transactionResponse.getAmount()));
        depositResponse.setTransactionId(transactionResponse.getId());
        depositResponse.setTransactionStatus(TransactionStatus.SUCCESS);
        return depositResponse;
    }

    private Account createAccountFrom(CreateAccountRequest request){
        return modelMapper.map(request,Account.class);
    }

    private Account createAccountNumber (Account acc){
        while (acc.getAccountNumber() == null
                || accountRepository.existsByAccountNumber(acc.getAccountNumber())){
        StringBuilder Number= new StringBuilder();;
        for (int i = 0; i < 10; i++) {
            Number.append(new Random().nextInt(10));
        }
        String accountNumber = Number.toString();
        acc.setAccountNumber(accountNumber);}
        return acc;

    }
}
