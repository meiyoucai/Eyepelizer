package com.example.ywq9682.eyepetizer.discover.vr;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by dllo on 16/7/30.
 */
public class EnjoyFragment extends BaseFragment {
    private String url = "http://baobab.wandoujia.com/api/v3/tag/videos?tagId=658&strategy" +
            "=shareCount&udid=cd1ee9c5b44e4f9487a505a4fe31ddcb07" +
            "441cc8&vc=121&vn=2.3.5&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_" +
            "market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";
    private ListView listView;
    private VrBean vrBean;
    private VrTimeAdapter timeAdapter;
    @Override
    public int setLayout() {
        return R.layout.fragment_week;
    }

    @Override
    public void initView(View view) {
        vrBean = new VrBean();
        timeAdapter = new VrTimeAdapter(context);
        listView = (ListView) view.findViewById(R.id.fragment_week_listview);
        Log.d("TimeFragment", url);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(), "e:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {

                Gson gson = new Gson();
                vrBean = gson.fromJson(response, VrBean.class);
                Log.d("TimeFragment", "discoverGridviewBean.getCount():" + vrBean.getCount());
                timeAdapter.setVrBean(vrBean);
                listView.setAdapter(timeAdapter);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
