import Rest.RestClient;

import java.io.IOException;
import java.time.LocalDate;

public class Main {


    public static void main(String[] args) throws IOException {

        RestClient restClient = new RestClient();

        String localDate = LocalDate.now().minusDays(2).toString();
        double cenaPLN = restClient.getUSDExchangeRate(localDate);
        System.out.println(cenaPLN);

    }


}
