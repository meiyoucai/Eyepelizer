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
<<<<<<< HEAD

=======
>>>>>>> 8f79be0b7c57910db17a67ad224472d57d01d937

    }

    @Override
    public void initData() {

    }

<<<<<<< HEAD
    @Override
    protected void onPause() {
        super.onPause();

    }
=======

>>>>>>> 8f79be0b7c57910db17a67ad224472d57d01d937
}
