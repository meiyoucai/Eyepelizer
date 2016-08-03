package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.SelectionBean;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.selection.SelectionDetailActivity;
import com.example.ywq9682.eyepetizer.selection.SelectionWebActivity;
import com.example.ywq9682.eyepetizer.tools.Window;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/19.
 */
public class SelectionAdapter extends BaseAdapter {
    private SelectionBean selectionBean;
    private ArrayList<SelectionListBean> selectionListBean;
    private Context context;

    private static final int TYPE_COUNT = 3;
    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_BANNER = 2;


    public SelectionAdapter(Context context) {
        this.context = context;
    }

    public void addSelectionBean(SelectionBean selectionBean) {
        this.selectionBean.getIssueList().addAll(selectionBean.getIssueList());
        buildBean();
        notifyDataSetChanged();
    }

    public void setSelectionBean(SelectionBean selectionBean) {
        this.selectionBean = selectionBean;
        buildBean();
        notifyDataSetChanged();
    }

    public void buildBean() {
        selectionListBean = new ArrayList<>();
        for (int i = 0; i < selectionBean.getIssueList().size(); i++) {
            int count = selectionBean.getIssueList().get(i).getCount();
            for (int j = 0; j < selectionBean.getIssueList().get(i).getItemList().size(); j++) {
                String type = selectionBean.getIssueList().get(i).getItemList().get(j).getType();
                String text = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getText();
                String title = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getTitle();
                String category = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getCategory();
                int duration = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getDuration();
                String times = duration / 600 + "" + duration % 600 / 60 + "'" + duration % 60 / 10 + "" + duration % 60 % 10 + "''";
                String description = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getDescription();
                String playUrl = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getPlayUrl();

                String image = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getImage();
                int id = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getId();

                try {
                    String imageUrl = selectionBean.getIssueList().get(i)
                            .getItemList().get(j).getData().getCover().getDetail();
                    String blurredUrl = selectionBean.getIssueList().get(i)
                            .getItemList().get(j).getData().getCover().getBlurred();

                    int collectionCount = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getConsumption().getCollectionCount();
                    int shareCount = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getConsumption().getShareCount();
                    int replyCount = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getConsumption().getReplyCount();
                    SelectionListBean bean = new SelectionListBean(type, text, title, times, category, imageUrl, description, playUrl,
                            blurredUrl, null, collectionCount, shareCount, replyCount, count, id);

                    selectionListBean.add(bean);

                } catch (Exception e) {
                    SelectionListBean bean = new SelectionListBean(type, text, title, times, category, null, description, playUrl,
                            null, image, 0, 0, 0, count, id);
                    selectionListBean.add(bean);

                }
            }
        }
        notifyDataSetChanged();
        EventBus.getDefault().post(selectionListBean);
    }

    @Override
    public int getCount() {
        return selectionListBean == null ? 0 : selectionListBean.size();
    }

    @Override
    public Object getItem(int position) {
        return selectionListBean == null ? null : selectionListBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if ("video".equals(selectionListBean.get(position).getType())) {
            return TYPE_VIDEO;
        } else if ("textHeader".equals(selectionListBean.get(position).getType())) {
            return TYPE_HEADER;
        } else {
            return TYPE_BANNER;
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
        BannerViewHolder bannerViewHolder = null;
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
                case TYPE_BANNER:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_selection_banner, parent, false);
                    bannerViewHolder = new BannerViewHolder(convertView);
                    convertView.setTag(bannerViewHolder);
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
                case TYPE_BANNER:
                    bannerViewHolder = (BannerViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case TYPE_VIDEO:
                videoViewHolder.titleTv.setText(selectionListBean.get(position).getTitle());
                videoViewHolder.categoryTv.setText("#" + selectionListBean.get(position).getCategory());
                videoViewHolder.timeTv.setText(selectionListBean.get(position).getTime());
                Glide.with(context).load(selectionListBean.get(position).getImageUrl()).into(videoViewHolder.imageView);
                videoViewHolder.videoLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selectionListBean.size() == position + 1) {
                            Intent intentRefresh = new Intent(context.getPackageName() + "selectionRefresh");
                            context.sendBroadcast(intentRefresh);
                        }

                        Intent intent = new Intent(context, SelectionDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("selectionListBean", selectionListBean);
                        intent.putExtra("position", position);
                        intent.putExtras(bundle);
                        context.startActivity(intent);

                    }
                });
                break;
            case TYPE_HEADER:
                headerViewHolder.headerText.setText(selectionListBean.get(position).getText());
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lobster.ttf");
                headerViewHolder.headerText.setTypeface(typeface);
                break;
            case TYPE_BANNER:
                Glide.with(context).load(selectionListBean.get(position).getImage()).into(bannerViewHolder.bannerImage);
                bannerViewHolder.bannerImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SelectionWebActivity.class);
                        context.startActivity(intent);
                    }
                });
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
            videoLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Window.getDisplayHeight(context) * 0.4f)));
        }
    }

    class HeaderViewHolder {
        private TextView headerText;

        public HeaderViewHolder(View view) {
            headerText = (TextView) view.findViewById(R.id.text_header);
        }
    }

    class BannerViewHolder {
        private ImageView bannerImage;

        public BannerViewHolder(View view) {
            bannerImage = (ImageView) view.findViewById(R.id.banner_image);
        }
    }
}
