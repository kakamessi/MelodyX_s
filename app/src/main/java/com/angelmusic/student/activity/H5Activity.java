package com.angelmusic.student.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.angelmusic.student.constant.Constant;
import com.angelmusic.student.core.ActionBean;
import com.angelmusic.student.core.ActionProtocol;
import com.angelmusic.student.core.ActionResolver;
import com.angelmusic.student.utils.SharedPreferencesUtil;

import java.util.Random;

import butterknife.BindView;

public class H5Activity extends BaseActivity {


    public static final String URL_ROOT = "file:///android_asset/question-for-student2/";

    @BindView(R.id.webView1)
    WebView mWebview;
    WebSettings mWebSettings;

    private String questionIndex = "";

    private void nextQuestionImpl(){
        mWebview.loadUrl("javascript:switchQuestion('" + questionIndex +"')");
    }

    private void init() {
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.addJavascriptInterface(new AndroidtoJs(), "android");

        mWebview.loadUrl(URL_ROOT + "questionForStudent.html");

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
        doAction((String) action.obj);
    }
    private void doAction(String str) {
        Log.e("kaka", "----------H5Activity code------- " + str);
        ab = ActionResolver.getInstance().resolve(str);
        if (ab.getCodeByPositon(0) == 2) {
            if(ab.getCodeByPositon(1) == 3){
                questionIndex = ab.getStringByPositon(2);
                nextQuestionImpl();
            }else if(ab.getCodeByPositon(1) == 0){
                finish();
            }else if(ab.getCodeByPositon(1) == 2){
                //教师端收到成绩  发送给h5
                mWebview.loadUrl("javascript:callJS('" + ab.getStringByPositon(2) + "')");
            }
        }
    }

    //----非公共逻辑----------------------------------------------------------------------------------

    // 获取学生答案，发送教师端
    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void submit(String args) {
            Log.e("kaka","-------------------------------submit" + args);
            String name = SharedPreferencesUtil.getString(Constant.CACHE_STUDENT_NAME,"小朋友");
            AndroidDispatcher.getInstance().sendMsg(ActionProtocol.ACTION_TEST_QUESTION + "|" +  name + "=" + args);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_h5;
    }

}
