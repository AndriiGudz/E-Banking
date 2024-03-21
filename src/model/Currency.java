package model;

public class Currency {
    private CurrencyCode code;

    public Currency(CurrencyCode code) {
        this.code = code;
    }

    public CurrencyCode getCode() {
        return code;
    }

}