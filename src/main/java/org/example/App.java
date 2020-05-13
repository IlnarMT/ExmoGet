package org.example;

import org.example.model.CarrencyTradePair;
import org.example.model.OrderBook;
import org.example.service.JsonParsService;
import org.example.service.MyHttpService;

import java.time.Instant;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws Exception {

        ConnectDb connectDb=new ConnectDb();
        MyHttpService myHttpService=new MyHttpService();
        JsonParsService jsonParsService=new JsonParsService();
        String strJson;
        CarrencyTradePair carrencyTradePair;
        OrderBook orderBook;
        int k=0;
        String url;
        Instant instant=Instant.now();
        Long currentUnixTime=instant.getEpochSecond();

        for (int i=0;i<10000000;i++){

            url="https://api.exmo.me/v1.1/order_book";
            String strJsonTest=myHttpService.doPostRequest(url,new HashMap<String, String>() {{
                put("pair", "BTC_USD");
                put("limit", "50");
            }});
            //System.out.println(strJsonTest);
            orderBook=jsonParsService.parsJsonToOrderBook(strJsonTest);
            connectDb.insertIntoOrderBooks(TradePairType.BTC_USD,orderBook);

            if ((i % 30)==0) {
                url = "https://api.exmo.me/v1.1/trades";
                strJson = myHttpService.doPostRequest(url, new HashMap<String, String>() {{
                    put("pair", "BTC_USD");
                    /*put("pair", "BTC_EUR");*/
                }});
                carrencyTradePair=jsonParsService.parsJsonToCarrencyTradePair(strJson);
                connectDb.insertIntoTableTransaction(TradePairType.BTC_USD,carrencyTradePair.BTC_USD);
                k++;
                System.out.println("Данные добавлены в таблицу carrencyTradePair "+k);
            }

            //connectDb.insertIntoTableTransaction(TradePairType.BTC_EUR,carrencyTradePair.BTC_EUR);
            Thread.sleep(1000);

            System.out.println("Данные добавлены в таблицу orderBook "+(i+1));
        }
    }

}
