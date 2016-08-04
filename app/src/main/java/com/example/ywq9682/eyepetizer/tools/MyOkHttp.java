package com.example.ywq9682.eyepetizer.tools;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/8/4.
 */
public class MyOkHttp {
    private static MyOkHttp myOkHttp;
    public OkHttpClient client;
    private Gson gson;


    private MyOkHttp() {
        client = new OkHttpClient();
        gson = new Gson();


    }


    public static MyOkHttp getInstance() {
        if (myOkHttp == null) {
            synchronized (MyOkHttp.class) {
                if (myOkHttp == null) {
                    myOkHttp = new MyOkHttp();
                }
            }

        }
        return myOkHttp;
    }

    //异步的get请求
    public <T> void getRequestAsync(String url, final Class<T> clazz, final OnTrue<T> onTrue, final OnError onError) {

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onError.noHasData();
            }

            @Override
            public void onResponse(Call call, Response response) {
                T t = null;
                String result = null;
                try {
                    result = response.body().string();
                    t = gson.fromJson(result, clazz);
                    onTrue.hasData(t);

                } catch (IOException e) {
                    e.printStackTrace();
                    onError.noHasData();
                }

            }


        });

    }

    public interface OnTrue<T> {
        void hasData(T data);
    }

    public interface OnError {
        void noHasData();
    }
}
