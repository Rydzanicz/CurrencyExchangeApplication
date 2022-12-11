package com.example.CurrencyExchangeApplication.restControler;

import com.example.CurrencyExchangeApplication.Calculation;
import com.example.CurrencyExchangeApplication.NbpClient.Currency.Currency;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController public class CalculationEndpoint {
    @RequestMapping(value = "/calculation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public double getCalculation(@RequestParam(value = "haveCurrency", defaultValue = "PLN") String haveCurrency,
                                 @RequestParam(value = "getCurrency", defaultValue = "PLN") String getCurrency,
                                 @RequestParam(value = "amount", defaultValue = "1.0") String amount,
                                 @RequestParam(value = "commission", defaultValue = "0.05") String commission) {
        Currency haveCurrency1 = null;
        Currency getCurrency1 = null;
        double amount1;
        double commission1;

        haveCurrency1 = Currency.valueOf(haveCurrency);
        getCurrency1 = Currency.valueOf(getCurrency);
        amount1 = Double.parseDouble(amount);
        commission1 = Double.parseDouble(commission);

        Calculation calculation = new Calculation(haveCurrency1, getCurrency1, amount1, commission1);
        return calculation.getCalculation();
    }

}
