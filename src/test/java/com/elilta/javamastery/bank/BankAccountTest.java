package com.elilta.javamastery.bank;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    private BankAccount account;
    private BankAccount sourceAccount;
    private BankAccount destinationAccount;
    private BankAccount logicalDestinationAccount;


    @BeforeEach
    void setup() {

        account = new BankAccount("345667", "Alice", 1000);
        sourceAccount = new BankAccount("3546", "Anna", 1000);
        destinationAccount = new BankAccount("3567546", "alexa", 500);
        logicalDestinationAccount = new BankAccount("3546", "Liam", 200);
    }

    @Test
    void depositShouldIncreaseBalance() {

        account.deposit(200);
        assertEquals(1200.0, account.getAccountBalance(), 0.001);
    }

    @Test
    void depositShouldThrowExceptionForNegativeAmount() {

        assertThrows(IllegalArgumentException.class, () -> account.deposit(-200));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        account.withdraw(200);
        assertEquals(800.0, account.getAccountBalance(), 0.001);
    }

    @Test
    void withdrawShouldThrowExceptionForNegativeAmount() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(-200));
        assertEquals("withdrawAmount must be greater than 0", exception.getMessage());
    }

    @Test
    void withdrawShouldThrowExceptionForZeroAmount() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertEquals("withdrawAmount must be greater than 0", exception.getMessage());
    }

    @Test
    void withdrawShouldThrowExceptionForInsufficientBalance() {

        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> account.withdraw(1200));
        assertEquals("insufficient funds", exception.getMessage());
    }

    @Test
    void transferShouldThrowExceptionWhenFundsAreInsufficient() {

        assertThrows(InsufficientFundsException.class, () -> sourceAccount.transfer(destinationAccount, 2000));
        assertEquals(1000, sourceAccount.getAccountBalance());
        assertEquals(500, destinationAccount.getAccountBalance());

    }

    @Test
    void transferShouldChangeBalanceForValidAmount() {
        sourceAccount.transfer(destinationAccount, 500);
        assertEquals(500, sourceAccount.getAccountBalance());
        assertEquals(1000, destinationAccount.getAccountBalance());
    }

    @Test
    void transferShouldNotChangeBalanceForSameDestinationAccount() {
        sourceAccount.transfer(sourceAccount, 200);
        assertEquals(1000.0, sourceAccount.getAccountBalance(), 0.001);
    }

    @Test
    void transferShouldNotChangeBalancesWhenDestinationHasSameAccountNumber() {

        sourceAccount.transfer(logicalDestinationAccount, 300);
        assertEquals(1000, sourceAccount.getAccountBalance());
        assertEquals(200, logicalDestinationAccount.getAccountBalance());
    }

    @Test
    void transferShouldThrowExceptionForNullDestinationAccount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sourceAccount.transfer(null, 100));
        assertEquals(1000, sourceAccount.getAccountBalance());
        assertEquals("destination account can not be null", exception.getMessage());
    }

    @Test
    void transferShouldThrowExceptionForInvalidAmount() {
       assertThrows(IllegalArgumentException.class, () -> sourceAccount.transfer(destinationAccount, -200));
        assertEquals(1000,sourceAccount.getAccountBalance());
        assertEquals(500,destinationAccount.getAccountBalance());
    }

}
