package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;

/**
 * Created by dllo on 16/7/18.
 */
public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MyViewHolder> {


    private DiscoverBean.ItemListBean.DataBeanSecond discoverBean;
    private Context context;

    public DiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverBean(DiscoverBean.ItemListBean.DataBeanSecond discoverBean) {
        this.discoverBean = discoverBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_discover, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
if (discoverBean.getItemList().get(position).getType()=="squareCard") {
    holder.title.setText(discoverBean.getItemList().get(position).getData().getTitle());

}




    }

    @Override
    public int getItemCount() {
        return discoverBean == null ? 0 : discoverBean.getItemList().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_discover_tv);
            imageView = (ImageView) itemView.findViewById(R.id.item_discover_iv);
        }
    }

}
