import Currency.Currency;
import Currency.CurrencyRate;
import Currency.CurrencyRateList;
import Rest.NBPClient;

import java.io.IOException;
import java.time.LocalDate;

public class Main {


    public static void main(String[] args) throws IOException {

        NBPClient clientNBP = new NBPClient();
        LocalDate localDate = LocalDate.now().minusDays(2);
        CurrencyRate currencyRate = new CurrencyRate(Currency.USD);
        CurrencyRateList currencyRateList = new CurrencyRateList(currencyRate.getCurrency());
//        currencyRateList = clientNBP.getCurrencyExchangeRateMedium(currencyRateList,localDate.minusDays(5),localDate);
        currencyRateList = clientNBP.getCurrencyExchangeAll(currencyRateList);
        System.out.println(currencyRateList);

    }


}
