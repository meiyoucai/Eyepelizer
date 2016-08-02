package com.example.ywq9682.eyepetizer.author.authordetial.detialnext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.Utils;

/**
 * Created by dllo on 16/7/26.
 */
public class DetialNextRecyclerAdapter extends RecyclerView.Adapter<DetialNextRecyclerAdapter.MyViewHolder> {
    private DetialNextBean.ItemListBean.DataBean detialNextBean;
    private Context context;
    private String time;

    public DetialNextRecyclerAdapter(Context context) {
        this.context = context;

    }

    public void setDetialNextBean(DetialNextBean.ItemListBean.DataBean detialNextBean) {
        this.detialNextBean = detialNextBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_detialnext_recycler, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.content.setText(detialNextBean.getItemList().get(position).getData().getTitle());
        int duration = detialNextBean.getItemList().get(position).getData().getDuration();
        time = duration / 60 + " ' " + duration % 60 + " '' ";
        holder.time.setText(time);
        Glide.with(context).load(detialNextBean.getItemList().get(position).getData().getCover().getFeed()).into(holder.imageView);
        ViewGroup.LayoutParams layoutParams=holder.imageView.getLayoutParams();
        layoutParams.width= (int) (Utils.getScreenWidth(context)*0.46f);
        layoutParams.height= (int) (Utils.getScreenHeight(context)*0.17f);
        holder.imageView.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        return detialNextBean.getItemList().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView content;
        TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.detialnext_recycler_image);
            content = (TextView) itemView.findViewById(R.id.detialnext_recycler_content);
            time = (TextView) itemView.findViewById(R.id.detialnext_recycler_time);

        }
    }
}
