import Rest.Currency;
import Rest.CurrencyRate;
import Rest.NBPClient;

import java.io.IOException;
import java.time.LocalDate;

public class Main {


    public static void main(String[] args) throws IOException {

        NBPClient clientNBP = new NBPClient();
        LocalDate localDate = LocalDate.now().minusDays(2);
        CurrencyRate currencyRate = new CurrencyRate(Currency.USD);
        currencyRate = clientNBP.getCurrencyExchangeRateBuyAndSell(currencyRate, localDate);
        System.out.println(currencyRate);

        currencyRate = clientNBP.getCurrencyExchangeRateMedium(currencyRate, localDate);
        System.out.println(currencyRate);
        currencyRate = clientNBP.getCurrencyExchangeRateMedium(currencyRate);
        System.out.println(currencyRate);

    }


}
