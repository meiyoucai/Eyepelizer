package com.example.ywq9682.eyepetizer.discover.topic;

import android.widget.ListView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by dllo on 16/7/30.
 */
public class TopicActivity extends BaseActivity {
    private TopicBean topicBean;
    private TopicAdapter topicAdapter;
    private ListView listView;
    private String url = "http://baobab.wandoujia.com/api/v3/specialTopics?udid=cd1ee9c5b44e4f9487a505a4fe31ddcb074" +
            "41cc8&vc=121&vn=2.3.5&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_" +
            "xiaomi_market&system_version_code=23";

    @Override
    public int setLayout() {
        return R.layout.fragment_week;
    }

    @Override
    public void initView() {


        listView = (ListView) findViewById(R.id.fragment_week_listview);
        topicAdapter = new TopicAdapter(this);
        topicBean=new TopicBean();
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                Gson gson = new Gson();
                topicBean = gson.fromJson(response, TopicBean.class);
                topicAdapter.setTopicBean(topicBean);
            }
        });
        listView.setAdapter(topicAdapter);


    }

    @Override
    public void initData() {

    }
}
