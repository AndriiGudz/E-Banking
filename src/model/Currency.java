package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Currency {
    private CurrencyCode code;
    private double exchangeRate;
    private List<ExchangeRateEntry> exchangeRateHistory; // История изменения курса валюты

    public Currency(CurrencyCode code, double exchangeRate) {
        this.code = code;
        this.exchangeRateHistory = new ArrayList<>();
        this.exchangeRateHistory.add(new ExchangeRateEntry(exchangeRate, LocalDateTime.now())); // Добавляем начальное значение курса в историю
    }

    public CurrencyCode getCode() {
        return code;
    }

    public List<ExchangeRateEntry> getExchangeRateHistory() {
        return exchangeRateHistory;
    }

    public void setExchangeRate(double exchangeRate, LocalDateTime dateTime) {
        this.exchangeRateHistory.add(new ExchangeRateEntry(exchangeRate, dateTime)); // Добавляем новое значение курса в историю
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code=" + code +
                ", exchangeRateHistory=" + exchangeRateHistory +
                '}';
    }
}