package Trash;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<Transaction> transactions;

    // Конструктор класса
    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    // Метод для добавления новой транзакции в историю операций
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Метод для получения всех транзакций из истории операций
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // Метод для фильтрации транзакций по типу операции
    public List<Transaction> filterTransactionsByType(Transaction.Type transactionType) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType().equals(transactionType)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    // Метод для фильтрации транзакций по идентификатору счета
    public List<Transaction> filterTransactionsByAccountId(Long accountId) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getSourceAccountId().equals(accountId) || transaction.getDestinationAccountId().equals(accountId)) { // TODO проверить типы сравниваемых полей
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
}