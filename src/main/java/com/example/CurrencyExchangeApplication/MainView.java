package com.example.CurrencyExchangeApplication;

import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.NBPClient;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
        TextField value = new TextField("Ilość");

        try {
            currencyRate = clientNBP.getCurrencyExchangeRateBuyAndSell(currencyRate);
        } catch (IOException e) {
            System.out.println("Nie udało się pobrać dane");
        }
        add(new Span("Waluta: "));
        add(new Span(currencyRate.getCurrency().toString()));
        add(new Hr());
        add(String.valueOf(value));

    }
}
