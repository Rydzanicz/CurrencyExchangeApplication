package com.example.CurrencyExchangeApplication.NbpClient;


import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRateList;
import com.example.CurrencyExchangeApplication.NbpClient.Table_A_B.RateA_B;
import com.example.CurrencyExchangeApplication.NbpClient.Table_AllCurrency.RateAllCurrency;
import com.example.CurrencyExchangeApplication.NbpClient.Table_C.RateC;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

public class NBPClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/";
    private static final String NBP_API_URL_All_CURRENCY = "http://api.nbp.pl/api/exchangerates/tables/a";
    private static final String FORMAT_JSON = "/?format=json";
    private static final String FORMAT_JSON_LAST_ONE = "/?format=json/last/1/?format=json/";
    private static final String FORMAT_JSON_LAST_ONE_HUNDRED = "/?format=json/last/100/?format=json/";
    private static final String SLASH = "/";
    private static String jsonUrl;


    public CurrencyRate getCurrencyExchangeRateBuyAndSell(CurrencyRate currencyRate,
                                                          LocalDate localDate) throws IOException
        {

        jsonUrl = NBP_API_URL + NBPTable.C + SLASH + currencyRate.getCurrency() + SLASH + localDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateC rateC = new Gson().fromJson(reader, RateC.class);
        currencyRate.setExchangeRateDateForBuyAndSell(rateC.getRates().get(0).getEffectiveDate());
        currencyRate.setBuy(java.util.Optional.ofNullable(rateC.getRates().get(0).getBid()));
        currencyRate.setSell(java.util.Optional.ofNullable(rateC.getRates().get(0).getAsk()));
        return currencyRate;
        }

    public CurrencyRate getCurrencyExchangeRateBuyAndSell(CurrencyRate currencyRate) throws IOException
        {
        jsonUrl = NBP_API_URL + SLASH + NBPTable.C + SLASH + currencyRate.getCurrency() + FORMAT_JSON_LAST_ONE;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateC rateC = new Gson().fromJson(reader, RateC.class);
        currencyRate.setExchangeRateDateForBuyAndSell(rateC.getRates().get(0).getEffectiveDate());
        currencyRate.setBuy(java.util.Optional.ofNullable(rateC.getRates().get(0).getBid()));
        currencyRate.setSell(java.util.Optional.ofNullable(rateC.getRates().get(0).getAsk()));
        return currencyRate;
        }

    public CurrencyRate getCurrencyExchangeRateMedium(CurrencyRate currencyRate, LocalDate localDate) throws IOException
        {


        jsonUrl = NBP_API_URL + NBPTable.A + SLASH + currencyRate.getCurrency() + SLASH + localDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        currencyRate.setExchangeRateDateForMid(rateA_b.getRates().get(0).getEffectiveDate());
        currencyRate.setMedium(java.util.Optional.ofNullable(rateA_b.getRates().get(0).getMid()));
        return currencyRate;
        }

    public CurrencyRateList getCurrencyExchangeRateMedium(CurrencyRateList currencyRateList,
                                                          LocalDate fromLocalDate,
                                                          LocalDate toLocalDate) throws IOException
        {


        jsonUrl = NBP_API_URL + NBPTable.A + SLASH + currencyRateList.getCurrency() + SLASH + fromLocalDate + SLASH + toLocalDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        Collection<CurrencyRate> localCurrencyRate = rateA_b.getRates()
                                                            .stream()
                                                            .map(x -> new CurrencyRate(currencyRateList.getCurrency(),
                                                                                       Optional.empty(),
                                                                                       Optional.empty(),
                                                                                       Optional.empty(),
                                                                                       Optional.of(LocalDate.parse(x.getEffectiveDate())),
                                                                                       Optional.of(x.getMid())))
                                                            .collect(Collectors.toCollection(LinkedList::new));
        for(int x = 0; x < localCurrencyRate.size(); x++)
        {
            currencyRateList.addCurrencyRate(localCurrencyRate.stream().toList().get(x));

        }
        return currencyRateList;
        }

    public CurrencyRateList getCurrencyExchangeAll(CurrencyRateList currencyRateList) throws IOException
        {


        jsonUrl = NBP_API_URL_All_CURRENCY + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateAllCurrency rateAllCurrency = new Gson().fromJson(reader, RateAllCurrency.class);

        return currencyRateList;
        }

    public CurrencyRate getCurrencyExchangeRateMedium(CurrencyRate currencyRate) throws IOException
        {
        jsonUrl = NBP_API_URL + SLASH + NBPTable.A + SLASH + currencyRate.getCurrency() + FORMAT_JSON_LAST_ONE;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        currencyRate.setExchangeRateDateForMid(rateA_b.getRates().get(0).getEffectiveDate());
        currencyRate.setMedium(java.util.Optional.ofNullable(rateA_b.getRates().get(0).getMid()));
        return currencyRate;
        }


}
