package com.elilta.javamastery.bank;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }


    @GetMapping("/accounts")
    public List<BankAccount> getAccounts() {
        return bankService.getAccounts();
    }

    @PostMapping("/accounts")
    public ResponseEntity<BankAccount> createBankAccount(@Valid @RequestBody CreateAccountRequest request){

      BankAccount account = bankService.createBankAccount(request.getAccountNumber(), request.getAccountHolderName(), request.getInitialBalance());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(account);
    }

    @GetMapping("/accounts/{accountNumber}")
    public BankAccount getAccount(@PathVariable String accountNumber){
        return bankService.getAccount(accountNumber);
    }

    @PostMapping("/transfers")
    public ResponseEntity<Void> transfer(@Valid@RequestBody TransferRequest request){

        bankService.transfer(request.getFromAccount(), request.getToAccount(), request.getAmount());
        return  ResponseEntity.noContent().build();
    }
}
