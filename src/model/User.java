package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private List<Account> accounts; // список счетов
    private List<Transaction> transactionHistory; // список транзакций

    // Конструктор класса
    public User(int userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
    }

    // Геттеры и сеттеры для всех полей
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    // Метод для добавления нового счета
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    // Метод для добавления новой транзакции в историю операций
    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}