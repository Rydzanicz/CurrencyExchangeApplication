package com.example.CurrencyExchangeApplication.restControler;

import com.example.CurrencyExchangeApplication.Calculation;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRatesAllList;
import com.example.CurrencyExchangeApplication.NbpClient.NBPClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
public class CalculationEndpoint {
    @GetMapping(value = "/calculation", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getCalculation(@RequestParam(value = "haveCurrency", defaultValue = "PLN") String haveCurrency,
                                 @RequestParam(value = "getCurrency", defaultValue = "PLN") String getCurrency,
                                 @RequestParam(value = "amount", defaultValue = "1.0") String amount,
                                 @RequestParam(value = "commission", defaultValue = "0.05") String commission) {
        Currency haveCurrency1;
        Currency getCurrency1;
        double amount1;
        double commission1;

        haveCurrency1 = Currency.valueOf(haveCurrency);
        getCurrency1 = Currency.valueOf(getCurrency);
        amount1 = Double.parseDouble(amount);
        commission1 = Double.parseDouble(commission);

        Calculation calculation = new Calculation(haveCurrency1, getCurrency1, amount1, commission1);
        return calculation.getCalculation();
    }

    @GetMapping(value = "/lastBalance", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getLastBalance(@RequestParam(value = "haveCurrency", defaultValue = "PLN") String haveCurrency,
                                 @RequestParam(value = "getCurrency", defaultValue = "PLN") String getCurrency) {
        Currency haveCurrency1;
        Currency getCurrency1;

        haveCurrency1 = Currency.valueOf(haveCurrency);
        getCurrency1 = Currency.valueOf(getCurrency);

        Calculation calculation = new Calculation(haveCurrency1, getCurrency1);
        return calculation.getCurrencyRate();
    }

    @GetMapping(value = "/exchangeRateChart", produces = MediaType.APPLICATION_JSON_VALUE)
    public List getExchangeRateChart(@RequestParam(value = "haveCurrency", defaultValue = "PLN") String haveCurrency,
                                     @RequestParam(value = "getCurrency", defaultValue = "PLN") String getCurrency) throws IOException {

        Currency haveCurrency1;
        Currency getCurrency1;
        try {
             haveCurrency1 = Currency.valueOf(haveCurrency);
             getCurrency1 = Currency.valueOf(getCurrency);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST);        }
        List<String> newList = new ArrayList<>(200);

        NBPClient nbpClient = new NBPClient();

        CurrencyRatesAllList get = nbpClient.getCurrencyExchangeRateMediumLastHundred(getCurrency1);

        if (haveCurrency1 == Currency.PLN) {
            newList.addAll(get.getCurrencyRate().stream().map(x -> x.getMedium().toString()).toList());
            newList.addAll(get.getCurrencyRate().stream().map(x -> x.getExchangeRateDateForMid().toString()).toList());
            return newList;
        }
        
        CurrencyRatesAllList have = nbpClient.getCurrencyExchangeRateMediumLastHundred(haveCurrency1);

        for (int i = 0; i < have.getCurrencyRate().size(); i++) {
            get.getCurrencyRate().get(i).setMedium(have.getCurrencyRate().get(i).getMedium() / get.getCurrencyRate().get(i).getMedium());
            get.getCurrencyRate().get(i).setExchangeRateDateForMid(String.valueOf(have.getCurrencyRate().get(i).getExchangeRateDateForMid()));
        }

        newList.addAll(get.getCurrencyRate().stream().map(x -> x.getMedium().toString()).toList());
        newList.addAll(get.getCurrencyRate().stream().map(x -> x.getExchangeRateDateForMid().toString()).toList());
        return newList;
    }
}
