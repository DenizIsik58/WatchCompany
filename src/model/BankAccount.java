package model;

public class BankAccount {

    private int balance;


    public BankAccount(int balance) {
       this.balance = balance;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int value){
        balance += value;
    }

    public void withdraw(int amount){
        balance -= amount;
    }




}
