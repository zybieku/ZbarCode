package com.znq.zbarcode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressLint("SetJavaScriptEnabled")
public class ResultsActivity extends Activity {
    private static final String TAG = "ResultsActivity";

    private TextView mTxtTitle;
    private WebView mWebView;
    private TextView mTxtContent;

    private String mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result1);

        ImageView mImgBack = (ImageView) findViewById(R.id.img_back);
        mTxtTitle = (TextView) findViewById(R.id.scan_title);
        mWebView = (WebView) findViewById(R.id.web_view);
        mTxtContent = (TextView) findViewById(R.id.tv_result);

        if (getIntent() != null) {
            mResult = getIntent().getStringExtra(CaptureActivity.EXTRA_STRING);
        }

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (mResult.startsWith("http") || mResult.startsWith("https")) {
            mWebView.setVisibility(View.VISIBLE);
            mTxtContent.setVisibility(View.GONE);
            loadUrl();
        } else {
            mWebView.setVisibility(View.GONE);
            mTxtContent.setVisibility(View.VISIBLE);
            mTxtTitle.setText("扫描结果");
            mTxtContent.setText(mResult);
        }
    }

    private void loadUrl() {
        mWebView.loadUrl(mResult);
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        // 设置setWebChromeClient对象
        mWebView.setWebChromeClient(wvcc);
        mWebView.setWebViewClient(wvc);
    }


    WebChromeClient wvcc = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mTxtTitle.setText(title);
        }

    };

    ProgressDialog _dialog;
    // 创建WebViewClient对象
    WebViewClient wvc = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
            mWebView.loadUrl(url);
            // 消耗掉这个事件。Android中返回True的即到此为止吧
            // 事件就会不会冒泡传递了，我们称之为消耗掉
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            _dialog = new ProgressDialog(ResultsActivity.this);
            _dialog.setMessage(getString(R.string.loading));
            _dialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            _dialog.dismiss();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (_dialog != null) {
                _dialog.dismiss();
                _dialog=null;
            }
            mTxtTitle.setVisibility(View.GONE);
            mTxtContent.setVisibility(View.VISIBLE);
            mTxtContent.setText("链接有问题");
        }
    };
}
