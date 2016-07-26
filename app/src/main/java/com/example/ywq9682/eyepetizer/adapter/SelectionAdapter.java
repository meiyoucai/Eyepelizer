package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.bean.SelectionBean;
import com.example.ywq9682.eyepetizer.selection.SelectionDetailActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/20.
 */
public class SelectionAdapter extends BaseAdapter {
    private SelectionBean selectionBean;
    private ArrayList<SelectionListBean> selectionListBeen;
    private Context context;

    private static final int TYPE_COUNT = 2;
    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_HEADER = 1;

    public SelectionAdapter(Context context) {
        this.context = context;
    }

    public void addSelectionBean(SelectionBean selectionBean) {
        this.selectionBean.getIssueList().addAll(selectionBean.getIssueList());
        buildBean();
    }

    public void setSelectionBean(SelectionBean selectionBean) {
        this.selectionBean = selectionBean;
        buildBean();

    }

    public void buildBean() {
        selectionListBeen = new ArrayList<>();
        for (int i = 0; i < selectionBean.getIssueList().size(); i++) {
            for (int j = 0; j < selectionBean.getIssueList().get(i).getItemList().size(); j++) {
                String type = selectionBean.getIssueList().get(i).getItemList().get(j).getType();
                String text = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getText();
                String title = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getTitle();
                String category = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getCategory();
                int duration = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getDuration();
                String description = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getDescription();
                String playUrl = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getPlayUrl();
                try {
                    String imageUrl = selectionBean.getIssueList().get(i)
                            .getItemList().get(j).getData().getCover().getDetail();
                    String blurredUrl = selectionBean.getIssueList().get(i)
                            .getItemList().get(j).getData().getCover().getBlurred();
                    int collectionCount = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getConsumption().getCollectionCount();
                    int shareCount = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getConsumption().getShareCount();
                    int replyCount = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getConsumption().getReplyCount();
                    SelectionListBean bean = new SelectionListBean(type, text, title, category, imageUrl, description, playUrl,
                            blurredUrl, duration, collectionCount, shareCount, replyCount);
                    selectionListBeen.add(bean);
                } catch (Exception e) {
                    SelectionListBean bean = new SelectionListBean(type, text, title, category, null, description, playUrl,
                            null, duration, 0, 0, 0);
                    selectionListBeen.add(bean);

                }
            }
        }


        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return selectionListBeen == null ? 0 : selectionListBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return selectionListBeen == null ? null : selectionListBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if ("video".equals(selectionListBeen.get(position).getType())) {
            return TYPE_VIDEO;
        } else {
            return TYPE_HEADER;
        }

    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        VideoViewHolder videoViewHolder = null;
        HeaderViewHolder headerViewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_VIDEO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_selection_video, parent, false);
                    videoViewHolder = new VideoViewHolder(convertView);
                    convertView.setTag(videoViewHolder);
                    break;
                case TYPE_HEADER:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_selection_header, parent, false);
                    headerViewHolder = new HeaderViewHolder(convertView);
                    convertView.setTag(headerViewHolder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_VIDEO:
                    videoViewHolder = (VideoViewHolder) convertView.getTag();
                    break;
                case TYPE_HEADER:
                    headerViewHolder = (HeaderViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case TYPE_VIDEO:
                videoViewHolder.titleTv.setText(selectionListBeen.get(position).getTitle());
                videoViewHolder.categoryTv.setText("#" + selectionListBeen.get(position).getCategory());
                int times = selectionListBeen.get(position).getDuration();
                videoViewHolder.timeTv.setText(times / 600 + "" + times % 600 / 60 + "'" + times % 60 / 10 + "" + times % 60 % 10 + "''");
                Glide.with(context).load(selectionListBeen.get(position).getImageUrl()).into(videoViewHolder.imageView);
                videoViewHolder.videoLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SelectionDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("selectionListBean", selectionListBeen);
                        intent.putExtra("position", position);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });
                break;
            case TYPE_HEADER:
                headerViewHolder.headerText.setText(selectionListBeen.get(position).getText());
//                Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/lobster.ttf");
//                headerViewHolder.headerText.setTypeface(typeface);
                break;
        }

        return convertView;
    }

    class VideoViewHolder {
        private TextView titleTv, categoryTv, timeTv;
        private ImageView imageView;
        private RelativeLayout videoLayout;

        public VideoViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.video_title);
            categoryTv = (TextView) view.findViewById(R.id.video_category);
            imageView = (ImageView) view.findViewById(R.id.video_icon);
            timeTv = (TextView) view.findViewById(R.id.video_time);
            videoLayout = (RelativeLayout) view.findViewById(R.id.video_layout);
        }
    }

    class HeaderViewHolder {
        private TextView headerText;

        public HeaderViewHolder(View view) {
            headerText = (TextView) view.findViewById(R.id.text_header);
        }
    }
}
