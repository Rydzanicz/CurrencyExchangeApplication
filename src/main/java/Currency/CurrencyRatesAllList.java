package Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesAllList {

    private List<CurrencyRate> currencyRate;

    public CurrencyRatesAllList(Currency currency, List<CurrencyRate> currencyRates) {
        this.currencyRate = currencyRates;
    }

    public CurrencyRatesAllList(Currency currency) {

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


    public String toString() {
        String result = "";
        if (!currencyRate.isEmpty()) {
            result += currencyRate.stream();
        }

        return result;
    }
}


