package com.elilta.javamastery.bank;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class TransferRequest {

    @NotBlank
    private String fromAccount;
    @NotBlank
    private String toAccount;
    @Positive
    private double amount;

    public TransferRequest(String fromAccount, String toAccount, double amount){

        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String getFromAccount(){
        return fromAccount;
    }


    public String getToAccount(){
        return toAccount;
    }


    public double getAmount(){
        return amount;
    }

    public void setFromAccount(String fromAccount){
        this.fromAccount = fromAccount;
    }

    public void setToAccount(String toAccount){
        this.toAccount = toAccount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}
