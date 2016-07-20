package com.example.ywq9682.eyepetizer.discover;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.DiscoverAdapter;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class DiscoverFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private DiscoverAdapter discoverAdapter;
    private DiscoverBean discoverBean;
    @Override
    public int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_discover_recycler);
    }
    @Override
    protected void initData() {
        discoverAdapter = new DiscoverAdapter(context);
        discoverBean= new DiscoverBean();
        String url = AllBean.DISCOVER_URL;
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                discoverBean = gson.fromJson(response, DiscoverBean.class);
                //Log.d("DiscoverFragment", "discoverBean.getCount():" + discoverBean.getItemList().get(13).getData().getTitle());
                discoverAdapter.setDiscoverBean(discoverBean);
                recyclerView.setAdapter(discoverAdapter);
                GridLayoutManager gridLayoutManager =new GridLayoutManager(context,2);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        });
    }
}
