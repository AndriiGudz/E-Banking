package model;

public enum CurrencyCode {
    USD("USD", "US Dollar"),
    EUR("EUR", "Euro"),
    GBP("GBP", "British Pound"),
    PLN("PLN", "Polish Zloty"),
    CZK("CZK", "Czech Koruna"),
    ;

    private final String code;
    private final String name;

    CurrencyCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}