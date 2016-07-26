package com.example.perk.guidetest0702;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HActivity extends AppCompatActivity {

    Toolbar toolbar;
    WebView webView;
    ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressdialog.setMessage("帮助信息加载中");
                progressdialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressdialog.dismiss();
            }
        });
        webView.loadUrl("http://www.yunrfid.com/public/document/nfclock/index.html");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
