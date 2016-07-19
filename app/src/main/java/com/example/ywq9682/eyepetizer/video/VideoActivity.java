package com.example.ywq9682.eyepetizer.video;

import android.content.Intent;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;


/**
 * Created by YWQ9682 on 2016/7/19.
 */
public class VideoActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

    }

    @Override
    public void initData() {

    }


}
