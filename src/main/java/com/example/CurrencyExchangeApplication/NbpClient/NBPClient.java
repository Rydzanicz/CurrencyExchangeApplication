package com.example.CurrencyExchangeApplication.NbpClient;


import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRate;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRateList;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.CurrencyRatesAllList;
import com.example.CurrencyExchangeApplication.NbpClient.Table_A_B.RateA_B;
import com.example.CurrencyExchangeApplication.NbpClient.Table_AllCurrency.RateAllCurrency;
import com.example.CurrencyExchangeApplication.NbpClient.Table_AllCurrency.RateEmbAllCurrency;
import com.example.CurrencyExchangeApplication.NbpClient.Table_All_C.RateAllC;
import com.example.CurrencyExchangeApplication.NbpClient.Table_All_C.RateEmbAllC;
import com.example.CurrencyExchangeApplication.NbpClient.Table_C.RateC;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class NBPClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/";
    private static final String NBP_API_URL_All_CURRENCY_A = "http://api.nbp.pl/api/exchangerates/tables/a";
    private static final String NBP_API_URL_All_CURRENCY_C = "http://api.nbp.pl/api/exchangerates/tables/c";
    private static final String FORMAT_JSON = "/?format=json";
    private static final String FORMAT_JSON_LAST_ONE = "/?format=json/last/1/?format=json/";
    private static final String FORMAT_JSON_LAST_ONE_HUNDRED = "/?format=json/last/100/?format=json/";
    private static final String SLASH = "/";
    private static String jsonUrl;


    public CurrencyRate getCurrencyExchangeRateBuyAndSell(CurrencyRate currencyRate, LocalDate localDate) throws IOException {

        jsonUrl = NBP_API_URL + NBPTable.C + SLASH + currencyRate.getCurrency() + SLASH + localDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateC rateC = new Gson().fromJson(reader, RateC.class);
        currencyRate.setExchangeRateDateForBuyAndSell(rateC.getRates().get(0).getEffectiveDate());
        currencyRate.setBuy(rateC.getRates().get(0).getBid());
        currencyRate.setSell(rateC.getRates().get(0).getAsk());
        return currencyRate;
    }

    public CurrencyRate getCurrencyExchangeRateMedium(CurrencyRate currencyRate, LocalDate localDate) throws IOException {


        jsonUrl = NBP_API_URL + NBPTable.A + SLASH + currencyRate.getCurrency() + SLASH + localDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        currencyRate.setExchangeRateDateForMid(rateA_b.getRates().get(0).getEffectiveDate());
        currencyRate.setMedium(rateA_b.getRates().get(0).getMid());
        return currencyRate;
    }

    public CurrencyRateList getCurrencyExchangeRateMedium(CurrencyRateList currencyRateList,
                                                          LocalDate fromLocalDate,
                                                          LocalDate toLocalDate) throws IOException {

        jsonUrl = NBP_API_URL + NBPTable.A + SLASH + currencyRateList.getCurrency() + SLASH + fromLocalDate + SLASH + toLocalDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        Collection<CurrencyRate> localCurrencyRate = rateA_b.getRates()
                                                            .stream()
                                                            .map(x -> new CurrencyRate(currencyRateList.getCurrency(),
                                                                                       null,
                                                                                       null,
                                                                                       null,
                                                                                       LocalDate.parse(x.getEffectiveDate()),
                                                                                       x.getMid()))
                                                            .collect(Collectors.toCollection(LinkedList::new));
        for(int x = 0; x < localCurrencyRate.size(); x++) {
            currencyRateList.addCurrencyRate(localCurrencyRate.stream().toList().get(x));

        }
        return currencyRateList;
    }

    public CurrencyRatesAllList getCurrencyExchangeAllBuyAndSell() throws IOException {
        CurrencyRatesAllList currencyRateList = new CurrencyRatesAllList();

        jsonUrl = NBP_API_URL_All_CURRENCY_C + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        RateAllC[] rateAllCS = new Gson().fromJson(reader, RateAllC[].class);
        RateEmbAllC[] rateEmbC = rateAllCS[ 0 ].getRates();

        LocalDate localDate = LocalDate.parse(rateAllCS[ 0 ].getEffectiveDate());

        for(int i = 0; i < rateEmbC.length; i++) {
            for(Currency currency : Currency.values()) {
                if (currency.name().equalsIgnoreCase(rateEmbC[ i ].getCode())) {
                    CurrencyRate localCurrencyRate = new CurrencyRate(rateEmbC[ i ].getCode(),
                                                                      localDate,
                                                                      rateEmbC[ i ].getBid(),
                                                                      rateEmbC[ i ].getAsk(),
                                                                      null,
                                                                      null);
                    currencyRateList.addCurrencyRate(localCurrencyRate);
                }
            }
        }

        return currencyRateList;
    }

    public CurrencyRatesAllList getNewCurrencyExchangeAll() throws IOException {
        CurrencyRatesAllList currencyRateList = new CurrencyRatesAllList();

        jsonUrl = NBP_API_URL_All_CURRENCY_A + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        Gson gson = new Gson();
        RateAllCurrency[] rateAllCurrencies = gson.fromJson(reader, RateAllCurrency[].class);

        RateEmbAllCurrency[] rateEmbAllCurrency = rateAllCurrencies[ 0 ].getRates();

        LocalDate localDate = LocalDate.parse(rateAllCurrencies[ 0 ].getEffectiveDate());

        for(int i = 0; i < rateEmbAllCurrency.length; i++) {
            for(Currency currency : Currency.values()) {
                if (currency.name().equalsIgnoreCase(rateEmbAllCurrency[ i ].getCode())) {
                    CurrencyRate localCurrencyRate = new CurrencyRate(rateEmbAllCurrency[ i ].getCode(),
                                                                      null,
                                                                      null,
                                                                      null,
                                                                      localDate,
                                                                      rateEmbAllCurrency[ i ].getMid());
                    currencyRateList.addCurrencyRate(localCurrencyRate);
                }
            }
        }

        return currencyRateList;
    }

    public CurrencyRatesAllList getCurrencyExchangeRate() throws IOException {
        CurrencyRatesAllList currencyRatesAllListMid = getNewCurrencyExchangeAll();
        CurrencyRatesAllList currencyRatesAllListBuySell = getCurrencyExchangeAllBuyAndSell();
        CurrencyRatesAllList currencyRateList = new CurrencyRatesAllList();

        int length = Math.max(currencyRatesAllListMid.getCurrencyRate().size(), currencyRatesAllListBuySell.getCurrencyRate().size());

        for(int i = 0; i < length; i++) {
            CurrencyRate localCurrencyRate = new CurrencyRate(currencyRatesAllListBuySell.getCurrencyRate().get(i).getCurrency().name(),
                                                              currencyRatesAllListBuySell.getCurrencyRate()
                                                                                         .get(i)
                                                                                         .getExchangeRateDateForBuyAndSell(),
                                                              currencyRatesAllListBuySell.getCurrencyRate().get(i).getBuy(),
                                                              currencyRatesAllListBuySell.getCurrencyRate().get(i).getSell(),
                                                              currencyRatesAllListMid.getCurrencyRate().get(i).getExchangeRateDateForMid(),
                                                              currencyRatesAllListMid.getCurrencyRate().get(i).getMedium());
            currencyRateList.addCurrencyRate(localCurrencyRate);
        }

        return currencyRateList;
    }
}
