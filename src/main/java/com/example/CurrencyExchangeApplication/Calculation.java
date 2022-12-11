package com.example.CurrencyExchangeApplication;

import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRatesAllList;
import com.example.CurrencyExchangeApplication.NbpClient.NBPClient;

import java.io.IOException;
import java.util.Optional;

public class Calculation {
    private final Currency selectHaveCurrency;
    private final Currency selectGetCurrency;
    private final Double amount;
    private final Double commission;
    private final NBPClient clientNBP;
    private CurrencyRatesAllList currencyRateList;

    public Calculation(final Currency selectHaveCurrency, final Currency selectGetCurrency, final Double amount, final Double commission) {
        this.selectGetCurrency = selectGetCurrency;
        this.selectHaveCurrency = selectHaveCurrency;
        this.amount = amount;
        this.commission = commission;
        this.clientNBP = new NBPClient();
        this.currencyRateList = new CurrencyRatesAllList();
    }

    public double getCalculation() {
        try {
            currencyRateList = clientNBP.getCurrencyExchangeRate();

        } catch (IOException e) {
            System.out.println("Nie udało się pobrać dane");
        }

        Optional<CurrencyRate> getCurrencyRate = getCurrencyRate(this.selectGetCurrency);
        Optional<CurrencyRate> haveCurrencyRate = getCurrencyRate(this.selectHaveCurrency);

        if (selectGetCurrency.equals(selectHaveCurrency)) {
            return amount;
        }

        if (selectHaveCurrency.equals(Currency.PLN) && getCurrencyRate.map(CurrencyRate::getBuy).isPresent()) {
            double currencyRat = getCurrencyRate.map(CurrencyRate::getBuy).get();
            double commissionLocal = calculationCommission(currencyRat);
            return amount * currencyRat - commissionLocal;

        }

        if (selectGetCurrency.equals(Currency.PLN) && haveCurrencyRate.map(CurrencyRate::getSell).isPresent()) {
            double currencyRat = haveCurrencyRate.map(CurrencyRate::getSell).get();
            double commissionLocal = calculationCommission(currencyRat);
            return amount * currencyRat - commissionLocal;
        }

        double currencyRat = getCurrencyRate.map(CurrencyRate::getBuy).get() / haveCurrencyRate.map(CurrencyRate::getSell).get();
        double commissionLocal = calculationCommission(currencyRat);
        return amount * currencyRat - commissionLocal;
    }

    private double calculationCommission(final double currencyRat) {
        return currencyRat * amount * commission;
    }

    private Optional<CurrencyRate> getCurrencyRate(final Currency currency) {
        return currencyRateList.getCurrencyRate().stream().filter(x -> x.getCurrency().equals(currency)).findFirst();
    }

    public NBPClient getClientNBP() {
        return clientNBP;
    }

    public Double getCommission() {
        return commission;
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getSelectGetCurrency() {
        return selectGetCurrency;
    }

    public Currency getSelectHaveCurrency() {
        return selectHaveCurrency;
    }
}
