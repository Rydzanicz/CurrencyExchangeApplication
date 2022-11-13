package Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRateList {
    private final Currency currency;
    private List<CurrencyRate> currencyRate;

    public CurrencyRateList(Currency currency, List<CurrencyRate> currencyRates) {
        this.currency = currency;
        this.currencyRate = currencyRates;
    }

    public CurrencyRateList(Currency currency) {

        this.currency = currency;
        this.currencyRate = new ArrayList<>();
    }

    public List<CurrencyRate> getCurrencyRate() {
        return currencyRate;
    }

    public void addCurrencyRate(CurrencyRate currencyRate) {
        for (int x = 0; x < this.currencyRate.size(); x++) {

        }
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


