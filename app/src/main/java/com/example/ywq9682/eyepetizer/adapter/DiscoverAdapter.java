package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;

/**
 * Created by dllo on 16/7/18.
 */
public class DiscoverAdapter extends RecyclerView.Adapter {
    private DiscoverBean discoverBean;
    //private static final String horizontalScrollCard = "horizontalScrollCard";
   // private static final int rectangleCard = 2;
    private static final int squareCard = 1;

    private Context context;

    public DiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverBean(DiscoverBean discoverBean) {
        this.discoverBean = discoverBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        switch (discoverBean.getItemList().get(position).getType()) {
            case "rectangleCard":
                type = 2;

                break;
//            case "squareCard":
//                type = 1;
//                break;
        }
        return type;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case squareCard:
                View view = LayoutInflater.from(context).inflate(R.layout.item_discover_squarecard, parent, false);
                viewHolder = new SquareCard(view);
                break;
//            case rectangleCard:
//                View view1 = LayoutInflater.from(context).inflate(R.layout.item_discover_rectanglecard, parent, false);
//                viewHolder = new RectangleCard(view1);
//                break;
            default:
                View view2 = new View(context);
                viewHolder = new HVH(view2);
        }
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        int viewType = getItemViewType(position);
        switch (viewType) {
            case squareCard:
                SquareCard squareCard = (SquareCard) holder;
             squareCard.title.setText(discoverBean.getItemList().get(position).getData().getTitle());
                Log.d("DiscoverAdapter", "kasdklajdklassssss");
             Glide.with(context).load(discoverBean.getItemList().get(position).getData().getImage()).into(squareCard.imageView);
                break;
//            case rectangleCard:
//                RectangleCard rectangleCard = (RectangleCard) holder;
//               Glide.with(context).load(discoverBean.getItemList().get(position).getData().getImage()).into(rectangleCard.imageView1);
//                break;

        }
    }

    @Override
    public int getItemCount() {
        return discoverBean == null ? 0 : discoverBean.getItemList().size();
    }

    class SquareCard extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public SquareCard(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_discover_stv);
            imageView = (ImageView) itemView.findViewById(R.id.item_discover_siv);
        }
    }

    class RectangleCard extends RecyclerView.ViewHolder {
        ImageView imageView1;

        public RectangleCard(View itemView) {
            super(itemView);
            imageView1 = (ImageView) itemView.findViewById(R.id.item_discover_riv);
        }
    }
    class HVH extends RecyclerView.ViewHolder{

        public HVH(View itemView) {
            super(itemView);
        }
    }
}
