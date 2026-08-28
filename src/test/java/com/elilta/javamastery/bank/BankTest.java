package com.elilta.javamastery.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BankTest {

    private Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();

    }

    @Test
    void createAccountShouldReturnCreatedAccount() {
        BankAccount account = bank.createAccount("123", "Jessica", 1000);
        assertEquals("123", account.getAccountNumber());
        assertEquals(account, bank.findAccount("123"));
    }

    @Test
    void createAccountShouldThrowExceptionForDuplicateAccount() {
        BankAccount account = bank.createAccount("123", "Jessica", 1000);
        //BankAccount duplicateAccount = bank.createAccount("123", "Lozz", 200);

        DuplicateAccountNumberException exception = assertThrows(DuplicateAccountNumberException.class, () -> bank.createAccount("123", "Lozz", 200));

        assertEquals(1000.0, account.getAccountBalance(), 0.001);
        assertEquals(account, bank.findAccount("123"));
        assertEquals("account number already exists", exception.getMessage());
    }

    @Test
    void findAccountShouldReturnAccountForExistingAccountNumber() {
        BankAccount account = bank.createAccount("123", "Alice", 100);
        assertEquals(account, bank.findAccount("123"));
    }

    @Test
    void findAccountShouldThrowExceptionForNonExistingAccountNumber() {
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class, () -> bank.findAccount("123"));
        assertEquals("Account not found", exception.getMessage());
    }

    @Test
    void transferShouldUpdateAccountBalanceForSourceAndDestinationAccount() {

        BankAccount sourceAccount = bank.createAccount("12345", "JAne", 10000);
        BankAccount destinationAccount = bank.createAccount("123", "Alice", 1000);
        bank.transfer("12345", "123", 500);
        assertEquals(9500.0, sourceAccount.getAccountBalance(), 0.001);
        assertEquals(1500.0, destinationAccount.getAccountBalance(), 0.001);

    }

    @Test
    void transferShouldThrowExceptionWhenAccountNumberIsInvalid() {

        BankAccount sourceAccount = bank.createAccount("12345", "JAne", 10000);

        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class, () -> bank.transfer("12345", "1230", 500));
        assertEquals(10000.0, sourceAccount.getAccountBalance(), 0.001);
        assertEquals("Account not found", exception.getMessage());

    }

    @Test
    void getAllAccountsShouldReturnListOfAccounts() {

        BankAccount sourceAccount = bank.createAccount("12345", "JAne", 10000);
        BankAccount destinationAccount = bank.createAccount("123", "Alice", 1000);
        List<BankAccount> accounts = bank.getAllAccounts();
        assertEquals(2, accounts.size());
        assertTrue(accounts.contains(sourceAccount));
        assertTrue(accounts.contains(destinationAccount));


    }

    @Test
    void getAllAccountsShouldReturnEmptyListForEmptyAccount() {
        assertEquals(0, bank.getAllAccounts().size());
        assertTrue(bank.getAllAccounts().isEmpty());
    }

    @Test
    void getAllAccountsShouldNotExposeInternalAccountCollection() {
        bank.createAccount("12345", "JAne", 10000);
        bank.createAccount("123", "Alice", 1000);
        List<BankAccount> accounts = bank.getAllAccounts();
        accounts.clear();
        assertEquals(2, bank.getAllAccounts().size());
    }
}
