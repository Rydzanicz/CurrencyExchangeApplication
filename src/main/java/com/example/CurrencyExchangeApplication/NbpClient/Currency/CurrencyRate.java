package com.example.CurrencyExchangeApplication.NbpClient.Currency;

import java.time.LocalDate;

public class CurrencyRate {
    private final Currency currency;
    private LocalDate exchangeRateDateForBuyAndSell;
    private Double buy;
    private Double sell;
    private LocalDate exchangeRateDateForMid;
    private Double medium;

    public CurrencyRate(Currency currency,
                        LocalDate exchangeRateDateForBuyAndSell,
                        Double buy,
                        Double sell,
                        LocalDate exchangeRateDateForMid,
                        Double medium) {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = exchangeRateDateForBuyAndSell;
        this.exchangeRateDateForMid = exchangeRateDateForMid;
        this.buy = buy;
        this.sell = sell;
        this.medium = medium;
    }

    public CurrencyRate(String currency,
                        LocalDate exchangeRateDateForBuyAndSell,
                        Double buy,
                        Double sell,
                        LocalDate exchangeRateDateForMid,
                        Double medium) {
        this.currency = Currency.valueOf(currency);
        this.exchangeRateDateForBuyAndSell = exchangeRateDateForBuyAndSell;
        this.exchangeRateDateForMid = exchangeRateDateForMid;
        this.buy = buy;
        this.sell = sell;
        this.medium = medium;


    }

    public CurrencyRate() {
        this.currency = null;
        this.exchangeRateDateForBuyAndSell = null;
        this.exchangeRateDateForMid = null;
        this.buy = null;
        this.sell = null;
        this.medium = null;


    }

    public CurrencyRate(Currency currency) {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = null;
        this.exchangeRateDateForMid = null;
        this.buy = null;
        this.sell = null;
        this.medium = null;

    }

    public Currency getCurrency() {
        return currency;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getBuy() {
        return buy;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getSell() {
        return sell;
    }

    public void setMedium(Double medium) {
        this.medium = medium;
    }

    public Double getMedium() {
        return medium;
    }

    public void setExchangeRateDateForBuyAndSell(String exchangeRateDate) {
        this.exchangeRateDateForBuyAndSell = LocalDate.parse(exchangeRateDate);
    }

    public LocalDate getExchangeRateDateForBuyAndSell() {
        return exchangeRateDateForBuyAndSell;
    }

    public void setExchangeRateDateForMid(String exchangeRateDate) {
        this.exchangeRateDateForMid = LocalDate.parse(exchangeRateDate);
    }

    public LocalDate getExchangeRateDateForMid() {
        return exchangeRateDateForMid;
    }


    public String toString() {
        String result = "\nCurrency: " + currency + "\n";
        result += "ExchangeRateDateForBuyAndSell: " + exchangeRateDateForBuyAndSell + "  ";

        result += "Buy: " + buy + "  ";

        result += "Sell: " + sell + "\n";

        result += "ExchangeRateDateForMid: " + exchangeRateDateForMid + "  ";

        result += "Medium: " + medium;
        return result;
    }
}


