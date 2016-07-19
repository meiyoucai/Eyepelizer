package com.example.ywq9682.eyepetizer.adapter;

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
import com.example.ywq9682.eyepetizer.allinterface.DataRecyInterface;
import com.example.ywq9682.eyepetizer.bean.AuthorBean;

import java.util.zip.Inflater;

/**
 * Created by YWQ9682 on 2016/7/19.
 */
public class AuthorDataRecyAdapter extends RecyclerView.Adapter {
    private AuthorBean authorBean;
    private Context context;
    private DataRecyInterface dataRecyInterface;

    public void setDataRecyInterface(DataRecyInterface dataRecyInterface) {
        this.dataRecyInterface = dataRecyInterface;
    }

    private int pos;

    public AuthorDataRecyAdapter(Context context) {
        this.context = context;
    }

    public void setAuthorBean(AuthorBean authorBean) {
        this.authorBean = authorBean;
        notifyDataSetChanged();
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DataViewHolder dataViewHolder = null;
        dataViewHolder = new DataViewHolder(LayoutInflater.
                from(context).inflate(R.layout.item_author_recy_videobrief_recydata, parent, false));
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DataViewHolder dataViewHolder = (DataViewHolder) holder;
        dataViewHolder.tvTitle.setText(authorBean.
                getItemList().get(pos).getData().getItemList().get(position).getData().getTitle());
        dataViewHolder.tvCategory.setText("#" + authorBean.
                getItemList().get(pos).getData().getItemList().get(position).getData().getCategory());
        Glide.with(context).load(authorBean.
                getItemList().get(pos).getData().getItemList().get(position).getData().getCover().getFeed())
                .override(400,300)
                .into(dataViewHolder.ivCover);
        dataViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataRecyInterface.DataRecyOnClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return authorBean.
                getItemList().get(pos).getData().getItemList().size();
    }
    class DataViewHolder extends RecyclerView.ViewHolder{
        ImageView ivCover;
        TextView tvTitle, tvCategory;
        LinearLayout linearLayout;
        public DataViewHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_item_author_data_cover);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_author_data_title);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_item_author_data_category);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_item_datarecy);
        }
    }
}
