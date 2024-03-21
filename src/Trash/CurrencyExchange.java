package Trash;

import java.util.HashMap;
import java.util.Map;
// Класс для обработки операций обмена валюты
public class CurrencyExchange {
    private Map<String, Double> exchangeRates;

    // Конструктор класса
    public CurrencyExchange() {
        this.exchangeRates = new HashMap<>();
    }

    // Метод для установки курса обмена между двумя валютами
    public void setExchangeRate(ExchangeRate.Type sourceCurrency, ExchangeRate.Type targetCurrency, double rate) {
        String key = sourceCurrency + "_" + targetCurrency;
        exchangeRates.put(key, rate);
    }

    // Метод для получения курса обмена между двумя валютами
    public double getExchangeRate(ExchangeRate.Type sourceCurrency, ExchangeRate.Type targetCurrency) {
        String key = sourceCurrency + "_" + targetCurrency;
        return exchangeRates.getOrDefault(key, 0.0);
    }

    // Метод для расчета конечной суммы после обмена
    public double calculateExchangeAmount(double amount, ExchangeRate.Type sourceCurrency, ExchangeRate.Type targetCurrency) {
        double rate = getExchangeRate(sourceCurrency, targetCurrency);
        return amount * rate;
    }

// Другие методы, связанные с обменом валюты, могут быть добавлены по мере необходимости
}