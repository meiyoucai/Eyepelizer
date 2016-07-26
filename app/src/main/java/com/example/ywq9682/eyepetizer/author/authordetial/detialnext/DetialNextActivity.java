package com.example.ywq9682.eyepetizer.author.authordetial.detialnext;

import android.content.Intent;
import android.widget.ListView;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;
/**
 * Created by dllo on 16/7/26.
 */
public class DetialNextActivity extends BaseActivity {
    private ListView listView;
    private DetialNextAdapter detialNextAdapter;
    private DetialNextBean detialNextBean;
    private String headUrl = "http://baobab.wandoujia.com/api/v3/video/8220/detail/related?udid=cd1ee9c5b44e4f9487a505a4fe31ddcb074" +
            "41cc8&vc=";
    private String endUrl ="&vn=2.3.5&deviceModel=MI%205&first_channel\" +\n" +
            "\"=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";
    @Override
    public int setLayout() {
        return R.layout.activity_detialnext;
    }
    @Override
    public void initView() {
        Intent bacIntent = getIntent();
        int id = bacIntent.getIntExtra("ok", 0);
        String url = headUrl + id + endUrl;
        listView = (ListView) findViewById(R.id.detialnext_listview);
        detialNextBean=new DetialNextBean();
        detialNextAdapter = new DetialNextAdapter(this);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                detialNextBean = gson.fromJson(response, DetialNextBean.class);
                detialNextAdapter.setDetialNextBean(detialNextBean);
                listView.setAdapter(detialNextAdapter);
            }
        });
    }
    @Override
    public void initData() {
    }
}