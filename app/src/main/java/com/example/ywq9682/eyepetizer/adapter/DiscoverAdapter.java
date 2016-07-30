package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;

import java.util.List;

/**
 * Created by dllo on 16/7/18.
 */
public class DiscoverAdapter extends BaseAdapter {
    private DiscoverBean discoverBean;
    private List<DiscoverBean.ItemListBean> list;
    private Context context;
    private ClickPicture clickPicture;

    public void setClickPicture(ClickPicture clickPicture) {
        this.clickPicture = clickPicture;
    }

    public DiscoverAdapter(Context context) {
        this.context = context;

    }

    public void setDiscoverBean(DiscoverBean discoverBean) {
        this.discoverBean = discoverBean;
        list = discoverBean.getItemList();
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_discover_gridview, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPicture.onClick(i);
            }
        });

        viewHolder.textView.setText(list.get(i).getData().getTitle());
        Glide.with(context).load(list.get(i).getData().getImage()).into(viewHolder.imageView);


        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.item_discover_siv);
            textView = (TextView) view.findViewById(R.id.item_discover_stv);
        }

    }

    public interface ClickPicture {
        void onClick(int position);
    }
}