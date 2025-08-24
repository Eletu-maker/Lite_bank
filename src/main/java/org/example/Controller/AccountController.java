package org.example.Controller;

import lombok.AllArgsConstructor;
import org.example.DTO.Request.CreateAccountRequest;
import org.example.DTO.Request.DepositRequest;
import org.example.DTO.Respone.CreateAccountResponse;
import org.example.DTO.Respone.DepositResponse;
import org.example.DTO.Respone.ErrorResponse;
import org.example.DTO.Respone.ViewAccountResponse;
import org.example.Services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount (@RequestBody CreateAccountRequest request){
        try {
            CreateAccountResponse response = accountService.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse<>(e.getMessage()));
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney (@RequestBody DepositRequest request){
        try {
            DepositResponse  response = accountService.deposit(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse<>(e.getMessage()));
        }
    }
    @GetMapping("/viewTransaction/{accountNumber}")
    public ResponseEntity<?> viewResponse(@PathVariable("accountNumber") String accountNumber) {
        try {
            ViewAccountResponse response = accountService.viewDetailsFor(accountNumber);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }








}
