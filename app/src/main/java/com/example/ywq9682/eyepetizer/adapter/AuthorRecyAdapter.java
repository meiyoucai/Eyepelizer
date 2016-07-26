package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.allinterface.BriefOnClickListener;
import com.example.ywq9682.eyepetizer.allinterface.DataRecyInterface;
import com.example.ywq9682.eyepetizer.allinterface.VideoBriefOnClickListener;
import com.example.ywq9682.eyepetizer.bean.AuthorBean;
import com.example.ywq9682.eyepetizer.video.VideoPlayer;

/**
 * Created by YWQ9682 on 2016/7/18.
 */
public class AuthorRecyAdapter extends RecyclerView.Adapter {
    private AuthorBean author;
    private Context context;
    private int type;
    private static final int HEADER = 0;
    private static final int BRIEFCARD = 1;
    private static final int VIDEOBRIEF = 2;
    private static final int BRANKCARD = 3;
    private BriefOnClickListener briefOnClickListener;
    private VideoBriefOnClickListener videoBriefOnClickListener;

    public AuthorRecyAdapter(Context context) {
        this.context = context;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
        notifyDataSetChanged();
    }

    public void setBriefOnClickListener(BriefOnClickListener briefOnClickListener) {
        this.briefOnClickListener = briefOnClickListener;
    }

    public void setVideoBriefOnClickListener(VideoBriefOnClickListener videoBriefOnClickListener) {
        this.videoBriefOnClickListener = videoBriefOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case HEADER:
                viewHolder = new HEADERViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.item_author_recy_header, parent, false));
                break;
            case BRIEFCARD:
                viewHolder = new BRIEFCARDViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.item_author_recy_briefcard, parent, false));
                break;
            case VIDEOBRIEF:
                viewHolder = new VIDEOBRIEFViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.item_author_recy_videobrief, parent, false));
                break;
            case BRANKCARD:
                viewHolder = new BRANKCARDViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.iten_author_recy_blankcard, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case HEADER:
                HEADERViewHolder headerViewHolder = (HEADERViewHolder) holder;
                headerViewHolder.tvHeader.setText(author.getItemList().get(position).getData().getText());
                break;
            case BRIEFCARD:
                final BRIEFCARDViewHolder briefcardViewHolder = (BRIEFCARDViewHolder) holder;
                briefcardViewHolder.tvTitle.setText(author.getItemList()
                        .get(position).getData().getTitle());
                briefcardViewHolder.tvSubTitle.setText(author.getItemList()
                        .get(position).getData().getSubTitle());
                briefcardViewHolder.tvDescription.setText(author.getItemList()
                        .get(position).getData().getDescription());

                Glide.with(context).load(author.getItemList().get(position)
                        .getData().getIcon()).into(briefcardViewHolder.ivIcon);
                briefcardViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = briefcardViewHolder.getLayoutPosition();
                        briefOnClickListener.onClick(position);
                    }
                });
                break;
            case VIDEOBRIEF:
                // Log.d("ffdr", "position:" + position);
                // Log.d("ffdr", author.getItemList().get(position).getData().getHeader().getTitle());
                final VIDEOBRIEFViewHolder videobriefViewHolder = (VIDEOBRIEFViewHolder) holder;
                videobriefViewHolder.tvTitle.setText(author.getItemList().get(position)
                        .getData().getHeader().getTitle());
                videobriefViewHolder.tvSubTitle.setText(author.getItemList().get(position)
                        .getData().getHeader().getSubTitle());
                videobriefViewHolder.tvDescription.setText(author.getItemList().get(position)
                        .getData().getHeader().getDescription());
                Glide.with(context).load(author.getItemList().get(position)
                        .getData().getHeader().getIcon()).into(videobriefViewHolder.ivIcon);
                AuthorDataRecyAdapter authorDataRecyAdapter = new AuthorDataRecyAdapter(context);
                authorDataRecyAdapter.setAuthorBean(author);
                authorDataRecyAdapter.setPos(position);
                videobriefViewHolder.recyclerView.setAdapter(authorDataRecyAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                videobriefViewHolder.recyclerView.setLayoutManager(linearLayoutManager);
                authorDataRecyAdapter.setDataRecyInterface(new DataRecyInterface() {
                    @Override
                    public void DataRecyOnClick(int pos) {
                        Intent intent = new Intent(context, VideoPlayer.class);
                        intent.putExtra("url", author.getItemList().get(position).getData()
                                .getItemList().get(pos).getData().getPlayUrl());
                        context.startActivity(intent);
                    }
                });
               videobriefViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       int position=videobriefViewHolder.getLayoutPosition();
                       videoBriefOnClickListener.onClick(position);
                   }
               });
                break;
            case BRANKCARD:
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        switch (author.getItemList().get(position).getType()) {
            case "leftAlignTextHeader":
                type = HEADER;
                break;
            case "briefCard":
                type = BRIEFCARD;
                break;
            case "videoCollectionWithBrief":
                type = VIDEOBRIEF;
                break;
            case "blankCard":
                type = BRANKCARD;
                break;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return author.getCount();
    }


    //type为0
    class HEADERViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        public HEADERViewHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.tv_item_author_recy_header);
        }
    }//type为1

    class BRIEFCARDViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle, tvSubTitle, tvDescription;
        RelativeLayout relativeLayout;

        public BRIEFCARDViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_author_recy_briefcard_icon);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_author_recy_briefcard_title);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tv_item_author_recy_briefcard_subtitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_item_author_recy_briefcard_description);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.briefcard_relative);
        }
    }

    //type为2
    class VIDEOBRIEFViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle, tvSubTitle, tvDescription;
        RecyclerView recyclerView;
        RelativeLayout relativeLayout;

        public VIDEOBRIEFViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_author_recy_videobrief_title);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tv_item_author_recy_videobrief_subtitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_item_author_recy_videobrief_description);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_author_recy_videobrief_icon);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recy_author_recy_videobrief_data);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.videobrief_relative);
        }
    }

    //type为3
    class BRANKCARDViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;

        public BRANKCARDViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_author_recy_blankcard);
        }
    }


}
