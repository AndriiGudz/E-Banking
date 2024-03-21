package Trash;

public class Currency {
    public enum Type {EUR, USD, UAH}

    private Type currencyType; // тип валюты
    private double exchangeRate; // курс обмена

    // полное название валюты сделать





    // Конструктор класса
    public Currency(Type currencyType, double exchangeRate) {
        this.currencyType = currencyType;
        this.exchangeRate = exchangeRate;
    }

    // Геттеры и сеттеры для всех полей
    public Type getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Type currencyType) {
        this.currencyType = currencyType;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}