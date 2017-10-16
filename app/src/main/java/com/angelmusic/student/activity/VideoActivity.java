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
 *
 *  1- 钢琴发音
 *  2- 钢琴亮灯
 *  3- 钢琴静音
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
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
    public static int COURSE_TYPE = -1;
    public static final int TYPE_VEDIO = 1;
    public static final int TYPE_PLAY = 2;
    public static final int TYPE_MUSIC = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @OnClick({rl_video,R.id.activity_course})
    public void onClick(View view) {
        switch (view.getId()) {
            case rl_video:
                break;

            case R.id.activity_course:
                break;
        }
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
        if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_COURSE) {
            if (ab.getCodeByPositon(2) == 0) {
                VideoActivity.this.finish();
            }
        } else if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_VEDIO) {
            initVedioSection();
        } else if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_SCORE) {

        }
    }


    /***
     * 播放视频
     */
    public void initVedioSection() {
        COURSE_TYPE = TYPE_VEDIO;
        setUIType(R.id.rl_video);
        if(ActionProtocol.CODE_VEDIO_ON==ab.getCodeByPositon(2) || ActionProtocol.CODE_VEDIO_OFF==ab.getCodeByPositon(2) ){
            playOrPause();
        }else if(ActionProtocol.CODE_VEDIO_CHANGE==ab.getCodeByPositon(2)){
            swichPlayScr(ab.getStringByPositon(3));
        }
    }

    /**
     * 准备曲谱， 判断钢琴输入对错
     */
    public void initPlaySection() {
        COURSE_TYPE = TYPE_PLAY;

    }

    /**
     * 增加伴奏音乐
     */
    public void initMusicSection() {
        COURSE_TYPE = TYPE_MUSIC;

    }


    //-----------------------------------------------------------视频相关-----------------------------------------------------------------

    /**
     * 切换资源
     */
    private void swichPlayScr(String name) {
        vv.setVideoURI(Uri.parse(Utils.getVideoPath() + name));
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

    //-----------------------------------------------------------视频相关-----------------------------------------------------------------


}
