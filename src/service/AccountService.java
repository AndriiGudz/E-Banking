package service;

import model.Account;

import java.util.HashMap;
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

    // Метод проверяет остаток средств на счете. Если остаток равен 0, счет закрывается.
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

}
