package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.bean.SelectionBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/20.
 */
public class SelectionAdapter extends BaseAdapter{
    private SelectionBean selectionBean;
    private ArrayList<SelectionListBean> selectionListBeen;
    private Context context;

    private static final int TYPE_COUNT = 2;
    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_HEADER = 1;

    public SelectionAdapter(Context context) {
        this.context = context;
    }

    public void setSelectionBean(SelectionBean selectionBean) {
        this.selectionBean = selectionBean;
        selectionListBeen = new ArrayList<>();
        for (int i = 0; i < selectionBean.getIssueList().size(); i++) {
            for (int j = 0; j < selectionBean.getIssueList().get(i).getItemList().size(); j++) {
                String type = selectionBean.getIssueList().get(i).getItemList().get(j).getType();
                String text = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getText();
                String title = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getTitle();
                String category = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getCategory();
                int duration = selectionBean.getIssueList().get(i).getItemList().get(j).getData().getDuration();
                try {
                    String imageUrl = selectionBean.getIssueList().get(i)
                            .getItemList().get(j).getData().getCover().getDetail();
                    SelectionListBean bean = new SelectionListBean(type, text, title, category, imageUrl, duration);
                    selectionListBeen.add(bean);
                } catch (Exception e) {
                    SelectionListBean bean = new SelectionListBean(type, text, title, category, null, duration);
                    selectionListBeen.add(bean);

                }
            }
        }
//        int startTime = 90;
//        String time = new SimpleDateFormat("mm:ss").format(1200);
//        Log.d("SelectionAdapter", time);

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
    public View getView(int position, View convertView, ViewGroup parent) {
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

        public VideoViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.video_title);
            categoryTv = (TextView) view.findViewById(R.id.video_category);
            imageView = (ImageView) view.findViewById(R.id.video_icon);
            timeTv = (TextView) view.findViewById(R.id.video_time);
        }
    }

    class HeaderViewHolder {
        private TextView headerText;

        public HeaderViewHolder(View view) {
            headerText = (TextView) view.findViewById(R.id.text_header);
        }
    }
}
