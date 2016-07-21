package com.example.ywq9682.eyepetizer.my;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseFragment;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private ImageView headImage;
    private PopupWindow popupWindow;
    private  ImageView  mMap;


    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {

        popupWindow = new PopupWindow();
        headImage = (ImageView) view.findViewById(R.id.head_image);
        mMap= (ImageView) view.findViewById(R.id.map);
        headImage.setOnClickListener(this);
        mMap.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_image:
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);

                if (popupWindow != null || !popupWindow.isShowing()) {
                    popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    View view1 = LayoutInflater.from(context).inflate(R.layout.change_headimage_popwindow, null);
                    TextView headimageTv = (TextView) view1.findViewById(R.id.change_headimage);
                    TextView cancelTv = (TextView) view1.findViewById(R.id.cancel);
                    headimageTv.setOnClickListener(this);
                    cancelTv.setOnClickListener(this);
                    popupWindow.setContentView(view1);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setFocusable(false);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.showAtLocation(view1, Gravity.BOTTOM, 0, 0);
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 0.6f;
                    getActivity().getWindow().setAttributes(lp);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                            lp.alpha = 1f;
                            getActivity().getWindow().setAttributes(lp);
                        }
                    });
                }
                break;
            case R.id.map:



                break;


        }
    }
}
