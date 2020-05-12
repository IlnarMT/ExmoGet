package org.example.service;

import com.google.gson.Gson;
import org.example.model.CarrencyTradePair;

public class JsonParsService {

    public static CarrencyTradePair parsJsonToCarrencyTradePair(String jsonStr){
        Gson gson = new Gson();
        CarrencyTradePair carrencyTradePair =gson.fromJson(jsonStr, CarrencyTradePair.class);
        //System.out.println("Pars BTC_USD: "+ carrencyTradePair.BTC_USD.get(99).trade_id);
        //System.out.println("Pars BTC_EUR: "+ carrencyTradePair.BTC_EUR.get(99).trade_id);
/*        for (Transact transact:carrencyTradePair.BTC_USD){
            Long unixDate=transact.date;
            Instant instant = Instant.ofEpochSecond(unixDate);
            System.out.println(instant.toString());
        }*/
        return carrencyTradePair;
    }
}
