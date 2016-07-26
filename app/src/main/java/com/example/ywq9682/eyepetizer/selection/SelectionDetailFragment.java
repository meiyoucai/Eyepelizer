package com.example.ywq9682.eyepetizer.selection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.SelectionDetailViewAdapter;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/21.
 */
public class SelectionDetailFragment extends BaseFragment {
    private ViewPager viewPager;
    private SelectionDetailViewAdapter selectionDetailViewAdapter;
    private int position;
    private ArrayList<SelectionListBean> selectionListBean;

    public static SelectionDetailFragment getInstance(int pos) {
        SelectionDetailFragment selectionDetailFragment = new SelectionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        selectionDetailFragment.setArguments(bundle);
        return selectionDetailFragment;

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_selection_detail;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

    }

    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
    }


    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra("position", 0);
        selectionDetailViewAdapter = new SelectionDetailViewAdapter(getContext());
        selectionListBean = getActivity().getIntent().getParcelableArrayListExtra("selectionListBean");
        selectionDetailViewAdapter.setSelectionListBean(selectionListBean);
        selectionDetailViewAdapter.setPos(position);
        viewPager.setAdapter(selectionDetailViewAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("SelectionDetailFragment", "position-----------滑动:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
