package Trash;


import model.Account;
import model.CurrencyCode;
import model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

import static service.AccountService.deposit;
import static service.AccountService.withdraw;


public class CurrencyExchangeService {
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    private static final Map<String, List<Double>> exchangeRatesHistory = new HashMap<>();
    private static final Set<CurrencyCode> availableCurrencies = new HashSet<>();


    // Метод для добавления курса обмена валюты
//    public static void addExchangeRate(CurrencyCode baseCurrency, CurrencyCode targetCurrency, double rate) {
//        String key = baseCurrency.getCode() + "-" + targetCurrency.getCode();
//        exchangeRates.put(key, rate);
//        recordExchangeRateHistory(key, rate);
//        availableCurrencies.add(baseCurrency);
//        availableCurrencies.add(targetCurrency);
//    }

    // Метод для выполнения операции обмена валюты
    public static void exchangeCurrency(Account sourceAccount, Account targetAccount, double amount, double exchangeRate, double commission) {
        // Вычисляем сумму для обмена с учетом комиссии
        double amountToExchange = amount - commission;
        double exchangedAmount = amountToExchange * exchangeRate;

        // Проверяем, достаточно ли средств на счете для обмена
        if (sourceAccount.getBalance() >= amountToExchange) {
            // Снимаем сумму в исходной валюте с аккаунта
            withdraw(sourceAccount, amountToExchange);
            // Пополняем счет в целевой валюте
            deposit(targetAccount, exchangedAmount);
            System.out.println("Обмен валюты успешно выполнен.");
        } else {
            System.out.println("Недостаточно средств на счете для выполнения обмена.");
        }
    }

    // Метод для получения курса обмена валюты по заданным валютам
//    public static Double getExchangeRate(CurrencyCode baseCurrency, CurrencyCode targetCurrency) {
//        String key = baseCurrency.getCode() + "-" + targetCurrency.getCode();
//        return exchangeRates.get(key);
//    }

    // Метод для записи истории курса обмена валюты(записывает новые курсы обмена)
//    private static void recordExchangeRateHistory(String key, double rate) {
//        List<Double> rates = exchangeRatesHistory.getOrDefault(key, new ArrayList<>());
//        rates.add(rate);
//        exchangeRatesHistory.put(key, rates);
//    }

    // Метод для получения истории курсов по валюте(возвращает историю курсов для заданной пары валют)
//    public static List<Double> getExchangeRatesHistory(CurrencyCode baseCurrency, CurrencyCode targetCurrency) {
//        String key = baseCurrency.getCode() + "-" + targetCurrency.getCode();
//        return exchangeRatesHistory.getOrDefault(key, new ArrayList<>());
//    }

    // Метод для изменения курса обмена валюты администратором
//    public static void updateExchangeRate(CurrencyCode baseCurrency, CurrencyCode targetCurrency, double newRate) {
//        String key = baseCurrency.getCode() + "-" + targetCurrency.getCode();
//        if (exchangeRates.containsKey(key)) {
//            exchangeRates.put(key, newRate);
//            recordExchangeRateHistory(key, newRate);
//        } else {
//            throw new IllegalArgumentException("Курс обмена для указанных валют не найден.");
//        }
//    }

    // если попытаться обновить или удалить курс обмена для валют, курс которых не существует,
    // будет выброшено исключение IllegalArgumentException,
    // что поможет идентифицировать проблему сразу и обработать ее соответствующим образом в вашем коде

    // Метод для удаления курса обмена валюты
//    public static void removeExchangeRate(CurrencyCode baseCurrency, CurrencyCode targetCurrency) {
//        String key = baseCurrency.getCode() + "-" + targetCurrency.getCode();
//        if (exchangeRates.containsKey(key)) {
//            exchangeRates.remove(key);
//            exchangeRatesHistory.remove(key);
//        } else {
//            throw new IllegalArgumentException("Курс обмена для указанных валют не найден.");
//        }
//    }

    // метод для получения списка доступных валют
//        public static Set<CurrencyCode> getAvailableCurrencies() {
//            return availableCurrencies;
//        }

    // Метод для просмотра статистики операций по конкретной валюте
    public static List<Transaction> getCurrencyTransactionStatistics(List<Transaction> allTransactions, CurrencyCode currencyCode) {
        // Фильтруем операции по заданной валюте
        return allTransactions.stream()
                .filter(transaction -> transaction.getCurrencyCode() == currencyCode)
                .collect(Collectors.toList());
    }

}