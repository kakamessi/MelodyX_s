package com.angelmusic.student.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.angelmusic.student.R;
import com.angelmusic.student.base.BaseActivity;
import com.angelmusic.student.core.ActionBean;

import butterknife.BindView;

public class H5QukuActivity extends BaseActivity {


    public static final String URL_ROOT = "http://s.huangzhongdalv.com";

    @BindView(R.id.webView1)
    WebView mWebview;
    @BindView(R.id.ib_back)
    ImageButton ib_back;
    
    WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initView();
    }

    private void initView() {

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_quku_h5;
    }

    private void init() {
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        mWebview.loadUrl(URL_ROOT);

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
            }
            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                }
            }
        });
        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                if(mWebview!=null){
                    mWebview.loadUrl("javascript:switchQuestion(" + "'1'" + ")");
                }

            }
        });
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }

    private ActionBean ab;
    /**
     * 收到学生端成绩消息
     * @param msg
     */
    @Override
    protected void handleMsg(Message action) {

    }


}
