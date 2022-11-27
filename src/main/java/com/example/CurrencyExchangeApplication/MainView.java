package com.example.CurrencyExchangeApplication;

import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRatesAllList;
import com.example.CurrencyExchangeApplication.NbpClient.NBPClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

@Controller public class MainView {
    private final NBPClient clientNBP;
    private CurrencyRatesAllList currencyRateList;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public MainView() {
        this.clientNBP = new NBPClient();
        this.currencyRateList = new CurrencyRatesAllList();
    }

    @GetMapping("/") public String MainView(ModelMap modelMap) {
        try {
            currencyRateList = clientNBP.getCurrencyExchangeRate();

        } catch (IOException e) {
            System.out.println("Nie udało się pobrać dane");
        }

        Currency selectGet = Currency.USD;
        Currency selectHave = Currency.EUR;
        Double currencyValueHave = 1.0;
        double calculation = getCalculation(selectGet, selectHave, currencyValueHave);
        modelMap.put("calculation",df.format(calculation));
        modelMap.put("lastBalance",df.format(calculation));
        return "mainView";
    }

    private double getCalculation(final Currency getCurrency, final Currency haveCurrency, final Double currencyValueHave) {

        Optional<CurrencyRate> getCurrencyRate = getCurrencyRate(getCurrency);
        Optional<CurrencyRate> haveCurrencyRate = getCurrencyRate(haveCurrency);

        if (getCurrency.equals(haveCurrency)) {
            return currencyValueHave;
        }

        if (haveCurrency.equals(Currency.PLN)) {
            if (getCurrencyRate.map(CurrencyRate::getBuy).isPresent()) {
                return currencyValueHave * getCurrencyRate.map(CurrencyRate::getBuy).get();
            }
        }

        if (getCurrency.equals(Currency.PLN)) {
            if (haveCurrencyRate.map(CurrencyRate::getSell).isPresent()) {
                return currencyValueHave * haveCurrencyRate.map(CurrencyRate::getSell).get();
            }
        }

        double calculation = getCurrencyRate.map(CurrencyRate::getBuy).get() / haveCurrencyRate.map(CurrencyRate::getSell).get();

        calculation = calculation * currencyValueHave;
        return calculation;

    }

    private Optional<CurrencyRate> getCurrencyRate(final Currency currency) {
        return currencyRateList.getCurrencyRate().stream().filter(x -> x.getCurrency().equals(currency)).findFirst();
    }
}
