package model;

import java.time.LocalDateTime;

public class ExchangeRateEntry {
    private double exchangeRate;
    private LocalDateTime dateTime;

    public ExchangeRateEntry(double exchangeRate, LocalDateTime dateTime) {
        this.exchangeRate = exchangeRate;
        this.dateTime = dateTime;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "ExchangeRateEntry{" +
                "exchangeRate=" + exchangeRate +
                ", dateTime=" + dateTime +
                '}';
    }
}
