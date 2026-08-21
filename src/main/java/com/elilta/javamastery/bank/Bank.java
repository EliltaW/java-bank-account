package com.elilta.javamastery.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<String, BankAccount> accounts = new HashMap<>();

    public BankAccount createAccount(String accountNumber, String accountHolderName, double initialBalance) {

        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("account number already exists");
        }

        BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);

        return account;
    }

    public BankAccount findAccount(String accountNumber) {

        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return account;
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount){
        BankAccount sourceAccount = findAccount(fromAccountNumber);
        BankAccount destinationAccount = findAccount(toAccountNumber);
        sourceAccount.transfer(destinationAccount,amount);

    }

    public List<BankAccount> getAllAccounts(){

        return new ArrayList<>(accounts.values());
    }

}
