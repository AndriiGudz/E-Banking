package model;

public class Account {
    public enum Type {EUR, USD, UAH}
    private int accountId;
    private Type currencyAccount; // валюта счета
    private double balance;

    private User user;

    // Конструктор класса
    public Account(int accountId, Type currencyAccount, double balance, User user) {
        this.accountId = accountId;
        this.currencyAccount = currencyAccount;
        this.balance = balance;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Геттеры и сеттеры для всех полей
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
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


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", currencyAccount=" + currencyAccount +
                ", balance=" + balance +
                ", user=" + user +
                '}';
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