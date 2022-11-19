package com.example.CurrencyExchangeApplication;

import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.NBPClient;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.time.LocalDate;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() throws IOException
        {
        NBPClient clientNBP = new NBPClient();
        LocalDate localDate = LocalDate.now().minusDays(2);
        CurrencyRate currencyRate = new CurrencyRate(Currency.USD);
        currencyRate = clientNBP.getCurrencyExchangeRateBuyAndSell(currencyRate);
        add(new H1(currencyRate.toString()));
        }
}
