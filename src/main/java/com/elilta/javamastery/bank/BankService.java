package com.elilta.javamastery.bank;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository){

        this.accountRepository=accountRepository;
    }

    public List<BankAccount> getAccounts() {
        return accountRepository.getAllAccounts();
    }

    public BankAccount createBankAccount(String accountNumber, String accountHolderName, double initialBalance){

        if (accountRepository.isAccountPresent(accountNumber)){
            throw new DuplicateAccountNumberException("account number already exists");

        }
        BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
        accountRepository.saveAccount(accountNumber, account);

        return account;

    }

    public BankAccount getAccount(String accountNumber){
        if (!accountRepository.isAccountPresent(accountNumber)){

            throw new AccountNotFoundException("Account not found");
        }
        return accountRepository.getAccount(accountNumber);

    }

    public void transfer(String fromAccount, String toAccount, double amount) {

        BankAccount source = getAccount(fromAccount);
        BankAccount destination = getAccount(toAccount);

        source.transfer(destination, amount);
    }



}
