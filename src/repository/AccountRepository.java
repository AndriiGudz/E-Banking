package repository;


import model.Account;
import model.Transaction;
import model.User;
import service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static repository.TransactionRepository.addTransaction;

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

    public static List<Account> viewAllAccountsUser() {
        User currentUser = UserService.currentUser(); // Получаем текущего авторизованного пользователя

        List<Account> userAccounts = new ArrayList<>(); // Создаем список для хранения счетов пользователя

        if (currentUser != null) {
            boolean hasAccounts = false;

            // Проходим по всем счетам в репозитории
            for (Account account : accounts.values()) {
                // Проверяем, совпадает ли владелец счета с текущим пользователем
                if (account.getUser().equals(currentUser)) {
                    userAccounts.add(account); // Добавляем счет в список счетов пользователя

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

        return userAccounts; // Возвращаем список счетов пользователя
    }

    public static void withdraw(Scanner scanner) {
        User user = UserService.currentUser();
        if (user == null) {
            System.out.println("Ошибка: Требуется авторизация для операции снятия со счета.");
            return;
        }
        List<Account> userAccounts = AccountRepository.viewAllAccountsUser();
        if (userAccounts == null || userAccounts.isEmpty()) {
            return;
        }
        System.out.println("Выберите номер счета: ");
        String accountIdString = scanner.nextLine();
        UUID accountId = UUID.fromString(accountIdString);
        Account account = accounts.get(accountId);
        if (account != null) {
            System.out.println("Введите сумму для снятия со счета: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
                Transaction transaction = new Transaction(Transaction.Type.DEBIT, amount, LocalDateTime.now());
                addTransaction(transaction);
                System.out.println("Cумма " + amount + " была успешно снята со счета");
                System.out.println("Id транзакции: " + transaction.getTransactionId() + " | Тип операции: " + transaction.getTransactionType() + " | Сумма: " + amount + " | Время операции: " + transaction.getDateTime().format(formatter));
                System.out.println("Текущий баланс: " + account.getBalance());
            } else {
                System.out.println("На счету недостаточно средств");
            }
        } else {
            System.out.println("Такой счет не найден");
            return;
        }
    }

    public static void deposit(Scanner scanner) {
        User user = UserService.currentUser();
        if (user == null) {
            System.out.println("Ошибка: Требуется авторизация для операции пополнения счета.");
            return;
        }
        List<Account> userAccounts = AccountRepository.viewAllAccountsUser();
        if (userAccounts == null || userAccounts.isEmpty()) {
            return;
        }
        System.out.println("Выберите номер счета для пополнения: ");
        String accountIdString = scanner.nextLine();
        UUID accountId = UUID.fromString(accountIdString);
        Account account = accounts.get(accountId);
        if (account != null) {
            System.out.println("Введите сумму для пополнения счета: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.setBalance(account.getBalance() + amount);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            Transaction transaction = new Transaction(Transaction.Type.CREDIT, amount, LocalDateTime.now());
            addTransaction(transaction);
            System.out.println("Cумма " + amount + " была успешно внесена на счет");
            System.out.println("Id транзакции: " + transaction.getTransactionId() + " | Тип операции: " + transaction.getTransactionType() + " | Сумма: " + amount + " | Время операции: " + transaction.getDateTime().format(formatter));
            System.out.println("Текущий баланс: " + account.getBalance());
        } else {
            System.out.println("Такой счет не найден");
            return;
        }
    }

    public static void viewBalance(Scanner scanner){
        User user = UserService.currentUser();
        if (user == null) {
            System.out.println("Ошибка: Требуется авторизация для просмотра баланса счета.");
            return;
        }
        List<Account> userAccounts = AccountRepository.viewAllAccountsUser();
        if (userAccounts == null || userAccounts.isEmpty()) {
            return;
        }
        System.out.println("Выберите номер счета для просмотра баланса: ");
        String accountIdString = scanner.nextLine();
        UUID accountId = UUID.fromString(accountIdString);
        Account account = accounts.get(accountId);
        if (account != null) {
            System.out.println("Текущий баланс: " + account.getBalance());
        } else {
            System.out.println("Такой счет не найден");
            return;
        }
    }

    public static void closeAccount(Scanner scanner){
        User user = UserService.currentUser();
        if (user == null) {
            System.out.println("Ошибка: Требуется авторизация для закрытия счета.");
            return;
        }
        List<Account> userAccounts = AccountRepository.viewAllAccountsUser();
        if (userAccounts == null || userAccounts.isEmpty()) {
            return;
        }
        System.out.println("Выберите счет: ");
        String accountIdString = scanner.nextLine();
        UUID accountId = UUID.fromString(accountIdString);
        Account account = accounts.get(accountId);
        if (account != null) {
            if (account.getBalance() > 0) {
                System.out.println("Нельзя закрыть счет с положительным балансом. Снимите деньги со счета.");
            } else {
                accounts.remove(accountId);
                System.out.println("Счет успешно закрыт");
            }
        } else {
            System.out.println("Такой счет не найден");
            return;
        }
    }



    // Перевод денег между счетами - проверяет что валюта счетов одинаковая.
    public static void transferMoney(User user, UUID sourceAccountId, UUID targetAccountId, double amount) {
        if (UserService.currentUser() == null || !UserService.currentUser().equals(user)) {
            System.out.println("Ошибка: Требуется авторизация для выполнения операции.");
            return;
        }
        Account sourceAccount = accounts.get(sourceAccountId);
        Account targetAccount = accounts.get(targetAccountId);

        if (sourceAccount == null || targetAccount == null) {
            System.out.println("Ошибка: Один из счетов не найден.");
            return;
        }

        // Проверка валюты счетов
        if (sourceAccount.getCurrencyAccount() != targetAccount.getCurrencyAccount()) {
            System.out.println("Ошибка: Нельзя выполнить транзакцию между счетами с разными валютами.");
            return;
        }

        if (sourceAccount.getBalance() < amount) {
            System.out.println("Ошибка: Недостаточно средств на счете для выполнения операции.");
            return;
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        Transaction debitTransaction = new Transaction(Transaction.Type.DEBIT, amount, LocalDateTime.now());
        Transaction creditTransaction = new Transaction(Transaction.Type.CREDIT, amount, LocalDateTime.now());

        TransactionRepository.addTransaction(debitTransaction);
        TransactionRepository.addTransaction(creditTransaction);

        System.out.println("Перевод выполнен успешно.");

    }

}

