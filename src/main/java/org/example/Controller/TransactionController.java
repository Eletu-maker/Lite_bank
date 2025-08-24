package org.example.Controller;

import lombok.AllArgsConstructor;
import org.example.DTO.Request.CreateTransactionRequest;
import org.example.DTO.Respone.CreateTransactionResponse;
import org.example.DTO.Respone.ErrorResponse;
import org.example.DTO.Respone.TransactionResponse;
import org.example.Services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("/createTransaction")
    public ResponseEntity<?> createTransaction (@RequestBody CreateTransactionRequest request){
        try {
            CreateTransactionResponse response = transactionService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse<>(e.getMessage()));
        }
    }

    @GetMapping("/getTransactionById/{id}")
    public ResponseEntity<?> getTransactionById (@PathVariable("id") String id){
        try {
            TransactionResponse response = transactionService.getTransactionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse<>(e.getMessage()));
        }
    }

    @GetMapping("/TransactionsByAccountNumber/{accountNumber}")
    public ResponseEntity<?> TransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        try{
            List<TransactionResponse> transactionResponses = transactionService
                    .getTransactionsFor(accountNumber);
            return ResponseEntity.status(HttpStatus.OK).body(transactionResponses);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
}
