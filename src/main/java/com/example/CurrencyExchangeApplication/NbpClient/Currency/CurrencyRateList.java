package com.example.CurrencyExchangeApplication.NbpClient.Currency;

import java.util.List;

public class CurrencyRateList {
    private final Currency currency;
    private final List<CurrencyRate> currencyRate;

    public CurrencyRateList(Currency currency, List<CurrencyRate> currencyRates) {
        this.currency = currency;
        this.currencyRate = currencyRates;
    }

    public void addCurrencyRate(CurrencyRate currencyRate) {
        this.currencyRate.add(currencyRate);
    }

    public Currency getCurrency() {
        return currency;
    }

    public String toString() {
        String result = "Currency: " + currency + "\n";
        if (!currencyRate.isEmpty()) {
            result += currencyRate.toString();
        }

        return result;
    }
}


