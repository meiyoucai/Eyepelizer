package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.DownLoadBean;

import java.util.ArrayList;

/**
 * Created by YWQ9682 on 2016/8/4.
 */
public class DownLoadLvAdapter extends BaseAdapter {
    private ArrayList<DownLoadBean> datas;
    private Context context;

    public DownLoadLvAdapter(Context context) {
        this.context = context;
    }
    public void clear(){
        if (datas!= null) {
            datas.clear();
        }
    }

    public void setDatas(ArrayList<DownLoadBean> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyyViewHolder myyViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_download_lv, viewGroup, false);
            myyViewHolder = new MyyViewHolder(view);
            view.setTag(myyViewHolder);
        } else {
            myyViewHolder = (MyyViewHolder) view.getTag();
        }
        myyViewHolder.tvTitle.setText(datas.get(i).getTitle());
        return view;
    }

    class MyyViewHolder {
        private TextView tvTitle;
        public MyyViewHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_item_download_lv_title);
        }
    }
}
