package com.example.ywq9682.eyepetizer.discover.banner;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.DiscoverSearchBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by dllo on 16/7/29.
 */
public class BannerSearchActivity extends AppCompatActivity {
    /**
     * This variable is the container that will host our cards
     */
    private CardContainer mCardContainer;
    private String url = "http://baobab.wandoujia.com/api/v3/recommend?udid=cd1ee9c5b44e4f9487a505a4fe31d" +
            "dcb07441cc8&vc=121&vn=2.3.5&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";
    private CardModel cardModel;
    private SimpleCardStackAdapter adapter;
    private boolean flag = true;
    private int times;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_andtinder);
        mCardContainer = (CardContainer) findViewById(R.id.layoutview);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);
        mCardContainer.setOrientation(Orientations.Orientation.Disordered);
        Resources r = getResources();

        //  cardModel = new CardModel("Title1", "Description goes here", r.getDrawable(R.mipmap.picture1));
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                final DiscoverSearchBean discoverSearchBean = gson.fromJson(response, DiscoverSearchBean.class);
                // cardModel.get

                adapter = new SimpleCardStackAdapter(BannerSearchActivity.this);
                for (DiscoverSearchBean.ItemListBean itemListBean : discoverSearchBean.getItemList()) {
                    String url = itemListBean.getData().getCover().getFeed();
                    Glide.with(BannerSearchActivity.this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (resource != null&& flag) {


                                adapter.add(new CardModel("Title1", "Description goes here", new BitmapDrawable(resource)));
                                times++;
                                if(times >= discoverSearchBean.getItemList().size()){

                mCardContainer.setAdapter(adapter);
                                }
                            }
                        }
                    });
                }


            }
        });


    }
}
