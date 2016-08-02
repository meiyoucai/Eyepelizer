package com.example.ywq9682.eyepetizer.my.collect;

import android.widget.ListView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.base.SingleLiteOrm;
import com.example.ywq9682.eyepetizer.selection.CollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/1.
 */
public class CollectActivity extends BaseActivity {
    private ListView listView;
    private CollectAdapter collectAdapter;
    private List<CollectBean> collectBean;


    @Override
    public int setLayout() {
        return R.layout.my_collect;
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.my_collect_listview);
        collectAdapter = new CollectAdapter(this);
        collectBean = new ArrayList<>();

        for (CollectBean bean : SingleLiteOrm.getSingleLiteOrm().quaryAllSingle(CollectBean.class)) {
            collectBean.add(bean);
        }
                collectAdapter.setCollectBeen(collectBean);
               listView.setAdapter(collectAdapter);
    }

    @Override
    public void initData() {

    }
}
