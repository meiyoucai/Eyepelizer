package com.example.ywq9682.eyepetizer.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayout());
        initView();
        initData();
    }


    public abstract int setLayout();

    public abstract void initView();

    public abstract void initData();

}
