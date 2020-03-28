package org.example;

import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.model.CarrencyTradePair;
import org.example.model.Transact;

import java.io.IOException;
import java.time.Instant;

public class App {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        App obj = new App();
        String strJson;
        ConnectDb connectDb=new ConnectDb();
        CarrencyTradePair carrencyTradePair;
        int k=0;

        try {
            for (int i=0;i<120;i++){
                System.out.println("Testing 1 - Send Http GET request");
                strJson=obj.sendGet();

                carrencyTradePair=parsJson(strJson);

                connectDb.insertIntoTableTransaction(TradePairType.BTC_USD,carrencyTradePair.BTC_USD);
                connectDb.insertIntoTableTransaction(TradePairType.BTC_EUR,carrencyTradePair.BTC_EUR);
                Thread.sleep(30000);
                k++;
                System.out.println("Сделан цикл get "+k);
            }

        } finally {
            obj.close();
        }

    }

    private void close() throws IOException {
        httpClient.close();
    }

    private String sendGet() throws Exception {
        HttpEntity entity;

        HttpGet request = new HttpGet("https://api.exmo.me/v1/trades/?pair=BTC_USD,BTC_EUR"); //,BTC_EUR

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                //System.out.println(result);
                return result;
              //  parsJson(result);
            } else throw new Exception("Exmo вернуло null!");
        }

    }

    private static CarrencyTradePair parsJson(String json){
        Gson gson = new Gson();
        CarrencyTradePair carrencyTradePair =gson.fromJson(json, CarrencyTradePair.class);
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
