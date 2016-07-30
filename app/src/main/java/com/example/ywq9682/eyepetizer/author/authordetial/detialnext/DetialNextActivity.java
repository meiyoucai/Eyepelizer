package com.example.ywq9682.eyepetizer.author.authordetial.detialnext;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    View view, view2;
    private String headUrl = "http://baobab.wandoujia.com/api/v3/video/";
    private String url;
    private String endUrl = "/detail/related?udid=cd1ee9c5b44e4f9487a505a4fe31ddcb07441cc8&vc\n" +
            "// =121&vn=2.3.5&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";

    @Override
    public int setLayout() {
        return R.layout.activity_detialnext;
    }

    @Override
    public void initView() {

        Intent bacIntent = getIntent();
        int id = bacIntent.getIntExtra("ok", 0);
        Log.d("DetialNextActivity", "id:" + id);
        url = headUrl + id + endUrl;
        listView = (ListView) findViewById(R.id.detialnext_listview);
        detialNextBean = new DetialNextBean();
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
                initHead();
            }
        });
    }

    private void initHead() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_detialnext_headall, null);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.item_detialnext_head_recycler);
        final DetialNextHeadAdapter detialNextHeadAdapter = new DetialNextHeadAdapter(this);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                detialNextBean = gson.fromJson(response, DetialNextBean.class);
                //Log.d("lanou", "detialNextBean.getItemList().get(0).getData().getHeader().getId():" + detialNextBean.getItemList().get(0).getData().getHeader().getId());
                detialNextHeadAdapter.setDetialNextBean(detialNextBean);
                recyclerView.setAdapter(detialNextHeadAdapter);
                LinearLayoutManager   linearLayoutManager=new LinearLayoutManager(DetialNextActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });
        view2 = LayoutInflater.from(this).inflate(R.layout.item_detialnext_head_title, null);
        final ImageView imageView = (ImageView) view2.findViewById(R.id.item_detialnext_head_image);
        final TextView title = (TextView) view2.findViewById(R.id.item_detialnext_head_title);
        final TextView content = (TextView) view2.findViewById(R.id.item_detialnext_head_content);
        title.setText(detialNextBean.getItemList().get(0).getData().getHeader().getTitle());
        content.setText(detialNextBean.getItemList().get(0).getData().getHeader().getDescription());
        Glide.with(DetialNextActivity.this).load(detialNextBean.getItemList().get(0).getData().getHeader().getIcon()).into(imageView);
        listView.addHeaderView(view2);
        listView.addHeaderView(view);
    }

    @Override
    public void initData() {
    }
}