package model;
// Класс представляет курс обмена между различными валютами
public class ExchangeRate {
    public enum Type {EUR, USD, UAH}
    private Type sourceCurrency; // с какой валюты меняю
    private Type targetCurrency; // на какую меняют
    private double rate; // курс обмена

    // Конструктор класса
    public ExchangeRate(Type sourceCurrency, Type targetCurrency, double rate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    // Геттеры и сеттеры для всех полей
    public Type getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Type sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Type getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Type targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}