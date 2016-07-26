package com.example.ywq9682.eyepetizer.author.authordetial.detialnext;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;

/**
 * Created by dllo on 16/7/26.
 */
public class DetialNextAdapter extends BaseAdapter {
    private DetialNextBean detialNextBean;
    private Context context;

    public DetialNextAdapter(Context context) {
        this.context = context;
    }

    public void setDetialNextBean(DetialNextBean detialNextBean) {
        this.detialNextBean = detialNextBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return detialNextBean == null ? 0 : detialNextBean.getItemList().size()-1;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_detialnext_hotspot, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(detialNextBean.getItemList().get(i+1).getData().getHeader().getTitle());
        DetialNextRecyclerAdapter detialNextRecyclerAdapter = new DetialNextRecyclerAdapter(context);
        detialNextRecyclerAdapter.setDetialNextBean(detialNextBean.getItemList().get(i+1).getData());
        viewHolder.recyclerView.setAdapter(detialNextRecyclerAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);

        return view;

    }

    class ViewHolder {
        TextView title;
        RecyclerView recyclerView;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.item_detialnext_hotspot);
            recyclerView = (RecyclerView) view.findViewById(R.id.detialnext_recycler_hotspot);

        }

    }


}