package com.angelmusic.student.core;

/**
 * Created by wangshuai on 2017/10/9.
 */

public class ActionProtocol {

    //--准备上课
    public static final String ACTION_COURSE_START = "1|1|1";
    //--播放视频
    public static final String ACTION_VEDIO_ON = "1|2|1";
    //--暂停视频
    public static final String ACTION_VEDIO_PAUSE = "1|2|0";
    //--画谱界面
    public static final String ACTION_COURSE_NOTE = "1|3";
    //--下课
    public static final String ACTION_COURSE_STOP = "1|1|0";



    //--准备上课
    public static final int CODE_ACTION_COURSE = 1;
    //--播放视频
    public static final int CODE_ACTION_VEDIO = 2;
    //--画谱界面
    public static final int CODE_ACTION_NOTE = 3;


    //--学生连接通知
    public static final int CODE_ACTION_CONNECTED = 401;



}