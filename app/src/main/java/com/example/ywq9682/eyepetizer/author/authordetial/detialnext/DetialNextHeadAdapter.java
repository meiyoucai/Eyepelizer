package com.example.ywq9682.eyepetizer.author.authordetial.detialnext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;

/**
 * Created by dllo on 16/7/26.
 */
public class DetialNextHeadAdapter extends RecyclerView.Adapter<DetialNextHeadAdapter.MyViewHolder> {

    private DetialNextBean detialNextBean;
    private Context context;
    private String time;

    public DetialNextHeadAdapter(Context context) {
        this.context = context;
    }

    public void setDetialNextBean(DetialNextBean detialNextBean) {
        this.detialNextBean = detialNextBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_detialnext_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (detialNextBean.getItemList().get(0).getData().getHeader() != null) {
            holder.content.setText(detialNextBean.getItemList().get(0).getData().getItemList().get(position).getData().getDescription());
            int duration = detialNextBean.getItemList().get(0).getData().getItemList().get(position).getData().getDuration();
            time = duration / 60 + " ' " + duration % 60 + " '' ";
            holder.time.setText(time);
            Glide.with(context).load(detialNextBean.getItemList().get(0).getData().getItemList().get(position).getData().getCover().getFeed()).into(holder.imageView);
        }
        else{

            holder.linearLayout.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return detialNextBean.getItemList().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView content;
        TextView time;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.detialnext_recycler_image);
            content = (TextView) itemView.findViewById(R.id.detialnext_recycler_content);
            time = (TextView) itemView.findViewById(R.id.detialnext_recycler_time);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.detialnext_lin);


        }
    }
}