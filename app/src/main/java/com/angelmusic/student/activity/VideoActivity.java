package com.angelmusic.student.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.angelmusic.stu.utils.Log;
import com.angelmusic.student.R;
import com.angelmusic.student.base.BaseMidiActivity;
import com.angelmusic.student.core.ActionBean;
import com.angelmusic.student.core.ActionProtocol;
import com.angelmusic.student.core.ActionResolver;
import com.angelmusic.student.core.MelodyU;
import com.angelmusic.student.core.NoteInfo;
import com.angelmusic.student.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;

import static com.angelmusic.student.R.id.rl_video;
import static com.angelmusic.student.core.MelodyU.d_color_1;
import static com.angelmusic.student.core.MelodyU.d_color_2;
import static com.angelmusic.student.core.MelodyU.d_color_3;
import static com.angelmusic.student.core.MelodyU.d_color_4;
import static com.angelmusic.student.core.MelodyU.d_duringtime_1;
import static com.angelmusic.student.core.MelodyU.d_duringtime_2;
import static com.angelmusic.student.core.MelodyU.d_duringtime_3;
import static com.angelmusic.student.core.MelodyU.d_duringtime_4;
import static com.angelmusic.student.core.MelodyU.d_note_1;
import static com.angelmusic.student.core.MelodyU.d_note_2;
import static com.angelmusic.student.core.MelodyU.d_note_3;
import static com.angelmusic.student.core.MelodyU.d_note_4;
import static com.angelmusic.student.core.MelodyU.d_starttime_1;
import static com.angelmusic.student.core.MelodyU.d_starttime_2;
import static com.angelmusic.student.core.MelodyU.d_starttime_3;
import static com.angelmusic.student.core.MelodyU.d_starttime_4;

