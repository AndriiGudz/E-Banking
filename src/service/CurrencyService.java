package service;

import model.Currency;
import model.CurrencyCode;
import model.ExchangeRateEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyService {
    private static Map<CurrencyCode, Currency> currencies = new HashMap<>();

    // Метод для добавления новой валюты
    public static void addCurrency(CurrencyCode code, double exchangeRate) {
        currencies.put(code, new Currency(code, exchangeRate));
    }

    // Метод для изменения курса валюты // TODO добавить проверку - может делать только администратор
    public static void updateExchangeRate(CurrencyCode code, double newExchangeRate, LocalDateTime dateTime) {
        Currency currency = currencies.get(code);
        if (currency != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            currency.setExchangeRate(newExchangeRate, LocalDateTime.now()); // Передаем новый курс и дату и время изменения
            String formattedDateTime = dateTime.format(formatter);
            System.out.println("Курс валюты " + code + " успешно обновлен на " + newExchangeRate + " " + formattedDateTime);
        } else {
            System.out.println("Валюта " + code + " не найдена.");
        }
    }

    // Метод для вывода списка всех валют с историей изменения курса
    public static void displayAllCurrencies() {
        System.out.println("Список всех валют:");
        for (Currency currency : currencies.values()) {
            List<ExchangeRateEntry> history = currency.getExchangeRateHistory();
            ExchangeRateEntry lastEntry = history.get(history.size() - 1); // Получаем последнюю запись обменного курса
            double currentExchangeRate = lastEntry.getExchangeRate(); // Получаем текущий обменный курс
            System.out.println(currency.getCode() + ": текущий курс - " + currentExchangeRate);
        }
    }

    // Метод для вывода истории изменения курса валюты
    public static void displayExchangeRateHistory(CurrencyCode code) {
        Currency currency = currencies.get(code);
        if (currency != null) {
            List<ExchangeRateEntry> history = currency.getExchangeRateHistory();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            System.out.println("История изменения курса валюты " + code + ":");
            for (ExchangeRateEntry entry : history) {
                String formattedDateTime = entry.getDateTime().format(formatter);
                System.out.println("Дата и время: " + formattedDateTime + ", Курс: " + entry.getExchangeRate());
            }
        } else {
            System.out.println("Валюта " + code + " не найдена.");
        }
    }

    private static void initCurrencyAndRate() {
        // Добавление валют с начальными обменными курсами
        CurrencyService.addCurrency(CurrencyCode.USD, 1.0); // Для примера, предположим, что USD - это базовая валюта, поэтому курс равен 1
        CurrencyService.addCurrency(CurrencyCode.EUR, 1.12); // Например, 1 USD = 1.12 EUR
        CurrencyService.addCurrency(CurrencyCode.GBP, 1.33); // Например, 1 USD = 1.33 GBP
        CurrencyService.addCurrency(CurrencyCode.PLN, 0.26); // Например, 1 USD = 0.26 PLN
        CurrencyService.addCurrency(CurrencyCode.CZK, 0.044); // Например, 1 USD = 0.044 CZK
    }
}
