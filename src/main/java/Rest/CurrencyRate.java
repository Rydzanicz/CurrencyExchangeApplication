package Rest;

import java.time.LocalDate;
import java.util.Optional;

public class CurrencyRate {
    private final Currency currency;
    private Optional<LocalDate> exchangeRateDate;
    private Optional<Double> buy;
    private Optional<Double> sell;
    private Optional<Double> medium;

    public CurrencyRate(Currency currency, Optional<LocalDate> exchangeRateDate, Optional<Double> buy, Optional<Double> sell, Optional<Double> medium) {
        this.currency = currency;
        this.exchangeRateDate = exchangeRateDate;
        this.buy = buy;
        this.sell = sell;
        this.medium = medium;
    }

    public CurrencyRate(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setExchangeRateDate(String exchangeRateDate) {
        this.exchangeRateDate = Optional.ofNullable(LocalDate.parse(exchangeRateDate));
    }

    public Optional<LocalDate> getExchangeRateDate() {
        return exchangeRateDate;
    }

    public void setBuy(Optional<Double> buy) {
        this.buy = buy;
    }

    public Optional<Double> getBuy() {
        return buy;
    }

    public void setSell(Optional<Double> sell) {
        this.sell = sell;
    }

    public Optional<Double> getSell() {
        return sell;
    }

    public void setMedium(Optional<Double> medium) {
        this.medium = medium;
    }

    public Optional<Double> getMedium() {
        return medium;
    }

    public String toString() {
        return "Currency: " + currency + "ExchangeRateDate: " + exchangeRateDate + "Buy: " + buy + "Sell: " + sell + "Medium: " + medium;
    }
}


