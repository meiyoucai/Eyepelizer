package com.example.ywq9682.eyepetizer.discover.gridview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by dllo on 16/7/29.
 */
public class EnjoyFragement extends BaseFragment {
    private DiscoverGridviewBean discoverGridviewBean;
    private TimeAdapter  timeAdapter;
    private ListView listView;
    private  String url;
    private String headUrl = "http://baobab.wandoujia.com/api/v3/videos?categoryId=";
    private String endUrl = "&strategy=shareCount&udid=cd1ee9c5b44e4f9487a505a4fe31ddc\" +\n" +
            "            \"b07441cc8&vc=121&vn=2.3.5&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";

    @Override
    public int setLayout() {
        return R.layout.fragment_week;
    }

    @Override
    public void initView(View view) {
        discoverGridviewBean = new DiscoverGridviewBean();
        timeAdapter = new TimeAdapter(context);
        listView = (ListView) view.findViewById(R.id.fragment_week_listview);
        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("gridviewId", 0);
        url = headUrl + id + endUrl;
        Log.d("EnjoyFragement", url);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {

                Gson gson = new Gson();
                discoverGridviewBean = gson.fromJson(response, DiscoverGridviewBean.class);
                timeAdapter.setDiscoverGridviewBean(discoverGridviewBean);
                listView.setAdapter(timeAdapter);

            }
        });




    }

    @Override
    protected void initData() {

    }
}
