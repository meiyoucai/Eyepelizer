package com.example.ywq9682.eyepetizer.selection;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

/**
 * Created by dllo on 16/7/29.
 */
public class SelectionWebActivity extends BaseActivity {
    private WebView mWebView;

    @Override
    public int setLayout() {
        return R.layout.activity_selection_web;
    }

    @Override
    public void initView() {
        mWebView = (WebView) findViewById(R.id.selection_web_view);
    }

    @Override
    public void initData() {
        String url = "http://www.wandoujia.com/eyepetizer/collection.html?name=summer&shareable=true";
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
    }
}
