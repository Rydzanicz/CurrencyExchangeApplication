package Rest;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RestClient {

    //    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/usd/";
    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/c/usd/";
    private static final String FORMAT_JSON = "/?format=json";
    private static String jsonUrl;


    public double getUSDExchangeRate(String date) throws IOException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        String formatDate = dateTimeFormatter.format(localDate);

        jsonUrl = NBP_API_URL + formatDate + FORMAT_JSON;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        Rate rate = new Gson().fromJson(reader, Rate.class);
        double usdRate = rate.getRates().get(0).getBid();

        return usdRate;
    }

}
