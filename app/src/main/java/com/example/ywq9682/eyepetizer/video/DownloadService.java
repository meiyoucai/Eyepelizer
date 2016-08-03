package com.example.ywq9682.eyepetizer.video;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.example.ywq9682.eyepetizer.BuildConfig;
import com.google.gson.annotations.Until;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import okhttp3.Call;

public class DownloadService extends Service {
    private String urlS;
    private String title;
    private MyBind myBind = new MyBind();
    private EventBusBean eventBusBean;

    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {

        urlS = intent.getStringExtra("urlS");
        title = intent.getStringExtra("title");
        if (urlS != null) {
            Util.showToast(this, "开始下载, 请在缓存目录中查看");
            Util.showLog("TAGGG", urlS);
            Log.d("ywq66v", title);
            OkHttpUtils//
                    .get()//
                    .url(urlS)//
                    .build()//
                    .execute(new FileCallBack(Environment.getExternalStorageDirectory()
                            + File.separator + "kaiyan/video/", title + ".mp4") {

                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);
                            Util.showLog("video", "进度:" + progress);
                            eventBusBean = new EventBusBean();
                            eventBusBean.setTitle(title);
                            eventBusBean.setProcess(progress);
                            Util.showLog("ywq66", eventBusBean.getTitle() + "");
                            EventBus.getDefault().post(eventBusBean);
                            if (progress == 1) {
                                Util.showToast(DownloadService.this, title + "下载完成!");
                                stopSelf();
                            }
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Util.showLog("video", "onError :" + e.getMessage());
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            Util.showLog("video", "onError :" + response.getAbsolutePath());
                            Util.showToast(DownloadService.this, "下载失败");
                        }
                    });
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBind;
    }


    class MyBind extends Binder {

    }


}
