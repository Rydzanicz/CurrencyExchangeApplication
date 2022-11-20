package com.example.CurrencyExchangeApplication.NbpClient.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesAllList {

    private final List<CurrencyRate> currencyRate;

    public CurrencyRatesAllList(List<CurrencyRate> currencyRates) {
        this.currencyRate = currencyRates;
    }

    public CurrencyRatesAllList() {

        this.currencyRate = new ArrayList<>();
    }

    public List<CurrencyRate> getCurrencyRate() {
        return currencyRate;
    }

    public void addCurrencyRate(CurrencyRate currencyRate) {
        this.currencyRate.add(currencyRate);
    }


    public String toString() {
        String result = "";
        if (!currencyRate.isEmpty()) {
            result += currencyRate.stream();
        }

        return result;
    }
}


