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
import android.widget.ImageView;
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
import com.angelmusic.student.utils.FileUtil;
import com.angelmusic.student.utils.Utils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;

import static android.R.attr.name;
import static com.angelmusic.student.R.id.rl_video;
import static com.angelmusic.student.base.App.init;
import static com.angelmusic.student.core.MelodyU.d_color_1;
import static com.angelmusic.student.core.MelodyU.d_color_2;
import static com.angelmusic.student.core.MelodyU.d_color_27;
import static com.angelmusic.student.core.MelodyU.d_color_3;
import static com.angelmusic.student.core.MelodyU.d_color_4;
import static com.angelmusic.student.core.MelodyU.d_color_5;
import static com.angelmusic.student.core.MelodyU.d_duringtime_1;
import static com.angelmusic.student.core.MelodyU.d_duringtime_2;
import static com.angelmusic.student.core.MelodyU.d_duringtime_27;
import static com.angelmusic.student.core.MelodyU.d_duringtime_3;
import static com.angelmusic.student.core.MelodyU.d_duringtime_4;
import static com.angelmusic.student.core.MelodyU.d_duringtime_5;
import static com.angelmusic.student.core.MelodyU.d_note_1;
import static com.angelmusic.student.core.MelodyU.d_note_2;
import static com.angelmusic.student.core.MelodyU.d_note_27;
import static com.angelmusic.student.core.MelodyU.d_note_3;
import static com.angelmusic.student.core.MelodyU.d_note_4;
import static com.angelmusic.student.core.MelodyU.d_note_5;
import static com.angelmusic.student.core.MelodyU.d_starttime_1;
import static com.angelmusic.student.core.MelodyU.d_starttime_2;
import static com.angelmusic.student.core.MelodyU.d_starttime_27;
import static com.angelmusic.student.core.MelodyU.d_starttime_3;
import static com.angelmusic.student.core.MelodyU.d_starttime_4;
import static com.angelmusic.student.core.MelodyU.d_starttime_5;

/**
 * 1- 钢琴发音
 * 2- 钢琴亮灯
 * 3- 钢琴静音
 */
public class VideoActivity extends BaseH5Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {


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
    @BindView(R.id.rl_teacher_screen)
    ImageView rlTeacherScreen;


    private MidiOutputDevice mOutputDevice;


    //当前消息
    private String actionMsg;
    public static int COURSE_TYPE = -1;
    public static final int TYPE_VEDIO = 1;
    public static final int TYPE_PLAY = 2;
    public static final int TYPE_IMG = 3;
    public static final int TYPE_ANSWER = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVV();
        initMidi();
        initH5();
        mOutputDevice = getMidiOutputDevice();
        setUIType(R.id.rl_loading);


//        doInnerAction();
//        rlScore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doInnerAction();
//            }
//        });
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                //FileUtil.delAllFile(Utils.getVideoPath());
            }
        }).start();
        //MelodyU.getInstance().stopBeatThread(mOutputDevice);
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

