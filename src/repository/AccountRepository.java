package repository;


import model.Account;
import model.Transaction;
import model.TransactionType;
import model.User;
import service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountRepository {
    // Коллекция для хранения счетов
    private static final Map<UUID, Account> accounts = new HashMap<>();

    // Метод для открытия нового счета
    public static Account openAccount(User user, Account.Type currencyAccount, double initialBalance) {
        // Проверяем, авторизован ли текущий пользователь
        if (UserService.currentUser() == null || !UserService.currentUser().equals(user)) {
            System.out.println("Ошибка: Требуется авторизация для открытия нового счета.");
            return null;
        }

        // Создаем новый счет
        Account newAccount = new Account(currencyAccount, initialBalance, user);
        System.out.println("\nНовый счет успешно создан:");
        System.out.println("Номер счета: " + newAccount.getAccountId() + " | Валюта счета: " + newAccount.getCurrencyAccount()
        + " | Имя владельца счета: " + newAccount.getUser().getUsername() + " | Баланс счета: " + newAccount.getBalance() + " " + newAccount.getCurrencyAccount());

        // Сохраняем счет в репозитории
        saveAccount(newAccount);

        return newAccount;
    }


    // Метод для сохранения счета в репозитории
    public static void saveAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    // Просмотреть все счета всех пользователей
    public static void getAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Нет открытых счетов.");
        } else {
            for (Account account : accounts.values()) {
                System.out.println("------------------------------------");
                System.out.println("**Информация о счете:**");
                System.out.println("Владелец: " + account.getUser().getUsername());
                System.out.println("Номер счета: " + account.getAccountId());
                System.out.println("Валюта: " + account.getCurrencyAccount());
                System.out.println("Баланс: " + account.getBalance());
                System.out.println("------------------------------------");
            }
        }
    }

    // Просмотреть открытые счета у пользователя
    public static void viewAllAccountsUser() {
        User currentUser = UserService.currentUser(); // Получаем текущего авторизованного пользователя

        if (currentUser != null) {
            boolean hasAccounts = false;

            // Проходим по всем счетам в репозитории
            for (Account account : accounts.values()) {
                // Проверяем, совпадает ли владелец счета с текущим пользователем
                if (account.getUser().equals(currentUser)) {
                    System.out.println("------------------------------------");
                    System.out.println("**Информация о счете:**");
                    System.out.println("Владелец: " + account.getUser().getUsername());
                    System.out.println("Номер счета: " + account.getAccountId());
                    System.out.println("Валюта: " + account.getCurrencyAccount());
                    System.out.println("Баланс: " + account.getBalance());
                    System.out.println("------------------------------------");

                    hasAccounts = true;
                }
            }

            if (!hasAccounts) {
                System.out.println("У вас еще нет открытых счетов.");
            }
        } else {
            System.out.println("Вы не авторизованы.");
        }
    }

    public static void withdrawAmount(User user, String accountIdString, double amount) {
        if (UserService.currentUser() == null || !UserService.currentUser().equals(user)) {
            System.out.println("Ошибка: Требуется авторизация для открытия нового счета.");
        }
        UUID accountId = UUID.fromString(accountIdString);
        Account account = accounts.get(accountId);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
                Transaction transaction = new Transaction(Transaction.Type.DEBIT, amount, LocalDateTime.now());
                System.out.println("Cумма " + amount + " была успешно снята со счета");
                System.out.println("Id транзакции: " + transaction.getTransactionId() + " | Тип операции: " + transaction.getTransactionType() + " | Сумма: " + amount + " | Время операции: " + transaction.getDateTime().format(formatter));
                System.out.println("Текущий баланс: " + account.getBalance());
            } else {
                System.out.println("На счету недостаточно средств");
            }
        } else {
            System.out.println("Такой счет не найден");
        }
    }
    public static void depositAmount(User user, String accountIdString, double amount) {
        if (UserService.currentUser() == null || !UserService.currentUser().equals(user)) {
            System.out.println("Ошибка: Требуется авторизация для открытия нового счета.");
        }
        UUID accountId = UUID.fromString(accountIdString);
        Account account = accounts.get(accountId);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            Transaction transaction = new Transaction(Transaction.Type.CREDIT, amount, LocalDateTime.now());
            System.out.println("Cумма " + amount + " была успешно внесена на счет");
            System.out.println("Id транзакции: " + transaction.getTransactionId() + " | Тип операции: " + transaction.getTransactionType() + " | Сумма: " + amount + " | Время операции: " + transaction.getDateTime().format(formatter));
            System.out.println("Текущий баланс: " + account.getBalance());
        } else {
            System.out.println("Такой счет не найден");
        }
    }

}

