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
    private CurrencyRatesAllList currencyRateList;
    private final Button button = new Button("Sprawdź kurs");
    private final TextField currencyValueHave = new TextField("Waluta");
    private final TextField currencyValueGet = new TextField("Waluta");
    private final Select<String> selectHave = new Select<>();
    private final Select<String> selectGet = new Select<>();

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

        add(new HorizontalLayout(selectHave, currencyValueHave, button));
        add(new HorizontalLayout(selectGet, currencyValueGet));


        button.addClickListener(e -> {

            double calculation = getCalculation(Currency.valueOf(selectGet.getValue()),
                                                Currency.valueOf(selectHave.getValue()),
                                                Double.valueOf(currencyValueHave.getValue()));

            currencyValueGet.setValue(String.valueOf(calculation));

            add(new Paragraph(currencyValueHave.getValue()));
        });
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

    private void showCurrencyChart() {
        Image image = new Image("https://comparic.xyz/uploads/2019/03/Kursy-waluty-19-marca-2019-3.png", "anatomy of an image");
        image.setMaxHeight("1000");
        image.setMaxHeight("500");
        add(image);

    }
}
