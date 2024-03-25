package model;

import java.time.LocalDateTime;

public class Transaction {
    public enum Type {DEBIT, CREDIT}
    private static int nexId = 1;
    private final int transactionId; // идентификатор транзакции
    private Type transactionType; // тип операции
    private double amount; // сумма транзакции
    private LocalDateTime dateTime; // время транзакции
    private String sourceAccountId; // счет с которого переводят
    private String destinationAccountId; // счет на который переводят

    // Конструктор класса
    public Transaction(Type transactionType, double amount, LocalDateTime dateTime) {
        this.transactionId = nexId++;
        this.transactionType = transactionType;
        this.amount = amount;
        this.dateTime = dateTime;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                '}';
    }

    // Геттеры и сеттеры для всех полей
    public int getTransactionId() {
        return transactionId;
    }



    public Type getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Type transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public CurrencyCode getCurrencyCode() {
        return getCurrencyCode();
    }
}