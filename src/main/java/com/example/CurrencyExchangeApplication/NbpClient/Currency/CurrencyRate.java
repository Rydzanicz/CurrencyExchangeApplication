package com.example.CurrencyExchangeApplication.NbpClient.Currency;

import java.time.LocalDate;
import java.util.Optional;

public class CurrencyRate {
    private final Currency currency;
    private Optional<LocalDate> exchangeRateDateForBuyAndSell;
    private Optional<LocalDate> exchangeRateDateForMid;
    private Optional<Double> buy;
    private Optional<Double> sell;
    private Optional<Double> medium;

    public CurrencyRate(Currency currency,
                        Optional<LocalDate> exchangeRateDateForBuyAndSell,
                        Optional<Double> buy,
                        Optional<Double> sell,
                        Optional<LocalDate> exchangeRateDateForMid,
                        Optional<Double> medium)
        {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = exchangeRateDateForBuyAndSell;
        this.exchangeRateDateForMid = exchangeRateDateForMid;
        this.buy = buy;
        this.sell = sell;
        this.medium = medium;
        }

    public CurrencyRate(Currency currency)
        {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = Optional.empty();
        this.exchangeRateDateForMid = Optional.empty();
        this.buy = Optional.empty();
        this.sell = Optional.empty();
        this.medium = Optional.empty();

        }

    public Currency getCurrency()
        {
        return currency;
        }

    public void setBuy(Optional<Double> buy)
        {
        this.buy = buy;
        }

    public Optional<Double> getBuy()
        {
        return buy;
        }

    public void setSell(Optional<Double> sell)
        {
        this.sell = sell;
        }

    public Optional<Double> getSell()
        {
        return sell;
        }

    public void setMedium(Optional<Double> medium)
        {
        this.medium = medium;
        }

    public Optional<Double> getMedium()
        {
        return medium;
        }

    public void setExchangeRateDateForBuyAndSell(String exchangeRateDate)
        {
        this.exchangeRateDateForBuyAndSell = Optional.ofNullable(LocalDate.parse(exchangeRateDate));
        }

    public Optional<LocalDate> getExchangeRateDateForBuyAndSell()
        {
        return exchangeRateDateForBuyAndSell;
        }

    public void setExchangeRateDateForMid(String exchangeRateDate)
        {
        this.exchangeRateDateForMid = Optional.ofNullable(LocalDate.parse(exchangeRateDate));
        }

    public Optional<LocalDate> getExchangeRateDateForMid()
        {
        return exchangeRateDateForMid;
        }


    public String toString()
        {
        String result = "\nCurrency: " + currency + "\n";
        if(exchangeRateDateForBuyAndSell.isPresent())
        {
            result += "ExchangeRateDateForBuyAndSell: " + exchangeRateDateForBuyAndSell.get() + "  ";
        }

        if(buy.isPresent())
        {
            result += "Buy: " + buy.get() + "  ";
        }

        if(sell.isPresent())
        {
            result += "Sell: " + sell.get() + "\n";
        }

        if(exchangeRateDateForMid.isPresent())
        {
            result += "ExchangeRateDateForMid: " + exchangeRateDateForMid.get() + "  ";

        }
        if(medium.isPresent())
        {
            result += "Medium: " + medium.get();
        }
        return result;
        }
}


