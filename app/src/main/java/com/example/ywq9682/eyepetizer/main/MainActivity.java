package com.example.ywq9682.eyepetizer.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_main);

    }

    @Override
    public void initData() {
        OkHttpUtils.get().url(AllBean.DISCOVER).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("www", response);
            }
        });
    }
}
