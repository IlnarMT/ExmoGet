package org.example;

import org.apache.commons.net.ntp.TimeStamp;
import org.example.model.CarrencyTradePair;
import org.example.model.OrderBook;
import org.example.service.JsonParsService;
import org.example.service.MyHttpService;
import org.example.service.NTPService;

import java.time.Instant;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws Exception {

        ConnectDb connectDb = new ConnectDb();
        MyHttpService myHttpService = new MyHttpService();
        JsonParsService jsonParsService = new JsonParsService();
        String strJson;
        CarrencyTradePair carrencyTradePair;
        OrderBook orderBook;
        int k = 0;
        String url;
        NTPService ntpService = new NTPService();
        long offsetTimeMilli = ntpService.getOffsetTimeMilli();

        for (int i = 0; i < 100000000; i++) {
/*            long systemUnixTime= Instant.now().getEpochSecond();
            long unixTimeOffset=ntpService.getUnixTimeOffset();*/
            long systemTimeMilli = Instant.now().toEpochMilli();

            if ((i % (12 * 60 * 60)) == 0) {
                offsetTimeMilli = ntpService.getOffsetTimeMilli();
            }
            long currentUnixTime = Math.round((double) (systemTimeMilli + offsetTimeMilli) / 1000);

            url = "https://api.exmo.me/v1.1/order_book";
            String strJsonTest = myHttpService.doPostRequest(url, new HashMap<String, String>() {{
                put("pair", "BTC_USD");
                put("limit", "50");
            }});
            //System.out.println(strJsonTest);
            orderBook = jsonParsService.parsJsonToOrderBook(strJsonTest);
            System.out.println(systemTimeMilli + " " + offsetTimeMilli + " " + (systemTimeMilli + offsetTimeMilli) + " " + TimeStamp.getNtpTime((currentUnixTime) * 1000).toDateString());
            connectDb.insertIntoOrderBooks(TradePairType.BTC_USD, orderBook, currentUnixTime);

            if ((i % 30) == 0) {
                url = "https://api.exmo.me/v1.1/trades";
                strJson = myHttpService.doPostRequest(url, new HashMap<String, String>() {{
                    put("pair", "BTC_USD");
                    /*put("pair", "BTC_EUR");*/
                }});
                carrencyTradePair = jsonParsService.parsJsonToCarrencyTradePair(strJson);
                connectDb.insertIntoTableTransaction(TradePairType.BTC_USD, carrencyTradePair.BTC_USD);
                System.out.println("Time to table Transact " + carrencyTradePair.BTC_USD.get(0).getDate());
                System.out.println("!Time to table Transact-currentUnixTime " + (carrencyTradePair.BTC_USD.get(0).getDate() - currentUnixTime));
                k++;
                System.out.println("Данные добавлены в таблицу carrencyTradePair " + k);
            }

            //connectDb.insertIntoTableTransaction(TradePairType.BTC_EUR,carrencyTradePair.BTC_EUR);
            Thread.sleep(900);

            System.out.println("Данные добавлены в таблицу orderBook " + (i + 1));
        }
    }

}
