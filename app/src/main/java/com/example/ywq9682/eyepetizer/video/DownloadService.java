package com.example.ywq9682.eyepetizer.video;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class DownloadService extends Service {
    public boolean flag = false;
    private String urlS;
    private String title;
    private MyBind myBind = new MyBind();

    public DownloadService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        urlS = intent.getStringExtra("urlS");
        title = intent.getStringExtra("title");
        Util.showToast(this, "开始下载, 请在缓存目录中查看");
        Util.showLog("TAGGG",urlS);
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
                        if (progress == 1){
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
                    }
                });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBind;
    }


    class MyBind extends Binder{

    }


}
