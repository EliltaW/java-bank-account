package com.elilta.javamastery.bank;

public class DuplicateAccountNumberException extends RuntimeException{

    public DuplicateAccountNumberException(String message){
        super(message);
    }
}
