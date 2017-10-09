package com.angelmusic.student.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.angelmusic.student.R;

import com.angelmusic.student.base.BaseMidiActivity;
import com.angelmusic.student.infobean.ActionBean;
import com.angelmusic.student.utils.Utils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 *
 *  本界面初始化只是一个单纯的准备中界面，其界面样式完全由教师端发消息来控制
 *
 *  界面样式由setLayoutStyle来控制，
 *
 *  主要有钢琴通讯处理，和教师端通信处理
 *
 *
 *
 */
public class VideoActivity extends BaseMidiActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{


    //当前消息
    private String actionMsg;
    private ActionBean ab;

    private FrameLayout fl_root;
    private RelativeLayout rl_video,rl_piano;
    private VideoView mVV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initView();
        initVV();
        initMidi();
        setUIType(R.id.rl_video);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected int setContentViewId() {
        return 0;
    }

    @Override
    protected void setTAG() {

    }

    private void initView() {

        mVV = (VideoView)findViewById(R.id.vv);

        rl_video = (RelativeLayout) findViewById(R.id.rl_video);
        rl_piano = (RelativeLayout) findViewById(R.id.rl_piano);
        fl_root = (FrameLayout) findViewById(R.id.fl_one);

    }

    /**
     * 视频插件初始化
     */
    private void initVV() {
        Vitamio.initialize(this);
        MediaController mc = new MediaController(this);
        mc.setVisibility(View.INVISIBLE);
        mVV.setMediaController(mc);
        mVV.setOnPreparedListener(this);
        mVV.setOnErrorListener(this);
        mVV.setOnCompletionListener(this);

        //mVV.setVideoURI(Uri.parse("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv"));
        mVV.setVideoURI(Uri.parse(Utils.getVideoPath()+"hehe.mp4"));
    }

    /**
     * 切换视图类型
     *
     * 1，视频类型
     * 2，画谱弹奏类型
     * 3，
     *
     *
     * @param resID
     */
    private void setUIType(int resID){
        for(int i = 0; i< fl_root.getChildCount(); i++){
            if(fl_root.getChildAt(i).getId()==resID){
                fl_root.getChildAt(i).setVisibility(View.VISIBLE);
            }else{
                fl_root.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }



    //-----------------------------------------------------------视频相关-----------------------------------------------------------------
    /**
     * 切换资源
     */
    private void swichPlayScr(String name){
        mVV.setVideoURI(Uri.parse(Utils.getVideoPath()+"hehe.mp4"));
        mVV.start();
    }
    /**
     * 播放/暂停
     */
    private void playOrPause() {
        if (mVV != null)
            if (mVV.isPlaying()) {
                mVV.pause();
            } else {
                mVV.start();
            }
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
    }
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this,"error", Toast.LENGTH_LONG).show();
        return false;
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        playOrPause();
    }
    //-----------------------------------------------------------视频相关-----------------------------------------------------------------



}
