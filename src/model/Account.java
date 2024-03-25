package model;

import java.util.UUID;

public class Account {
    public enum Type {EUR, USD, GBP}
    private UUID accountId;
    private Type currencyAccount; // валюта счета
    private double balance;
    private User user;

    // Конструктор класса
    public Account(Type currencyAccount, double balance, User user) {
        // this.accountId = UUID.fromString(currencyAccount.toString() + UUID.randomUUID());
        this.accountId = UUID.randomUUID();
        this.currencyAccount = currencyAccount;
        this.balance = balance;
        this.user = user;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Type getCurrencyAccount() {
        return currencyAccount;
    }

    public void setCurrencyAccount(Type currencyAccount) {
        this.currencyAccount = currencyAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String userString = (user != null) ? user.toString() : "null";
        return "Account {" +
                "accountId=" + accountId +
                ", currencyAccount=" + currencyAccount +
                ", balance=" + balance +
                ", user=" + userString +
                '}';
    }
}