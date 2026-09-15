package com.elilta.javamastery.bank;

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
    public BankAccount createBankAccount(@RequestBody CreateAccountRequest request){

        return bankService.createBankAccount(request.getAccountNumber(), request.getAccountHolderName(), request.getInitialBalance());
    }

    @GetMapping("/accounts/{accountNumber}")
    public BankAccount getAccount(@PathVariable String accountNumber){
        return bankService.getAccount(accountNumber);
    }
}
