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

@Route("")
public class MainView extends VerticalLayout {
    private final NBPClient clientNBP;
    private final LocalDate localDate;
    private CurrencyRatesAllList currencyRateList;
    private final Button button = new Button("Sprawdź kurs");
    private final TextField currencyHave = new TextField("Waluta");
    private final TextField currencyHaveSelected = new TextField("");
    private final TextField currencyGet = new TextField("Waluta");
    private final TextField currencyGetSelected = new TextField("");
    private Select<String> selectHave = new Select<>();
    private Select<String> selectGet = new Select<>();

    public MainView() {
        clientNBP = new NBPClient();
        localDate = LocalDate.now().minusDays(2);
        currencyRateList = new CurrencyRatesAllList();

        selectHave.setLabel("Mam");
        selectHave.setItems(Currency.EUR.toString(),
                Currency.USD.toString(),
                Currency.GBP.toString(),
                Currency.CHF.toString(),
                Currency.JPY.toString(),
                Currency.PLN.toString());
        selectHave.setValue(Currency.PLN.toString());

        selectGet.setLabel("Dostane");
        selectGet.setItems(Currency.EUR.toString(),
                Currency.USD.toString(),
                Currency.GBP.toString(),
                Currency.CHF.toString(),
                Currency.JPY.toString(),
                Currency.PLN.toString());
        selectGet.setValue(Currency.EUR.toString());

        showCalculator();
    }

    private void showCalculator() {

        try {
            currencyRateList = clientNBP.getCurrencyExchangeRate();

        } catch (IOException e) {
            System.out.println("Nie udało się pobrać dane");
        }

        add(new HorizontalLayout(selectHave, currencyHave, button));
        add(new HorizontalLayout(selectGet, currencyGet));

        Optional<CurrencyRate> finalCurrencyRate1 = currencyRateList.getCurrencyRate()
                .stream()
                .filter(x -> x.getCurrency().equals(Currency.GBP))
                .findFirst();

        CurrencyRatesAllList finalCurrencyRateList = currencyRateList;
        button.addClickListener(e -> {
            currencyHaveSelected.setValue(selectHave.getValue());
            currencyGetSelected.setValue(selectGet.getValue());

            add(new Paragraph(finalCurrencyRateList.toString()));
            add(new Paragraph(finalCurrencyRate1.get().getBuy().toString()));
        });
    }

    private void showCurrencyChart() {
        Image image = new Image("https://comparic.xyz/uploads/2019/03/Kursy-waluty-19-marca-2019-3.png", "anatomy of an image");
        image.setMaxHeight("1000");
        image.setMaxHeight("500");
        add(image);

    }
}
