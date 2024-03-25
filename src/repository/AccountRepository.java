package repository;


import model.Account;
import model.User;
import service.UserService;

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
        System.out.println("Новый счет успешно создан: " + newAccount);

        // Сохраняем счет в репозитории
        saveAccount(newAccount);

        return newAccount;
    }

    // Метод для сохранения счета в репозитории
    public static void saveAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

//    public static List<Account> getAllAccounts() {
//        List<Account> allAccounts = new ArrayList<>();
//        for (User user : users.values()) {
//            allAccounts.addAll(user.getAccounts());
//        }
//        return allAccounts;
//    }

    // Открытые счета у пользователя
//    public static void viewAllAccounts() {
//        User currentUser = UserService.currentUser(); // Получаем текущего авторизованного пользователя
//
//        if (currentUser != null) {
//            boolean hasAccounts = false;
//
//            // Проходим по всем счетам в репозитории
//            for (Account account : AccountRepository.getAllAccounts()) {
//                // Проверяем, совпадает ли владелец счета с текущим пользователем
//                if (account.getUser().equals(currentUser)) {
//                    System.out.println(account);
//                    hasAccounts = true;
//                }
//            }
//
//            if (!hasAccounts) {
//                System.out.println("У вас еще нет открытых счетов.");
//            }
//        } else {
//            System.out.println("Вы не авторизованы.");
//        }
//    }

//    private static final Map<Integer, Account> accounts = new HashMap<>();

//    // Метод для сохранения нового счета в репозитории
//    public static void saveAccount(Account account) {
//        accounts.put(account.getAccountId(), account);
//    }
//
//    // Метод для поиска счета по его идентификатору
//    public static Account findAccountById(int accountId) {
//        return accounts.get(accountId);
//    }
//
//    // Метод для удаления счета из репозитория
//    public static void deleteAccount(int accountId) {
//        accounts.remove(accountId);
//    }
//
//    // Метод для получения всех счетов из репозитория
//    public static Map<Integer, Account> getAllAccounts() {
//        return accounts;
//    }
}

