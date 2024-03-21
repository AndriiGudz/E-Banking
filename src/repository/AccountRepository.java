package repository;


import model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private static final Map<Integer, Account> accounts = new HashMap<>();

    // Метод для сохранения нового счета в репозитории
    public static void saveAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    // Метод для поиска счета по его идентификатору
    public static Account findAccountById(int accountId) {
        return accounts.get(accountId);
    }

    // Метод для удаления счета из репозитория
    public static void deleteAccount(int accountId) {
        accounts.remove(accountId);
    }

    // Метод для получения всех счетов из репозитория
    public static Map<Integer, Account> getAllAccounts() {
        return accounts;
    }
}

