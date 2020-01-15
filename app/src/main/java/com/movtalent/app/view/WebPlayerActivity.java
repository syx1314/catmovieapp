package com.movtalent.app.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.movtalent.app.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebPlayerActivity extends BaseActivity {


    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra("url");
        webview.loadUrl("http://jx.catmovie.cn/?v="+url);
        initWebViewSettings();
    }

    @Override
    protected void initView() {

    }
    private void initWebViewSettings() {


        final WebSettings webSetting = webview.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(false);
        webSetting.setDatabaseEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
//        webSetting.setBlockNetworkImage(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                webSetting.setBlockNetworkImage(true);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.e("加载url-》》》》",url);
                if (url.startsWith("http://")||url.startsWith("https://")){
                    view.loadUrl(url);
                }

                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                webView.scrollTo(webView.getWebScrollX(),0);
                webSetting.setBlockNetworkImage(false);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_player;
    }


}
