package com.example.ywq9682.eyepetizer.discover.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/7/30.
 */
public class TimeAdapter extends BaseAdapter {
   private  DiscoverGridviewBean  discoverGridviewBean;
    private Context context;
    private String time;

    public TimeAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverGridviewBean(DiscoverGridviewBean discoverGridviewBean) {
        this.discoverGridviewBean = discoverGridviewBean;
    }

    @Override
    public int getCount() {
        return discoverGridviewBean == null ? 0 : discoverGridviewBean.getItemList().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_time_data, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(discoverGridviewBean.getItemList().get(i).getData().getCover().getFeed()).into(viewHolder.imageView);
        viewHolder.textViewRecord.setText(discoverGridviewBean.getItemList().get(i).getData().getCategory());

        int duration = discoverGridviewBean.getItemList().get(i).getData().getDuration();
        time = duration / 60 + " ' " + duration % 60 + " '' ";
        viewHolder.textViewTime.setText(time);
        //viewHolder.textViewTitle.setText(weekBean.getItemList().get(i).getData().getName());
        viewHolder.textviewContent.setText(discoverGridviewBean.getItemList().get(i).getData().getTitle());
        return view;
    }


    class ViewHolder {
        ImageView imageView;
        TextView textviewContent;
        TextView textViewTime;
        TextView textViewRecord;


        public ViewHolder(View view) {
            textviewContent = (TextView) view.findViewById(R.id.author_time_tv);
            imageView = (ImageView) view.findViewById(R.id.author_time_image);
            textViewTime = (TextView) view.findViewById(R.id.author_time_time);
            textViewRecord = (TextView) view.findViewById(R.id.author_time_record);


        }

    }
}
