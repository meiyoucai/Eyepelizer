package com.example.ywq9682.eyepetizer.author.authordetial;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by dllo on 16/7/21.
 */
public class TimeFragment extends BaseFragment {
    private ListView listview;
    private TimeBean datas;
    private TimeAdapter timeAdapter;
    private String url = AllBean.AUTHOR_DETIAL_TIME_URL;

    @Override
    public int setLayout() {
        return R.layout.fragment_author_time;
    }

    @Override
    public void initView(View view) {
        listview = (ListView) view.findViewById(R.id.time_listview);
        datas= new TimeBean();
        timeAdapter = new TimeAdapter(context);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {

                Gson gson = new Gson();
                datas = gson.fromJson(response, TimeBean.class);
                Log.d("TimeFragment", "datas.getTotal():" + datas.getItemList().get(id).getData().getTitle());
                timeAdapter.setTimeBean(datas);
                listview.setAdapter(timeAdapter);
            }
        });


    }

    @Override
    protected void initData() {
    }
}
