package com.elilta.javamastery.bank;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();

        BankAccount alice = bank.createAccount(
                "1001",
                "Alice",
                1000);

        BankAccount bob = bank.createAccount(
                "1002",
                "Bob",
                500);

        System.out.println(alice);
        System.out.println(bob);

        bank.transfer("1001", "1002", 250);

        System.out.println(alice);
        System.out.println(bob);

        System.out.println(bank.getAllAccounts());
    }
}
