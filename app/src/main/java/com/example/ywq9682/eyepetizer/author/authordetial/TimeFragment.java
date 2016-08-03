package com.example.ywq9682.eyepetizer.author.authordetial;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.author.authordetial.detialnext.DetialNextActivity;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
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
    private String url = "http://baobab.wandoujia.com/api/v3/pgc/videos?pgcId=";
    private String endUrl = "&strategy=date&udid\" +\n" +
            "\"=cd1ee9c5b44e4f9487a505a4fe31ddcb07441cc8&vc=121&vn=\" + \"2.3.5&dev" +
            "iceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";

    @Override
    public int setLayout() {
        return R.layout.fragment_author_time;
    }

    @Override
    public void initView(View view) {
        Intent bacIntent = getActivity().getIntent();
        int id = bacIntent.getIntExtra("bcId", 0);
        String allUrl = url + id + endUrl;
        Log.d("TimeFragment", allUrl);
        listview = (ListView) view.findViewById(R.id.time_listview);
        datas = new TimeBean();
        timeAdapter = new TimeAdapter(context);
        OkHttpUtils.get().url(allUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                datas = gson.fromJson(response, TimeBean.class);
                // Log.d("TimeFragment", "datas.getTotal():" + datas.getItemList().get(id).getData().getTitle());
                timeAdapter.setTimeBean(datas);
                listview.setAdapter(timeAdapter);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(context, DetialNextActivity.class);
                intent.putExtra("ok", datas.getItemList().get(i).getData().getId());
               // Log.d("TimeFragment", "datas.getItemList().get(i).getData().getId():" + datas.getItemList().get(i).getData().getId());
                context.startActivity(intent);
            }
        });


        View  view1= LayoutInflater.from(context).inflate(R.layout.listview_footview,null);

        TextView endTv= (TextView) view1.findViewById(R.id.end_listview);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/lobster.ttf");
        endTv.setTypeface(typeface);
        listview.addFooterView(view1);
    }
    @Override
    protected void initData() {
    }
}
