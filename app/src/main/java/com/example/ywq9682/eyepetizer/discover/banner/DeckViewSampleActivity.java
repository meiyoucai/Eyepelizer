package com.example.ywq9682.eyepetizer.discover.banner;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;
import com.example.ywq9682.eyepetizer.Datum;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.bean.DiscoverSearchBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by dllo on 16/8/3.
 */
public class DeckViewSampleActivity extends BaseActivity {

    DeckView<Datum> mDeckView;
    private DiscoverSearchBean d;
    private ArrayList<DiscoverSearchBean> beans;
    Drawable mDefaultHeaderIcon;
    ArrayList<Datum> mEntries;
    private String url = "http://baobab.wandoujia.com/api/v3/recommend?udid=cd1ee9c5b44e4f9487a505a4fe31d" +
            "dcb07441cc8&vc=121&vn=2.3.5&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_ch" +
            "annel=eyepetizer_xiaomi_market&system_version_code=23";
    // Placeholder for when the image is being downloaded
    Bitmap mDefaultThumbnail;
    private FrameLayout frameLayout;
    // Retain position on configuration change
    // imageSize to pass to http://lorempixel.com
    int scrollToChildIndex = -1, imageSize = 500;
    // SavedInstance bundle keys
    final String CURRENT_SCROLL = "current.scroll", CURRENT_LIST = "current.list";


    @Override
    public int setLayout() {

        return R.layout.activity_deck_view_sample;
    }

    @Override
    public void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deck_view_sample, menu);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save current scroll and the list
        int currentChildIndex = mDeckView.getCurrentChildIndex();
        outState.putInt(CURRENT_SCROLL, currentChildIndex);
        outState.putParcelableArrayList(CURRENT_LIST, mEntries);

        super.onSaveInstanceState(outState);
    }


    private static int generateUniqueKey() {
        return ++KEY;
    }

    private static int KEY = 0;


    @Override
    public void initData() {

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                d = gson.fromJson(response, DiscoverSearchBean.class);
                if (mEntries == null) {
                    mEntries = new ArrayList<>();
                    for (int i = 0; i < d.getItemList().size(); i++) {
                        Datum datum = new Datum();
                        datum.setId(generateUniqueKey());
                        datum.setLink(d.getItemList().get(i).getData().getCover().getFeed());
                        datum.setHeaderTitle(d.getItemList().get(i).getData().getTitle());
                        // Log.d("DeckViewSampleActivity", d.getItemList().get(i).getData().getTitle());
                        mEntries.add(0, datum);
                    }
                    mDeckView = new DeckView<Datum>(DeckViewSampleActivity.this);
                    frameLayout.addView(mDeckView);
                }
                // Callback implementation
                DeckView.Callback<Datum> deckViewCallback = new DeckView.Callback<Datum>() {
                    @Override
                    public ArrayList<Datum> getData() {
                        return mEntries;
                    }

                    @Override
                    public void loadViewData(WeakReference<DeckChildView<Datum>> dcv, Datum item) {
                        loadViewDataInternal(item, dcv);
                    }

                    @Override
                    public void unloadViewData(Datum item) {
                        Picasso.with(DeckViewSampleActivity.this).cancelRequest(item.target);
                    }

                    @Override
                    public void onViewDismissed(Datum item) {
                        mEntries.remove(item);
                        mDeckView.notifyDataSetChanged();
                    }

                    @Override
                    public void onItemClick(Datum item) {
                        Toast.makeText(DeckViewSampleActivity.this,
                                "Item with title: '" + item.headerTitle + "' clicked",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNoViewsToDeck() {
                        Toast.makeText(DeckViewSampleActivity.this,
                                "No views to show",
                                Toast.LENGTH_SHORT).show();
                    }

                };

                mDeckView.initialize(deckViewCallback);

                if (scrollToChildIndex != -1)

                {
                    mDeckView.post(new Runnable() {
                        @Override
                        public void run() {
                            // Restore scroll position
                            mDeckView.scrollToChild(scrollToChildIndex);
                        }
                    });
                }
            }

        });

    }

    void loadViewDataInternal(final Datum datum,
                              final WeakReference<DeckChildView<Datum>> weakView) {
        // datum.target can be null
        Picasso.with(DeckViewSampleActivity.this).cancelRequest(datum.target);

        datum.target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Pass loaded Bitmap to view
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, bitmap,
                            mDefaultHeaderIcon, datum.headerTitle, Color.DKGRAY);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                // Loading failed. Pass default thumbnail instead
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultThumbnail,
                            mDefaultHeaderIcon, datum.headerTitle + " Failed", Color.DKGRAY);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // Pass the default thumbnail for now. It will
                // be replaced once the target Bitmap has been loaded
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultThumbnail,
                            mDefaultHeaderIcon, "Loading...", Color.DKGRAY);
                }
            }
        };

        // Begin loading
        Picasso.with(DeckViewSampleActivity.this).load(datum.link).into(datum.target);
    }


}