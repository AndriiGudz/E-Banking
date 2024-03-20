package model;

public class Account {
    public enum Type {EUR, USD, UAH}
    private long accountId;
    private Type currencyAccount; // валюта счета
    private double balance;

    // Конструктор класса
    public Account(long accountId, Type currencyAccount, double balance) {
        this.accountId = accountId;
        this.currencyAccount = currencyAccount;
        this.balance = balance;
    }

    // Геттеры и сеттеры для всех полей
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Type getCurrencyAccount() {
        return currencyAccount;
    }

    public void setCurrencyAccount(Type currencyType) {
        this.currencyAccount = currencyType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Метод для пополнения счета
    public void deposit(double amount) {
        balance += amount;
    }

    // Метод для снятия средств со счета
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Недостаточно средств.");
        }
    }

    // Метод для перевода средств с одного счета на другой
    public void transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
        } else {
            System.out.println("Недостаточно средств.");
        }
    }
}