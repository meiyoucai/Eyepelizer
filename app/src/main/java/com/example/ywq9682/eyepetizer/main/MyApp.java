package com.example.ywq9682.eyepetizer.main;

import android.app.Application;
import android.content.Context;

import com.fuqianla.paysdk.FuQianLa;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.bmob.v3.Bmob;
import okhttp3.OkHttpClient;

/**
 * Created by YWQ9682 on 2016/7/18.
 */
public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Bmob.initialize(this, "b253622732f2d2d76ebc289e47c23ac9");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        //支付环节
        FuQianLa.getInstance().init(getApplicationContext());
    }
}
