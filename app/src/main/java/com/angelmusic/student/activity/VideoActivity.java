package com.angelmusic.student.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.angelmusic.student.R;
import com.angelmusic.student.base.BaseMidiActivity;
import com.angelmusic.student.core.ActionBean;
import com.angelmusic.student.core.ActionProtocol;
import com.angelmusic.student.core.ActionResolver;
import com.angelmusic.student.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import static com.angelmusic.student.R.id.rl_video;

/**
 * 本界面初始化只是一个单纯的准备中界面，其界面样式完全由教师端发消息来控制
 * <p>
 * 界面样式由setLayoutStyle来控制，
 * <p>
 * 主要有钢琴通讯处理，和教师端通信处理
 */
public class VideoActivity extends BaseMidiActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {


    @BindView(R.id.vv)
    VideoView vv;
    @BindView(R.id.rl_video)
    RelativeLayout rlVideo;
    @BindView(R.id.rl_loading)
    RelativeLayout rl_loading;
    @BindView(R.id.activity_course)
    RelativeLayout activityCourse;
    @BindView(R.id.fl_one)
    FrameLayout fl_root;


    //当前消息
    private String actionMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initVV();
        initMidi();
        setUIType(R.id.rl_loading);

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
        return R.layout.activity_course;
    }

    private void initView() {


    }

    /**
     * 视频插件初始化
     */
    private void initVV() {
        Vitamio.initialize(this);
        MediaController mc = new MediaController(this);
        mc.setVisibility(View.INVISIBLE);
        vv.setMediaController(mc);
        vv.setOnPreparedListener(this);
        vv.setOnErrorListener(this);
        vv.setOnCompletionListener(this);

        //vv.setVideoURI(Uri.parse("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv"));
        vv.setVideoURI(Uri.parse(Utils.getVideoPath() + "hehe.mp4"));
    }

    /**
     * 切换视图类型
     * <p>
     * 1，视频类型
     * 2，画谱弹奏类型
     * 3，
     *
     * @param resID
     */
    private void setUIType(int resID) {
        for (int i = 0; i < fl_root.getChildCount(); i++) {
            if (fl_root.getChildAt(i).getId() == resID) {
                fl_root.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                fl_root.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }


    @Override
    protected void handleMsg(Message action) {
        doAction((String) action.obj);

    }

    /**
     * 处理消息逻辑 如下课，切换视频等逻辑
     */
    private ActionBean ab;
    private void doAction(String str) {
        ab = ActionResolver.getInstance().resolve(str);

        int c2 = Integer.parseInt(ab.getCodes()[1]);
        int c3 = Integer.parseInt(ab.getCodes()[2]);

        if (c2 == ActionProtocol.CODE_ACTION_COURSE) {
            if(c3==0){
                VideoActivity.this.finish();
            }
        } else if (c2 == ActionProtocol.CODE_ACTION_VEDIO) {

            playOrPause();
            setUIType(R.id.rl_video);

        } else if (c2 == ActionProtocol.CODE_ACTION_NOTE) {

        }
    }


    //-----------------------------------------------------------视频相关-----------------------------------------------------------------

    /**
     * 切换资源
     */
    private void swichPlayScr(String name) {
        vv.setVideoURI(Uri.parse(Utils.getVideoPath() + "hehe.mp4"));
        vv.start();
    }

    /**
     * 播放/暂停
     */
    private void playOrPause() {
        if (vv != null)
            if (vv.isPlaying()) {
                vv.pause();
            } else {
                vv.start();
            }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        playOrPause();
    }

    @OnClick({rl_video,R.id.activity_course})
    public void onClick(View view) {
        switch (view.getId()) {
            case rl_video:
                break;

            case R.id.activity_course:
                break;
        }
    }

    //-----------------------------------------------------------视频相关-----------------------------------------------------------------


}
