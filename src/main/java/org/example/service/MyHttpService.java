package org.example.service;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyHttpService {
    OkHttpClient client = new OkHttpClient();

    public MyHttpService() throws IOException {
    }

    // code request code here
    public String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String doPostRequest(String url, HashMap<String, String> params) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        for ( Map.Entry<String, String> entry : params.entrySet() ) {
            builder.add( entry.getKey(), entry.getValue() );
        }

        RequestBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
