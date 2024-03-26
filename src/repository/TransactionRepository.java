package repository;

import model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private static final List<Transaction> transactions = new ArrayList<>();

    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public static List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }
}
