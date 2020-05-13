package org.example;

import org.example.model.CarrencyTradePair;
import org.example.model.OrderBook;
import org.example.service.JsonParsService;
import org.example.service.MyHttpService;

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

        for (int i=0;i<1;i++){

            url="https://api.exmo.me/v1.1/order_book";
            String strJsonTest=myHttpService.doPostRequest(url,new HashMap<String, String>() {{
                put("pair", "BTC_USD");
                put("limit", "50");
            }});
            System.out.println(strJsonTest);
            orderBook=jsonParsService.parsJsonToOrderBook(strJsonTest);
            connectDb.insertIntoOrderBooks(TradePairType.BTC_USD,orderBook);

            if (i=)
            url="https://api.exmo.me/v1.1/trades";
            strJson=myHttpService.doPostRequest(url,new HashMap<String, String>() {{
                put("pair", "BTC_USD");
                /*put("pair", "BTC_EUR");*/
            }});

            carrencyTradePair=jsonParsService.parsJsonToCarrencyTradePair(strJson);

            connectDb.insertIntoTableTransaction(TradePairType.BTC_USD,carrencyTradePair.BTC_USD);
            //connectDb.insertIntoTableTransaction(TradePairType.BTC_EUR,carrencyTradePair.BTC_EUR);
            Thread.sleep(30000);
            k++;
            System.out.println("Сделан цикл get "+k);
        }
    }

}