/**
 * 1- 钢琴发音
 * 2- 钢琴亮灯
 * 3- 钢琴静音
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
    @BindView(R.id.rl_score)
    RelativeLayout rlScore;


    private MidiOutputDevice mOutputDevice;


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
        mOutputDevice = getMidiOutputDevice();
        setUIType(R.id.rl_loading);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vv.stopPlayback();
        stopTempleLight();
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
        //vv.setVideoURI(Uri.parse(Utils.getVideoPath() + "hehe.mp4"));
    }

    @OnClick({rl_video, R.id.activity_course})
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


    //------------公共课程逻辑start----------------------------------------------------------------------------------------------------------------

    private int currentPlayIndex = 0;

    /****** 消息入口 ******/
    @Override
    protected void handleMsg(Message action) {
        doAction((String) action.obj);
    }


    /****** 1|2|3  消息体封装类 ******/
    private ActionBean ab;

    private void doAction(String str) {

        /****** 开始新的一节， 重置各种状态 ******/
        resetLight();

        Log.e("kaka", "----------action code------- " + str);
        ab = ActionResolver.getInstance().resolve(str);
        if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_COURSE) {
            if (ab.getCodeByPositon(2) == 0) {
                VideoActivity.this.finish();
            }
        } else if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_VEDIO) {
            initVedioSection();

        } else if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_SCORE) {
            resetVideo();
            initPlaySection();
        }
    }

    /****** 启动视频  是否跟灯 ******/
    public void initVedioSection() {
        COURSE_TYPE = TYPE_VEDIO;
        setUIType(R.id.rl_video);
        if (ActionProtocol.CODE_VEDIO_ON == ab.getCodeByPositon(2) || ActionProtocol.CODE_VEDIO_OFF == ab.getCodeByPositon(2)) {
            playOrPause();
        } else if (ActionProtocol.CODE_VEDIO_CHANGE == ab.getCodeByPositon(2)) {

            /******  学生端  ******/
            //是否投屏
            if(ActionProtocol.CODE_1 == ab.getCodeByPositon(5)) {
                swichPlayScr(ab.getStringByPositon(3));
                //是否亮灯
                if (1 == ab.getCodeByPositon(4)) {
                    startTemple();
                }
            }else{
                resetVideo();
                setUIType(R.id.rl_loading);
            }


            /******  教师端  ******/
/*            swichPlayScr(ab.getStringByPositon(3));
            //是否亮灯
            if (1 == ab.getCodeByPositon(4)) {
                startTemple();
            }*/

            if("1-1-3-2.mp4".equals(ab.getStringByPositon(3))) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MelodyU.getInstance().showLight(getMidiOutputDevice());
                    }
                }, 3000);
            }

        }
    }

    /****** 显示指定图谱   ******/
    public void initPlaySection() {
        COURSE_TYPE = TYPE_PLAY;
        currentPlayIndex = 0;
        setUIType(R.id.rl_score);
        showTopLayout((currentPlayIndex + 1) + "");

        initNoteAndLight();
    }

    //初始化音符
    private void initNoteAndLight() {
        NoteInfo nextInfo = null;

        if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_1)){
            nextInfo = new NoteInfo(39,1,MelodyU.getKeyIndex(39),true);

        }else if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_2)){
            nextInfo = new NoteInfo(39,1,MelodyU.getKeyIndex(39),true);

        }else if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_3)){
            nextInfo = new NoteInfo(39,1,MelodyU.getKeyIndex(39),true);

        }else if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_4)){
            nextInfo = new NoteInfo(39,1,MelodyU.getKeyIndex(39),true);

        }else if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_D1)){
            nextInfo = new NoteInfo(39,1,MelodyU.getKeyIndex(39),true);

        }

        MelodyU.getInstance().setNoteAndKey(this, rlScore, nextInfo.getNoteIndex(), nextInfo.isIdNoteRed(), nextInfo.getKeyIndex(), nextInfo.isIdNoteRed());
        //亮灯显示
        doLight(nextInfo);

        preInfo = nextInfo;
    }

    /****** 显示指定图谱 ******/
    private void showTopLayout(String tag) {
        //遍历viewgroup
        LinearLayout vg = null;
        int[] ls = MelodyU.getInstance().getPlayLayouts(ab.getStringByPositon(2));
        for (int i = 0; i < ls.length; i++) {
            vg = (LinearLayout) getLayoutInflater().inflate(ls[i], null);
            ViewGroup vgTop = (ViewGroup) vg.findViewById(R.id.rl_top);
            for (int n = 0; n < vgTop.getChildCount(); n++) {
                if (tag.equals((String) vgTop.getChildAt(n).getTag())) {
                    replaceLayout(rlScore, ls[i]);
                    return;
                }
            }
        }
    }

    /****** 替换布局 ******/
    private void replaceLayout(ViewGroup fu, int resId) {
        fu.removeAllViews();
        ViewGroup vg = (ViewGroup) LayoutInflater.from(this).inflate(resId, fu);
    }

    /**
     * 增加伴奏音乐
     */
    public void initMusicSection() {
        COURSE_TYPE = TYPE_MUSIC;

    }

    //上一个亮灯
    private NoteInfo preInfo = null;
    /****** 输入检测  正确则返回下一个音符信息 ******/
    private void checkInput(int note) {
        NoteInfo nextInfo = null;
        //判断对错
        if ((nextInfo = MelodyU.checkInputX(note, currentPlayIndex, ab.getStringByPositon(2))) != null) {

            /****** 循环判断 ******/
            if (currentPlayIndex == (MelodyU.searchNotes(ab.getStringByPositon(2)).size() - 1)) {
                currentPlayIndex = 0;
            } else {
                currentPlayIndex++;
            }

            //处理多页面 加载正确的页面
            showTopLayout((currentPlayIndex + 1) + "");
            //下一个音符的UI显示
            MelodyU.getInstance().setNoteAndKey(this, rlScore, nextInfo.getNoteIndex(), nextInfo.isIdNoteRed(), nextInfo.getKeyIndex(), nextInfo.isIdNoteRed());
            if(preInfo!=null){
                offLight(preInfo);
            }
            doLight(nextInfo);
            preInfo = nextInfo;

        }
    }
    /******  点亮某一个灯 ******/
    private void doLight(NoteInfo nextInfo) {
        if(mOutputDevice==null){
            return;
        }
        MelodyU.getInstance().offAllLight(mOutputDevice);
        mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(nextInfo.getNote() + 21, nextInfo.isIdNoteRed(), true));
    }

    private void offLight(NoteInfo info) {
        if(mOutputDevice==null){
            return;
        }
        mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(info.getNote() + 21, info.isIdNoteRed(), false));
    }

    private void resetLight() {
        MelodyU.getInstance().offAllLight(mOutputDevice);
        stopTempleLight();

        //这里暂停 会出现异常情况
        //vv.stopPlayback();
    }

    private void resetVideo(){
        if(vv!=null){
            vv.stopPlayback();
        }
    }

    private void startTemple() {
        if (mOutputDevice == null) {
            return;
        }
        if (tt != null) {
            tt.interrupt();
            tt = null;
        }

        if("第一课".equals(ab.getStringByPositon(6))){
            tt = new TempleThread(mOutputDevice, d_starttime_1, d_duringtime_1, d_color_1, d_note_1);
        }else if("第二课".equals(ab.getStringByPositon(6))){
            tt = new TempleThread(mOutputDevice, d_starttime_2, d_duringtime_2, d_color_2, d_note_2);
        }else if("第三课".equals(ab.getStringByPositon(6))){
            tt = new TempleThread(mOutputDevice, d_starttime_3, d_duringtime_3, d_color_3, d_note_3);
        }else if("第四课".equals(ab.getStringByPositon(6))){
            tt = new TempleThread(mOutputDevice, d_starttime_4, d_duringtime_4, d_color_4, d_note_4);
        }

        if(tt!=null) {
            flagV = true;
            tt.start();
        }
    }

    //note 21 -108 序号  钢琴按键排序从1开始
    @Override
    public void onMidiNoteOff(@NonNull MidiInputDevice sender, int cable, int channel, final int note, int velocity) {
        super.onMidiNoteOff(sender, cable, channel, note, velocity);
        if (COURSE_TYPE == TYPE_PLAY) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    checkInput(note - 21);
                }
            });

        }
    }

    @Override
    public void onMidiOutputDeviceAttached(@NonNull MidiOutputDevice midiOutputDevice) {
        super.onMidiOutputDeviceAttached(midiOutputDevice);
        Toast.makeText(this,"钢琴已连接",0).show();
        mOutputDevice = getMidiOutputDevice();
    }

    private TempleThread tt;

    private void stopTempleLight() {
        if (tt != null) {
            tt.interrupt();
            tt = null;
            flagV = false;
        }
    }

    /* 跟灯 */
    private volatile boolean flagV = true;
    class TempleThread extends Thread {
        MidiOutputDevice md;
        long[] delay = null; //时间延迟执行
        long[] dur = null;   //亮灯时间
        int[] color = null;
        int[] index = null;   //亮灯位置

        public TempleThread(MidiOutputDevice mod, long[] mDelays, long[] mdur, int[] mcolor, int[] mindex) {
            md = mod;
            delay = mDelays;
            dur = mdur;
            color = mcolor;
            index = mindex;
        }

        @Override
        public void run() {
            int xunhuan = 0;
            try {
                while (flagV) {
                    if (xunhuan > delay.length - 1) {
                        if (tt != null) {
                            tt = null;
                        }
                        return;
                    }
                    if (vv != null) {
                        int curTime = (int) vv.getCurrentPosition();
                        if (curTime > delay[xunhuan]) {
                            MelodyU.getInstance().lightTempo(md, dur, color, index);
                            xunhuan++;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    //------------公共逻辑end----------------------------------------------------------------------------------------------------------------


}
