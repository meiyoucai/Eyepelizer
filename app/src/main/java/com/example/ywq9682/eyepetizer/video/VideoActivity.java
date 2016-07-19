package com.example.ywq9682.eyepetizer.video;

import android.content.Intent;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by YWQ9682 on 2016/7/19.
 */
public class VideoActivity extends BaseActivity {
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    @Override
    public int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
        jcVideoPlayerStandard.setUp(url, "呵呵");

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
