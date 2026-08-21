package com.elilta.javamastery.bank;


public class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private double accountBalance;

   public BankAccount( String accountNumber,String accountHolderName, double initialBalance) {

        if (accountHolderName == null || accountHolderName.isBlank()) {
            throw new IllegalArgumentException("accountHolder can not be empty");
        }
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("accountNumber can not be empty");
        }
        if (initialBalance < 0){
            throw new IllegalArgumentException("initialBalance can not be negative");
        }


        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.accountBalance= initialBalance;


    }

    public String getAccountHolderName(){

        return accountHolderName;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public double getAccountBalance(){
        return accountBalance;
    }

    public void deposit(double depositAmount){
       if (depositAmount > 0){
        accountBalance += depositAmount; }
       else throw new IllegalArgumentException("deposit amount must be greater than 0 ");
    }

    public void withdraw(double withdrawAmount){

        if (withdrawAmount <= 0){

            throw new IllegalArgumentException("withdrawAmount must be greater than 0");
        }
        if ( withdrawAmount >  accountBalance){
            throw new IllegalArgumentException("insufficient funds");
        }
        else accountBalance -= withdrawAmount;


    }

    public void transfer(BankAccount destinationAccount,double amount){
       if (destinationAccount == null){
           throw new IllegalArgumentException("destination account can not be null");
       }
       if (this.equals(destinationAccount)){
           return;
       }
       withdraw(amount);
        destinationAccount.deposit(amount);

    }

    @Override
    public String toString() {
       //BankAccount{accountNumber='ACC-1001', accountHolderName='John Doe', accountBalance=500.0}
       return  "BankAccount{accountNumber= " + accountNumber + "," + "accountHolderName=" + accountHolderName+ "," +
               "accountBalance = " + accountBalance + "}"; }

    @Override
    public boolean equals(Object obj) {
       if (this == obj){return true;}
       if (obj == null){return false;}
       if (!(obj instanceof BankAccount)){return false;}
       BankAccount other = (BankAccount) obj;
       return this.accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}

