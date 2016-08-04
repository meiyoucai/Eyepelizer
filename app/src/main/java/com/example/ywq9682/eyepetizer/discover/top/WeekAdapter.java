package com.example.ywq9682.eyepetizer.discover.top;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.Utils;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.selection.SelectionDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/29.
 */
public class WeekAdapter extends BaseAdapter {
    private WeekBean weekBean;
    private Context context;
    private ArrayList<SelectionListBean> selectionListBean;
    private String time;


    public WeekAdapter(Context context) {
        this.context = context;
    }

    public void setWeekBean(WeekBean weekBean) {
        this.weekBean = weekBean;
        notifyDataSetChanged();
        buildBean();

    }

    private void buildBean() {
        selectionListBean = new ArrayList<>();

        for (int i = 0; i < weekBean.getItemList().size(); i++) {


           // String type = weekBean.getIssueList().get(i).getItemList().get(j).getType();
           // String text = weekBean.getIssueList().get(i).getItemList().get(j).getData().getText();
            String title = weekBean.getItemList().get(i).getData().getTitle();
            String category = weekBean.getItemList().get(i).getData().getCategory();
            int duration = weekBean.getItemList().get(i).getData().getDuration();
            String times = duration / 600 + "" + duration % 600 / 60 + "'" + duration % 60 / 10 + "" + duration % 60 % 10 + "''";
            String description = weekBean.getItemList().get(i).getData().getDescription();
            String playUrl = weekBean.getItemList().get(i).getData().getPlayUrl();

            //String image = weekBean.getItemList().get(i).getData().getCover().getFeed();
            int id = weekBean.getItemList().get(i).getData().getId();

                String imageUrl = weekBean.getItemList().get(i).getData().getCover().getDetail();
                String blurredUrl = weekBean.getItemList().get(i)
                      .getData().getCover().getBlurred();
                int collectionCount = weekBean.getItemList().get(i).getData().getConsumption().getCollectionCount();
                int shareCount = weekBean.getItemList().get(i).getData().getConsumption().getShareCount();
                int replyCount = weekBean.getItemList().get(i).getData().getConsumption().getReplyCount();

                SelectionListBean bean = new SelectionListBean(null, null, title, times, category,imageUrl, description, playUrl,
                        blurredUrl, null, collectionCount, shareCount, replyCount, 0, id);
                selectionListBean.add(bean);

        }
    }


    @Override
    public int getCount() {
        return weekBean == null ? 0 : weekBean.getItemList().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_time_data, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(weekBean.getItemList().get(i).getData().getCover().getFeed()).into(viewHolder.imageView);
        viewHolder.textViewRecord.setText(weekBean.getItemList().get(i).getData().getCategory());
        int duration = weekBean.getItemList().get(i).getData().getDuration();
        time = duration / 60 + " ' " + duration % 60 + " '' ";
        viewHolder.textViewTime.setText(time);
        //viewHolder.textViewTitle.setText(weekBean.getItemList().get(i).getData().getName());
        viewHolder.textviewContent.setText(weekBean.getItemList().get(i).getData().getTitle());
        //  viewHolder.relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(Utils.getScreenWidth(context), (int) (Utils.getScreenHeight(context)*0.1f)));
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SelectionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("selectionListBean", selectionListBean);
                intent.putExtra("position", i);
//                        intent.putExtra("top", top);
//                        intent.putExtra("heigh", heigh);
//                        intent.putExtra("width", width);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        ViewGroup.LayoutParams layoutParams = viewHolder.relativeLayout.getLayoutParams();
        layoutParams.width = Utils.getScreenWidth(context);
        layoutParams.height = (int) (Utils.getScreenHeight(context) * 0.45f);
        viewHolder.relativeLayout.setLayoutParams(layoutParams);

        return view;
    }


    class ViewHolder {
        ImageView imageView;
        TextView textviewContent;
        TextView textViewTime;
        TextView textViewRecord;
        RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            textviewContent = (TextView) view.findViewById(R.id.author_time_tv);
            imageView = (ImageView) view.findViewById(R.id.author_time_image);
            textViewTime = (TextView) view.findViewById(R.id.author_time_time);
            textViewRecord = (TextView) view.findViewById(R.id.author_time_record);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.time_relative);


        }

    }
}
