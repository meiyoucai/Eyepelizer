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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by dllo on 16/7/26.
 */
public class DetialNextActivity extends BaseActivity {
    private ListView listView;
    private DetialNextAdapter detialNextAdapter;
    private DetialNextBean detialNextBean;
    View viewHead, viewRcy;
    private String headUrl = "http://baobab.wandoujia.com/api/v3/video/";
    private String url;
    private String endUrl = "/detail/related?udid=cd1ee9c5b44e4f9487a505a4fe31ddcb07441cc8&vc\n" +
            "// =121&vn=2.3.5&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";
    private String mTitle;
    private String mDescription;
    private String mIcon;

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
        //中间
        final View viewRcy = LayoutInflater.from(this).inflate(R.layout.item_detialnext_headall, null);
        final RecyclerView recyclerView = (RecyclerView) viewRcy.findViewById(R.id.item_detialnext_head_recycler);
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
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetialNextActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray itemList = jsonObject.getJSONArray("itemList");
                    JSONObject jsonObject1 = itemList.getJSONObject(0);
                    JSONObject data = jsonObject1.getJSONObject("data");
                    JSONObject header = data.getJSONObject("header");
                    mTitle = header.getString("title");
                    mDescription = header.getString("description");
                    mIcon = header.getString("icon");
                    Toast.makeText(DetialNextActivity.this, mTitle, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DetialNextActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                //头
                viewHead = LayoutInflater.from(DetialNextActivity.this).inflate(R.layout.item_detialnext_head_title, null);
                final ImageView imageView = (ImageView) viewHead.findViewById(R.id.item_detialnext_head_image);
                final TextView title = (TextView) viewHead.findViewById(R.id.item_detialnext_head_title);
                final TextView content = (TextView) viewHead.findViewById(R.id.item_detialnext_head_content);
                title.setText(detialNextBean.getItemList().get(0).getData().getTitle());
                //  Log.d("DetialNextActivity", detialNextBean.getItemList().get(0).getData().getTitle());
                content.setText(detialNextBean.getItemList().get(0).getData().getDescription());
                Glide.with(DetialNextActivity.this).load(detialNextBean.getItemList().get(0).getData().getIcon()).into(imageView);
                if (detialNextBean.getItemList().get(0).getData().getHeader() == null){
//                    title.setText(mTitle);
//                    content.setText(mDescription);
//                    Glide.with(DetialNextActivity.this).load(mIcon).into(imageView);
                    listView.addHeaderView(viewHead);
                }
                else if (detialNextBean.getItemList().get(0).getData().getHeader() != null) {
//                    title.setText(detialNextBean.getItemList().get(0).getData().getTitle());
//                    content.setText(detialNextBean.getItemList().get(0).getData().getDescription());
//                    Glide.with(DetialNextActivity.this).load(detialNextBean.getItemList().get(0).getData().getIcon()).into(imageView);
                    title.setText(mTitle);
                    content.setText(mDescription);
                    Glide.with(DetialNextActivity.this).load(mIcon).into(imageView);
                    listView.addHeaderView(viewHead);
                    listView.addHeaderView(viewRcy);
                }
            }
        });



    }

    @Override
    public void initData() {
    }
}