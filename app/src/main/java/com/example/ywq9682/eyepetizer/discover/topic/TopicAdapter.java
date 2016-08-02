package com.example.ywq9682.eyepetizer.discover.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ywq9682.eyepetizer.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/7/30.
 */
public class TopicAdapter extends BaseAdapter {


    private TopicBean topicBean;
    private Context context;

    public TopicAdapter(Context context) {
        this.context = context;
    }

    public void setTopicBean(TopicBean topicBean) {
        this.topicBean = topicBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return topicBean==null?0:topicBean.getItemList().size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_topic_image, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(topicBean.getItemList().get(i).getData().getImage()).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "跳到对应的网页 ,", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    class ViewHolder {
        ImageView imageView;


        public ViewHolder(View view) {

            imageView = (ImageView) view.findViewById(R.id.item_topic_imageview);
            ;


        }

    }


}
