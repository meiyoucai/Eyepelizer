package com.example.ywq9682.eyepetizer.my.collect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.selection.CollectBean;

import java.util.List;

/**
 * Created by dllo on 16/8/1.
 */
public class CollectAdapter extends BaseAdapter {


    private Context context;
    private List<CollectBean> collectBeen;

    public void setCollectBeen(List<CollectBean> collectBeen) {
        this.collectBeen = collectBeen;
        notifyDataSetChanged();
    }

    public CollectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return collectBeen == null ? 0 : collectBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return collectBeen.size()>0&&collectBeen!=null?collectBeen.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.item_selection_video, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.titleTv.setText(collectBeen.get(i).getTitleTv());
        viewHolder.categoryTv.setText("#" + collectBeen.get(i).getCategoryTv());
        viewHolder.timeTv.setText(collectBeen.get(i).getTimeTv());
        Glide.with(context).load(collectBeen.get(i).getImageView()).into(viewHolder.imageView);
        return view;
    }

    class ViewHolder {
        private TextView titleTv, categoryTv, timeTv;
        private ImageView imageView;

        public ViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.video_title);
            categoryTv = (TextView) view.findViewById(R.id.video_category);
            imageView = (ImageView) view.findViewById(R.id.video_icon);
            timeTv = (TextView) view.findViewById(R.id.video_time);

        }
    }
}
