package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;

/**
 * Created by dllo on 16/7/18.
 */
public class DiscoverAdapter extends RecyclerView.Adapter {
    private DiscoverBean discoverBean;
    private static final int horizontalScrollCard = 0;
    private static final int squareCard = 1;
    private int type;
    private Context context;

    public DiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverBean(DiscoverBean discoverBean) {
        this.discoverBean = discoverBean;
        discoverBean.getItemList().remove(3);

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //获得type
        switch (discoverBean.getItemList().get(position).getType()) {

            case "horizontalScrollCard":

                type = horizontalScrollCard;

                break;
            case "squareCard":
                type = squareCard;
                break;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case horizontalScrollCard:
                viewHolder = new HorizontalScrollCard(LayoutInflater.from(context).inflate(R.layout.item_discover_horizontalscrollcard, parent, false));
                break;
            case squareCard:
                viewHolder = new SquareCard(LayoutInflater.from(context).inflate(R.layout.item_discover_squarecard, parent, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case squareCard:
                SquareCard squareCard = (SquareCard) holder;
                squareCard.relativeLayout.setVisibility(View.VISIBLE);
                squareCard.title.setText(discoverBean.getItemList().get(position).getData().getTitle());
                Log.d("DiscoverAdapter", "kasdklajdklassssss");
                Glide.with(context).load(discoverBean.getItemList().get(position).getData().getImage()).into(squareCard.imageView);
                break;
            case horizontalScrollCard:
                HorizontalScrollCard horizontalScrollCard = (HorizontalScrollCard) holder;
                HorizontalAdapter horizontalAdapter = new HorizontalAdapter(context);
                horizontalAdapter.setDiscoverBean(discoverBean.getItemList().get(position).getData());
                horizontalScrollCard.viewPager.setAdapter(horizontalAdapter);
                break;

        }


    }

    @Override
    public int getItemCount() {
        return discoverBean == null ? 0 : discoverBean.getItemList().size();
    }

    class SquareCard extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public SquareCard(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_discover_stv);
            imageView = (ImageView) itemView.findViewById(R.id.item_discover_siv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rela_discover);
        }
    }


    class HorizontalScrollCard extends RecyclerView.ViewHolder {
        ViewPager viewPager;

        public HorizontalScrollCard(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.horizontalscroll_viewpager);
        }
    }


}
