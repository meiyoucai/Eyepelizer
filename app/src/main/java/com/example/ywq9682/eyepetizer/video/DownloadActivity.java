package com.example.ywq9682.eyepetizer.video;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.DownLoadLvAdapter;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.bean.DownLoadBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.c.V;

/**
 * Created by YWQ9682 on 2016/7/30.
 */
public class DownloadActivity extends BaseActivity {
    private ListView listView;
    private SeekBar seekBar;
    private TextView tvTitle;
    private TextView tvNow;
    private String cur_path = Environment.getExternalStorageDirectory()
            + File.separator + "kaiyan/video/";
    private DownLoadLvAdapter adapter;
    private ArrayList<DownLoadBean> datas;
    private Handler handler;

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
        adapter = new DownLoadLvAdapter(this);
        datas = new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DownloadActivity.this, VideoPlayerActivity.class);
                intent.putExtra("path", datas.get(i).getPath());
                intent.putExtra("title", datas.get(i).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        tvNow.setVisibility(View.GONE);
        tvTitle.setVisibility(View.GONE);
        seekBar.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    ArrayList<DownLoadBean> da = (ArrayList<DownLoadBean>) msg.obj;
                    adapter.clear();
                    adapter.setDatas(da);
                    listView.setAdapter(adapter);
                }
            }
        };
        loadVaule();

    }

    private void loadVaule() {
        File file = new File(cur_path);
        Util.showLog("sss", "Sss");
        File[] files = null;
        files = file.listFiles();
        Util.showLog("dfgh", files.length + "");
        if (files.length > 0) {
            if (datas.size() != 0) {
                datas = new ArrayList<>();
            }
            for (File file1 : files) {
                datas.add(new DownLoadBean(file1.getName(), file1.getPath()));
            }
            Message message = new Message();
            message.what = 0;
            message.obj = datas;
            Util.showLog("dfgh", datas.size() + "");
            handler.sendMessage(message);
        }


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
        if (progress == 1) {
            tvNow.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            seekBar.setVisibility(View.GONE);
        }
        Log.d("wqqq", "progress:" + progress);
        seekBar.setProgress((int) (progress * 100));
    }
}
