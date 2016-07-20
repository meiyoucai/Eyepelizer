package com.example.ywq9682.eyepetizer.my.cache;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.ywq9682.eyepetizer.R;


/**
 * Created by dllo on 16/7/19.
 */
public class MyCache extends FragmentActivity implements View.OnClickListener {
    private FrameLayout frameLayout;
    private RadioButton manuaklBtn, autoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cache);
        frameLayout = (FrameLayout) findViewById(R.id.repalce_fl);
        manuaklBtn = (RadioButton) findViewById(R.id.manual_rbtn);
        autoBtn = (RadioButton) findViewById(R.id.auto_rbtn);
        manuaklBtn.setOnClickListener(this);
        autoBtn.setOnClickListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.repalce_fl, new ManualFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (view.getId()) {
            case R.id.manual_rbtn:

                fragmentTransaction.replace(R.id.repalce_fl, new ManualFragment());

                break;

            case R.id.auto_rbtn:

                fragmentTransaction.replace(R.id.repalce_fl, new AutoFragment());

                break;


        }
        fragmentTransaction.commit();
    }
}
