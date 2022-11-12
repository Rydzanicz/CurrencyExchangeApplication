package Rest;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;

public class NBPClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/";
    private static final String FORMAT_JSON = "/?format=json";
    private static final String FORMAT_JSON_LAST_ONE = "/?format=json/last/1/?format=json/";
    private static final String SLASH = "/";
    private static String jsonUrl;


    public CurrencyRate getCurrencyExchangeRateBuyAndSell(CurrencyRate currencyRate, LocalDate localDate) throws IOException {


        jsonUrl = NBP_API_URL + NBPTable.C + SLASH + currencyRate.getCurrency() + SLASH + localDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateC rateC = new Gson().fromJson(reader, RateC.class);
        currencyRate.setExchangeRateDate(rateC.getRates().get(0).getEffectiveDate());
        currencyRate.setBuy(java.util.Optional.ofNullable(rateC.getRates().get(0).getBid()));
        currencyRate.setSell(java.util.Optional.ofNullable(rateC.getRates().get(0).getAsk()));
        return currencyRate;
    }

    public CurrencyRate getCurrencyExchangeRateBuyAndSell(CurrencyRate currencyRate) throws IOException {
        jsonUrl = NBP_API_URL + SLASH + NBPTable.C + SLASH + currencyRate.getCurrency() + FORMAT_JSON_LAST_ONE;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateC rateC = new Gson().fromJson(reader, RateC.class);
        currencyRate.setExchangeRateDate(rateC.getRates().get(0).getEffectiveDate());
        currencyRate.setBuy(java.util.Optional.ofNullable(rateC.getRates().get(0).getBid()));
        currencyRate.setSell(java.util.Optional.ofNullable(rateC.getRates().get(0).getAsk()));
        return currencyRate;
    }

    public CurrencyRate getCurrencyExchangeRateMedium(CurrencyRate currencyRate, LocalDate localDate) throws IOException {


        jsonUrl = NBP_API_URL + NBPTable.A + SLASH + currencyRate.getCurrency() + SLASH + localDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        currencyRate.setMedium(java.util.Optional.ofNullable(rateA_b.getRates().get(0).getMid()));
        return currencyRate;
    }

    public CurrencyRate getCurrencyExchangeRateMedium(CurrencyRate currencyRate) throws IOException {
        jsonUrl = NBP_API_URL + SLASH + NBPTable.A + SLASH + currencyRate.getCurrency() + FORMAT_JSON_LAST_ONE;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        currencyRate.setMedium(java.util.Optional.ofNullable(rateA_b.getRates().get(0).getMid()));
        return currencyRate;
    }


}
