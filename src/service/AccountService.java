package service;

import model.Account;
import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private static final Map<Integer, Account> accounts = new HashMap<>();

    // Метод для пополнения счета в выбранной валюте с учетом комиссии
    public static void deposit(Account account, double amount) {
        // Вычисляем сумму, которую нужно зачислить на счет с учетом комиссии
        double commission = calculateCommission(amount);

        double amountToDeposit = amount - commission;

        // Пополняем счет
        account.setBalance(account.getBalance() + amountToDeposit);

        System.out.println("Счет успешно пополнен на " + amountToDeposit + " " + account.getCurrencyAccount() + ".");
    }

    // Метод для расчета комиссии
    private static double calculateCommission(double amount) {
        return amount * 0.01; // Комиссия в размере 1% от суммы операции
    }

    // Метод для снятия средств со счета
    public static void withdraw(Account account, double amount) {
//        double commission = calculateCommission(amount); // Рассчитываем комиссию
//
//        if (account.getBalance() >= (amount + commission)) {
//            account.setBalance(account.getBalance() - (amount + commission)); // Учитываем комиссию
//            account.getTransactionHistory().add(new Transaction("withdraw", account.getCurrency(), amount));
//            System.out.println("Со счета успешно снято " + amount + " " + account.getCurrency() + " с учетом комиссии.");
//        } else {
//            System.out.println("Недостаточно средств на счете.");
//        }
    }

    // Метод для получения текущего баланса счета пользователя
    public static double getAccountBalance(Account account) {
        return account.getBalance();
    }

    // Метод для просмотра истории операций для конкретного счета

//    public static List<Transaction> getTransactionHistory(User user) {
//        List<Transaction> transactionHistory = new ArrayList<>();
//        for (Object account : user.getAccounts()) {
//            if (account instanceof Account) {
//                transactionHistory.addAll(((Account) account).getTransactionHistory());
//            }
//        }
//        return transactionHistory;
//    }

    //метод проверяет остаток средств на счете. Если остаток равен 0, счет закрывается.
    // Если остаток больше 0, выводится сообщение о необходимости снять все средства сначала.
    // Если остаток отрицательный, выводится сообщение о наличии задолженности, которую нужно погасить перед закрытием счета.
    public static void closeAccount(Account account) {
        double balance = account.getBalance();
        if (balance == 0) {
            accounts.remove(account.getAccountId());
            System.out.println("Счет успешно закрыт.");
        } else if (balance > 0) {
            System.out.println("На счету еще остаток. Пожалуйста, сначала снимите все средства.");
        } else {
            System.out.println("На счету имеется задолженность. Пожалуйста, погасите ее перед закрытием счета.");
        }
    }

    // Метод для перевода средств с одного счета на другой
    public static void transfer(Account recipient, double amount, double balance) throws InterruptedException {
        if (balance >= amount) {
            balance -= amount;
            recipient.wait((long) amount);
        } else {
            System.out.println("Недостаточно средств.");
        }
    }

    // Метод для просмотра истории операций пользователя администратору


    // будет принимать пользователя, перебирает все его счета и добавляет историю операций каждого
    // счета в общий список истории операций пользователя и возвращает историю операций для всех его счетов
//    public static List<Transaction> getUserTransactionHistory(User user) {
//        List<Transaction> userTransactionHistory = new ArrayList<>();
//        for (Object accountObj : user.getAccounts()) {
//            if (accountObj instanceof Account) {
//                Account account = (Account) accountObj;
//                userTransactionHistory.addAll(account.getTransactionHistory());
//            }
//        }
//        return userTransactionHistory;
//    }
}