//        try {
//            if(ab.getCodeByPositon(7)==1){
//                //进入h5
//                setUIType(R.id.webView);
//                loadH5(ab.getStringByPositon(3));
//            }
//        }catch (Exception e){
//
//        }

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

    /******
     * 消息入口
     ******/
    @Override
    protected void handleMsg(Message action) {
        super.handleMsg(action);
        doAction((String) action.obj);
    }


    /******
     * 1|2|3  消息体封装类
     ******/
    private ActionBean ab;

    private void doAction(String str) {

        /****** 开始新的一节， 重置各种状态 ******/
        resetLight();

        Log.e("kaka", "----------action code------- " + str);
        ab = ActionResolver.getInstance().resolve(str);

        if(ab.getCodeByPositon(0) == 1) {

            if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_COURSE) {
                if (ab.getCodeByPositon(2) == 0) {
                    VideoActivity.this.finish();
                }
            } else if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_VEDIO) {
                initVedioSection();

            } else if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_SCORE) {

//            if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_6)){
//                ab.setStringByPositon(2,MelodyU.PIC_NAME_30);
//            }else if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_7)){
//                ab.setStringByPositon(2,MelodyU.PIC_NAME_31);
//            }else if(ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_8)){
//                ab.setStringByPositon(2,MelodyU.PIC_NAME_32);
//            }

                resetVideo();
                initPlaySection();
            } else if (ab.getCodeByPositon(1) == ActionProtocol.CODE_ACTION_IMG) {
                //图片界面
                resetVideo();
                initImgSection();

            }else if (ab.getCodeByPositon(1) == 5) {
                //图片界面
                resetVideo();
                initAnswerSection();

            }

        }
    }

    private void initAnswerSection() {
        COURSE_TYPE = TYPE_ANSWER;
        setUIType(R.id.webView);    
        loadH5(ab.getStringByPositon(2));
    }

    /******
     * 启动视频  是否跟灯
     ******/
    public void initVedioSection() {
        COURSE_TYPE = TYPE_VEDIO;
        //setUIType(R.id.rl_video);  学生端黑屏bug
        if (ActionProtocol.CODE_VEDIO_ON == ab.getCodeByPositon(2) || ActionProtocol.CODE_VEDIO_OFF == ab.getCodeByPositon(2)) {
            playOrPause();
        } else if (ActionProtocol.CODE_VEDIO_CHANGE == ab.getCodeByPositon(2)) {

            /******  学生端  ******/
            //是否投屏
            if (ActionProtocol.CODE_1 == ab.getCodeByPositon(5)) {
                setUIType(R.id.rl_video);
                swichPlayScr(ab.getStringByPositon(3));
                //是否亮灯
                if (1 == ab.getCodeByPositon(4)) {
                    startTemple();
                }
            } else {
                resetVideo();
                setUIType(R.id.rl_loading);
            }


            /******  教师端  ******/
/*            swichPlayScr(ab.getStringByPositon(3));
            //是否亮灯
            if (1 == ab.getCodeByPositon(4)) {
                startTemple();
            }*/

            if ("1-1-3-2.mp4".equals(ab.getStringByPositon(3))) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MelodyU.getInstance().showLight(getMidiOutputDevice());
                    }
                }, 3000);
            }

        }
    }

    /**
     * 增加图片类型
     */
    public void initImgSection() {
        COURSE_TYPE = TYPE_IMG;
        setUIType(R.id.rl_teacher_screen);
        rlTeacherScreen.setImageBitmap(Utils.getBitMap(ab.getStringByPositon(2)));
    }

    /******
     * 显示指定图谱
     ******/
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
//
//        if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_1)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_2)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_3)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_4)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_5)) {
//            nextInfo = new NoteInfo(46, 1, MelodyU.getKeyIndex(46), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_6)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_7)) {
//            nextInfo = new NoteInfo(46, 1, MelodyU.getKeyIndex(46), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_8)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        } else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_D1)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//
//        // 9 - 16 课  1 1 0   111   111
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_9_1)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_9_2)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_10)) {
//            nextInfo = new NoteInfo(38, 1, MelodyU.getKeyIndex(38), false);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_11)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_12)) {
//            nextInfo = new NoteInfo(46, 1, MelodyU.getKeyIndex(46), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_13)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_14)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_15)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_16)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//
//        }
//        else if (ab.getStringByPositon(2).equals(MelodyU.PIC_NAME_19)) {
//            nextInfo = new NoteInfo(39, 1, MelodyU.getKeyIndex(39), true);
//            NoteInfo nii = new NoteInfo(27, 1, MelodyU.getKeyIndex(27), true);
//            nextInfo.setInfo(nii);
//
//        }

        nextInfo = MelodyU.getInfoByIndex(currentPlayIndex,ab.getStringByPositon(2));

        MelodyU.getInstance().setNoteAndKey(this, rlScore, nextInfo.getNoteIndex(), nextInfo.isIdNoteRed(), nextInfo.getKeyIndex(), nextInfo.isIdNoteRed());
        //亮灯显示
        doLight(nextInfo);
        preInfo = nextInfo;
    }

    /******
     * 显示指定图谱
     ******/
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

    /******
     * 显示指定图谱
     ******/
    private void showTopLayout(String tag,String name) {
        //遍历viewgroup
        LinearLayout vg = null;
        int[] ls = MelodyU.getInstance().getPlayLayouts(name);
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

    /******
     * 替换布局
     ******/
    private void replaceLayout(ViewGroup fu, int resId) {
        fu.removeAllViews();
        ViewGroup vg = (ViewGroup) LayoutInflater.from(this).inflate(resId, fu);
    }

    //上一个亮灯
    private NoteInfo preInfo = null;

    /******
     *
     *   1，  判断是否输入正确
     *   2，  关闭之前的灯
     *   3，  点亮之后的灯
     *
     *
     ******/
    private void checkInput(int note) {

        if(ab==null){
            return;
        }

        NoteInfo currentInfo = null;
        NoteInfo nextInfo = null;

        /****** 输入判断 ******/
        if (MelodyU.checkInputXX(note, currentPlayIndex, ab.getStringByPositon(2))) {

            currentInfo = MelodyU.getInfoByIndex(currentPlayIndex,ab.getStringByPositon(2));
            nextInfo = MelodyU.getInfoByIndex(currentPlayIndex+1,ab.getStringByPositon(2));

            //标识已经使用状态
            currentInfo.setUsedStatu(note);

            //当前环节没有结束，熄灭一个灯    双手弹奏
            if(!currentInfo.isFinish()){
                offLight(currentInfo.getNoteByid(note),false);

            }else{

                //下一个
                offLight(currentInfo,true);
                doLight(nextInfo);
                preInfo = nextInfo;

                /****** 处理多页面 加载正确的页面 TAG搜索******/
                if(currentPlayIndex + 2 > MelodyU.searchNotes(ab.getStringByPositon(2)).size()){
                    showTopLayout("2");
                }else{
                    showTopLayout((currentPlayIndex + 2) + "");
                }

                //下一个音符的UI显示
                if(nextInfo.getInfo()==null){
                    MelodyU.getInstance().setNoteAndKey(this, rlScore, nextInfo.getNoteIndex(), nextInfo.isIdNoteRed(), nextInfo.getKeyIndex(), nextInfo.isIdNoteRed());
                }else{
                    MelodyU.getInstance().setNoteAndKey1(this, rlScore, nextInfo);
                }

            }

            //状态重置
            if(currentInfo.isFinish()){
                if (currentPlayIndex == (MelodyU.searchNotes(ab.getStringByPositon(2)).size() - 1)) {
                    currentPlayIndex = 0;
                }else{
                    currentPlayIndex++;
                }
                currentInfo.reSet();
            }

        }
    }

    /******
     * 点亮某一个灯
     ******/
    private void doLight(NoteInfo nextInfo) {
        if (mOutputDevice == null) {
            return;
        }
        MelodyU.getInstance().offAllLight(mOutputDevice);
        mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(nextInfo.getNote() + 21, nextInfo.isIdNoteRed(), true));
        if(nextInfo.getInfo()!=null){
            mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(nextInfo.getInfo().getNote() + 21, nextInfo.getInfo().isIdNoteRed(), true));
        }
    }

    private void offLight(NoteInfo info, boolean isCheckSon) {
        if (mOutputDevice == null || info == null) {
            return;
        }
        //mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(info.getNote() + 21, info.isIdNoteRed(), false));
        mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(info.getNote() + 21, true, false));
        mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(info.getNote() + 21, false, false));
        if(isCheckSon && info.getInfo()!=null){
            //mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(info.getInfo().getNote() + 21, info.getInfo().isIdNoteRed(), false));
            mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(info.getInfo().getNote() + 21, true, false));
            mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(info.getInfo().getNote() + 21, false, false));
        }
    }

    private void resetLight() {
        MelodyU.getInstance().offAllLight(mOutputDevice);
        stopTempleLight();
        offLight(preInfo,true);
        //这里暂停 会出现异常情况
        //vv.stopPlayback();
    }

    private void resetVideo() {
        if (vv != null) {
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

        if ("第一课".equals(ab.getStringByPositon(6))) {
            tt = new TempleThread(mOutputDevice, d_starttime_1, d_duringtime_1, d_color_1, d_note_1);
        } else if ("第二课".equals(ab.getStringByPositon(6))) {
            tt = new TempleThread(mOutputDevice, d_starttime_2, d_duringtime_2, d_color_2, d_note_2);
        } else if ("第三课".equals(ab.getStringByPositon(6))) {
            tt = new TempleThread(mOutputDevice, d_starttime_3, d_duringtime_3, d_color_3, d_note_3);
        } else if ("第四课".equals(ab.getStringByPositon(6))) {
            tt = new TempleThread(mOutputDevice, d_starttime_4, d_duringtime_4, d_color_4, d_note_4);
        } else if ("第五课".equals(ab.getStringByPositon(6))) {
            //tt = new TempleThread(mOutputDevice, d_starttime_5, d_duringtime_5, d_color_5, d_note_5);
        } else if ("第六课".equals(ab.getStringByPositon(6))) {
            //tt = new TempleThread(mOutputDevice, d_starttime_4, d_duringtime_4, d_color_4, d_note_4);
        } else if ("第七课".equals(ab.getStringByPositon(6))) {
            //tt = new TempleThread(mOutputDevice, d_starttime_4, d_duringtime_4, d_color_4, d_note_4);
        } else if ("第八课".equals(ab.getStringByPositon(6))) {
            //tt = new TempleThread(mOutputDevice, d_starttime_4, d_duringtime_4, d_color_4, d_note_4);
        }else if("第二十七课".equals(ab.getStringByPositon(6))){
            tt = new TempleThread(mOutputDevice, d_starttime_27, d_duringtime_27, d_color_27, d_note_27);
        }

        if (tt != null) {
            flagV = true;
            tt.start();
        }
    }

    //note 21 -108 序号  钢琴按键排序从1开始
    @Override
    public void onMidiNoteOn(@NonNull MidiInputDevice sender, int cable, int channel, final int note, int velocity) {
        super.onMidiNoteOn(sender, cable, channel, note, velocity);
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
        Toast.makeText(this, "钢琴已连接", 0).show();
        mOutputDevice = getMidiOutputDevice();
        MelodyU.getInstance().startBeatThread(mOutputDevice);
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
            } catch (InterruptedException e) {
            }
        }
    }

    //------------公共逻辑end----------------------------------------------------------------------------------------------------------------


    //----------测试方法-----------------

    int i = 0;
    void doInnerAction(){
        //String name = MelodyU.PICS[(i++)%MelodyU.PICS.length];
        String name = MelodyU.PIC_NAME_22;
        String ac = "1|3|" + name;
        doAction(ac);
    }

}
