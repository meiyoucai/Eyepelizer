package com.example.ywq9682.eyepetizer.discover.vr;

import android.content.Intent;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.video.Util;

/**
 * Created by dllo on 16/7/30.
 */
public class Videoview extends BaseActivity {
    private VideoView videoview;

    @Override
    public int setLayout() {
        return R.layout.activity_videoview;
    }

    @Override
    public void initView() {
        videoview = (VideoView) findViewById(R.id.my_videoview);
        Intent bacIntent = getIntent();
        String url = bacIntent.getStringExtra("videoview");
        Util.showLog(url, "kakaka");
        videoview.setMediaController(new MediaController(this));
        videoview.setVideoURI(Uri.parse(url));
        videoview.start();
    }
    @Override
    public void initData() {

    }
}
