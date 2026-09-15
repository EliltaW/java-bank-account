package com.elilta.javamastery.bank;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {

    Map<String, BankAccount>accounts = new HashMap<>();

    public List<BankAccount> getAllAccounts(){

        return new ArrayList<>(accounts.values());
    }

    public BankAccount createBankAccount(String accountNumber, String accountHolderName, double initialBalance){

        if (accounts.containsKey(accountNumber)) {
            throw new DuplicateAccountNumberException("account number already exists");
        }

        BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);

        return account;
    }

    public boolean isAccountPresent(String accountNumber){

        return accounts.containsKey(accountNumber);
    }

    public void saveAccount(String accountNumber, BankAccount account){
        accounts.put(accountNumber, account);
    }

    public BankAccount getAccount(String accountNumber){

        return accounts.get(accountNumber);

    }
}
