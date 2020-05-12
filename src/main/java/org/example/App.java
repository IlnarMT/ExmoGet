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
import org.example.service.JsonParsService;
import org.example.service.MyHttpService;

import java.io.IOException;
import java.util.HashMap;

public class App {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        App app = new App();
        String strJson;
        ConnectDb connectDb=new ConnectDb();
        MyHttpService myHttpService=new MyHttpService();
        CarrencyTradePair carrencyTradePair;
        JsonParsService jsonParsService=new JsonParsService();
        int k=0;
        String url;
        test();

        try {
            for (int i=0;i<5000;i++){
                //System.out.println("Testing 1 - Send Http GET request");
                //strJson=app.sendGet();
                url="https://api.exmo.me/v1.1/trades";
                strJson=myHttpService.doPostRequest(url,new HashMap<String, String>() {{
                    put("pair", "BTC_USD");
                    put("pair", "BTC_EUR");
                }});

                carrencyTradePair=jsonParsService.parsJsonToCarrencyTradePair(strJson);

                connectDb.insertIntoTableTransaction(TradePairType.BTC_USD,carrencyTradePair.BTC_USD);
                //connectDb.insertIntoTableTransaction(TradePairType.BTC_EUR,carrencyTradePair.BTC_EUR);
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
        String getUri="https://api.exmo.me/v1/trades/?pair=BTC_USD,BTC_EUR";

        HttpGet request = new HttpGet(getUri);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            //System.out.println(response.getStatusLine().toString());

            entity = response.getEntity();
            Header headers = entity.getContentType();
            //System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                //System.out.println(result);
                return result;
              //  parsJson(result);
            } else throw new Exception("Exmo вернуло null!");
        }

    }


    public static void test() throws IOException {

        // issue the Get request
        MyHttpService myHttpService = new MyHttpService();
        String getResponse = myHttpService.doGetRequest("https://www.vogella.com/");
        System.out.println(getResponse);


        // issue the post request
        String url="https://api.exmo.me/v1.1/trades";
        String postResponse = myHttpService.doPostRequest(url,new HashMap<String, String>() {{
            put("pair", "BTC_USD");
            put("pair", "BTC_EUR");
        }});
        System.out.println(postResponse);
    }
}
