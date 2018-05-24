package com.angelmusic.student.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import com.angelmusic.stu.network.u3d.AndroidDispatcher;
import com.angelmusic.stu.utils.Log;
import com.angelmusic.student.R;
import com.angelmusic.student.base.BaseActivity;
import com.angelmusic.student.base.BaseMidiActivity;
import com.angelmusic.student.constant.Constant;
import com.angelmusic.student.core.ActionBean;
import com.angelmusic.student.core.ActionProtocol;
import com.angelmusic.student.core.MelodyU;
import com.angelmusic.student.utils.SharedPreferencesUtil;

import butterknife.BindView;
import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;

import static android.os.Build.VERSION_CODES.M;
import static com.angelmusic.student.activity.VideoActivity.COURSE_TYPE;
import static com.angelmusic.student.activity.VideoActivity.TYPE_PLAY;

public class H5QukuActivity extends BaseMidiActivity {

    //   10.0.0.8:1236/    http://s.huangzhongdalv.com
    public static final String URL_ROOT = "http://s.huangzhongdalv.com";

    @BindView(R.id.webView1)
    WebView mWebview;
    @BindView(R.id.ib_back)
    ImageButton ib_back;
    
    WebSettings mWebSettings;

    private MidiOutputDevice mOutputDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initView();
        initMidi();

        mOutputDevice = getMidiOutputDevice();
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
        mWebview.addJavascriptInterface(new H5QukuActivity.PianoAction(), "android");

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

        //MelodyU.getInstance().stopBeatThread(null);
    }

    public class PianoAction extends Object {

        // 定义JS需要调用的方法  亮灯熄灯
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        //亮灯
        public void setLight(int index,int isRed, int isOn){

            boolean iRed = false;
            boolean iOn = false;

            if(isRed==1){
                iRed = true;
            }else{
                iRed = false;
            }

            if(isOn==1){
                iOn = true;
            }else{
                iOn = false;
            }

            if(null!=mOutputDevice) {
                mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(index, iRed, iOn));
            }

        }

    }




    /**
     * 收到学生端成绩消息
     * @param msg
     */
    @Override
    protected void handleMsg(Message action) {

    }












    //--------------------------------------------------------------------------------------------------------
    @Override
    public void onMidiOutputDeviceAttached(@NonNull MidiOutputDevice midiOutputDevice) {
        super.onMidiOutputDeviceAttached(midiOutputDevice);
        Toast.makeText(this, "钢琴已连接", 0).show();
        mOutputDevice = getMidiOutputDevice();

        //MelodyU.getInstance().startBeatThread(mOutputDevice);
    }

    //note 21 -108 序号
    @Override
    public void onMidiNoteOn(@NonNull MidiInputDevice sender, int cable, int channel, final int note, int velocity) {
        super.onMidiNoteOn(sender, cable, channel, note, velocity);
        // 我调用js方法
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebview.loadUrl("javascript:backStatusJS('" + note + "')");
            }
        });
    }


    public void testLight(int index,int isRed, int isOn){

        boolean iRed = false;
        boolean iOn = false;

        if(isRed==1){
            iRed = true;
        }else{
            iRed = false;
        }

        if(isOn==1){
            iOn = true;
        }else{
            iOn = false;
        }

        if(null!=mOutputDevice) {
            mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(index, iRed, iOn));
        }

    }



}




