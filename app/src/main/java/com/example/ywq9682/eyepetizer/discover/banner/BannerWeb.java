package com.example.ywq9682.eyepetizer.discover.banner;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

/**
 * Created by dllo on 16/7/29.
 */
public class BannerWeb extends BaseActivity {
    private WebView webView;

    @Override
    public int setLayout() {
        return R.layout.banner_webview;
    }

    @Override
    public void initView() {

        webView = (WebView) findViewById(R.id.banner_webviewv);

        String url = "http://www.wandoujia.com/eyepetizer/collection.html?name=president&shareable=true";
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);


    }

    @Override
    public void initData() {

    }
}
