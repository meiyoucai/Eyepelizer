package com.example.ywq9682.eyepetizer.video;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.bmob.v3.c.V;

/**
 * Created by YWQ9682 on 2016/7/30.
 */
public class DownloadActivity extends BaseActivity {
    private ListView listView;
    private SeekBar seekBar;
    private TextView tvTitle;
    private TextView tvNow;


    @Override
    public int setLayout() {
        return R.layout.activity_download;
    }

    @Override
    public void initView() {

        EventBus.getDefault().register(this);
        listView = (ListView) findViewById(R.id.lv_download);
        seekBar = (SeekBar) findViewById(R.id.seekbar_nowdown);
        tvTitle = (TextView) findViewById(R.id.tv_down_title);
        tvNow = (TextView) findViewById(R.id.tv_down_now);
    }

    @Override
    public void initData() {
        tvNow.setVisibility(View.GONE);
        tvTitle.setVisibility(View.GONE);
        seekBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getProgress(EventBusBean eventBusBean) {
        float progress = eventBusBean.getProcess();
        String title = "";
        Util.showLog("wwwww", title + "");

        if (title != eventBusBean.getTitle()) {
            tvNow.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            seekBar.setVisibility(View.VISIBLE);
            title = eventBusBean.getTitle();
            tvTitle.setText(title);
        }
        if (progress == 1){
            tvNow.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            seekBar.setVisibility(View.GONE);
        }
        Log.d("wqqq", "progress:" + progress);
        seekBar.setProgress((int) (progress * 100));
    }
}
