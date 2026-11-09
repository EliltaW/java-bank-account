package com.elilta.javamastery.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @InjectMocks
    private BankService bankService;

    @Mock
    private AccountRepository accountRepository;

     @Test
    void shouldCreateAndReturnAccount(){
        when(accountRepository.isAccountPresent("123")).thenReturn(false);
        BankAccount result = bankService.createBankAccount("123", "Liya", 1000);
         assertEquals("123", result.getAccountNumber());
         assertEquals(1000, result.getAccountBalance(), 0.001);

         verify(accountRepository).saveAccount(eq("123"), any(BankAccount.class));
     }


    @Test
    void shouldThrowExceptionForExistingAccount(){
        when(accountRepository.isAccountPresent("123")).thenReturn(true);
        DuplicateAccountNumberException ex = assertThrows(DuplicateAccountNumberException.class, ()-> bankService.createBankAccount("123", "Liya", 1000));

        assertEquals("account number already exists", ex.getMessage());
        verify(accountRepository, never()).saveAccount(anyString(), any(BankAccount.class));
    }

    @Test
    void shouldReturnAccount(){
        when(accountRepository.isAccountPresent("123")).thenReturn(true);
        when(accountRepository.getAccount("123")).thenReturn(new BankAccount("123", "Liya", 1000));

        BankAccount result = bankService.getAccount("123");
        assertEquals("123", result.getAccountNumber());
        assertEquals(1000, result.getAccountBalance(), 0.001);
        assertEquals("Liya", result.getAccountHolderName());

    }


    @Test
    void shouldThrowExceptionForNonExistingAccount(){
        when(accountRepository.isAccountPresent("123")).thenReturn(false);

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,() -> bankService.getAccount("123"));
        assertEquals("Account not found", ex.getMessage());

        verify(accountRepository, never()).getAccount(anyString());


    }

}
