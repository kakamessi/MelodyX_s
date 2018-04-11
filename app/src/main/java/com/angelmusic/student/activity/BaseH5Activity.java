package com.angelmusic.student.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.angelmusic.stu.network.u3d.AndroidDispatcher;
import com.angelmusic.stu.utils.Log;
import com.angelmusic.student.R;
import com.angelmusic.student.base.BaseActivity;
import com.angelmusic.student.base.BaseMidiActivity;
import com.angelmusic.student.constant.Constant;
import com.angelmusic.student.core.ActionBean;
import com.angelmusic.student.core.ActionProtocol;
import com.angelmusic.student.core.ActionResolver;
import com.angelmusic.student.utils.SharedPreferencesUtil;

import butterknife.BindView;

public class BaseH5Activity extends BaseMidiActivity {


    public static final String URL_ROOT = "file:///android_asset/question-for-student2/";

    private WebView mWebview;
    private WebSettings mWebSettings;


    //---------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentViewId() {
        return 0;
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

    private void nextQuestionImpl(){
        mWebview.loadUrl("javascript:nextQuestionJs()");
    }

    public void loadH5(){
        //mWebview.loadUrl(URL_ROOT + "questionForStudent.html");
        mWebview.loadUrl("http://10.0.0.9:1235");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWebview.loadUrl("javascript:loadQuestion()");
            }
        },1000);
    }

    public void initH5() {

        mWebview = (WebView) findViewById(R.id.webView);

        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.addJavascriptInterface(new AndroidtoJs(), "android");

        //mWebview.loadUrl(URL_ROOT + "questionForStudent.html");

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

    private ActionBean ab;
    /**
     * 收到学生端成绩消息
     * @param msg
     */
    @Override
    protected void handleMsg(Message action) {
        doAction((String) action.obj);
    }
    private void doAction(String str) {
        Log.e("kaka", "----------H5Activity code------- " + str);
        ab = ActionResolver.getInstance().resolve(str);
        if (ab.getCodeByPositon(0) == 2) {

            if(ab.getCodeByPositon(1) == 3){
                nextQuestionImpl();
            }

        }
    }

    //----非公共逻辑----------------------------------------------------------------------------------

    // 获取学生答案，发送教师端
    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void submitAnd(String args) {
            Log.e("kaka","-------------------------------submit" + args);
            String name = SharedPreferencesUtil.getString(Constant.CACHE_STUDENT_NAME,"小朋友");
            AndroidDispatcher.getInstance().sendMsg(ActionProtocol.ACTION_TEST_QUESTION + "|" +  name + "=" + args);

        }

    }


}
