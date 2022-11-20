package com.example.CurrencyExchangeApplication;

import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRatesAllList;
import com.example.CurrencyExchangeApplication.NbpClient.NBPClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Route("") public class MainView extends VerticalLayout {
    private final NBPClient clientNBP;
    private final LocalDate localDate;

    public MainView() {
        clientNBP = new NBPClient();
        localDate = LocalDate.now().minusDays(2);

        showCalculator();
    }

    private void showCalculator() {
        CurrencyRatesAllList currencyRateList = new CurrencyRatesAllList();

        try {
            currencyRateList = clientNBP.getNewCurrencyExchangeAll(currencyRateList);

        } catch (IOException e) {
            System.out.println("Nie udało się pobrać dane");
        }
        var button = new Button("Sprawdź kurs");
        var currencyHave = new TextField("Waluta");
        var currencyHaveSelected = new TextField("");
        var currencyGet = new TextField("Waluta");
        var currencyGetSelected = new TextField("");
        Select<String> selectHave = new Select<>();
        selectHave.setLabel("Mam");
        selectHave.setItems(Currency.EUR.toString(),
                            Currency.USD.toString(),
                            Currency.GBP.toString(),
                            Currency.CHF.toString(),
                            Currency.JPY.toString(),
                            Currency.PLN.toString());
        selectHave.setValue(Currency.PLN.toString());
        add(new HorizontalLayout(selectHave, currencyHave, button));
        Select<String> selectGet = new Select<>();
        selectGet.setLabel("Dostane");
        selectGet.setItems(Currency.EUR.toString(),
                           Currency.USD.toString(),
                           Currency.GBP.toString(),
                           Currency.CHF.toString(),
                           Currency.JPY.toString(),
                           Currency.PLN.toString());
        selectGet.setValue(Currency.EUR.toString());
        add(new HorizontalLayout(selectGet, currencyGet));
        Optional<CurrencyRate> finalCurrencyRate1 = currencyRateList.getCurrencyRate()
                                                                    .stream()
                                                                    .filter(x -> x.getCurrency().equals(Currency.GBP))
                                                                    .findFirst();

        button.addClickListener(e -> {
            currencyHaveSelected.setValue(selectHave.getValue());
            currencyGetSelected.setValue(selectGet.getValue());

            add(new Paragraph(currencyHaveSelected.getValue()));
            add(new Paragraph(finalCurrencyRate1.toString()));
        });
    }

    private void showCurrencyChart() {
        Image image = new Image("https://comparic.xyz/uploads/2019/03/Kursy-waluty-19-marca-2019-3.png", "anatomy of an image");
        image.setMaxHeight("1000");
        image.setMaxHeight("500");
        add(image);

    }
}
