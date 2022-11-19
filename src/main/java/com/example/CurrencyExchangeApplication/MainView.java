package com.example.CurrencyExchangeApplication;

import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.NBPClient;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.time.LocalDate;

@Route("") public class MainView extends VerticalLayout {
    private final NBPClient clientNBP;
    private final LocalDate localDate;

    public MainView() {
        clientNBP = new NBPClient();
        localDate = LocalDate.now().minusDays(2);

        showCalculator();
    }

    private void showCalculator() {
        CurrencyRate currencyRate = new CurrencyRate(Currency.USD);

        try {
            currencyRate = clientNBP.getCurrencyExchangeRateBuyAndSell(currencyRate);
        } catch (IOException e) {
            System.out.println("Nie udało się pobrać dane");
        }
        add(new H1(currencyRate.toString()));

    }
}
