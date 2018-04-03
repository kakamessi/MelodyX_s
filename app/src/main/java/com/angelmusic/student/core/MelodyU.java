package com.angelmusic.student.core;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.angelmusic.student.R;

import java.util.ArrayList;
import java.util.Random;

import jp.kshoji.driver.midi.device.MidiOutputDevice;


/**
 * Created by wangshuai on 2017/10/18.
 */

public class MelodyU {

    //----钢琴指令-------------------------------------------------------------------------------------------
    //心跳
    public static byte[] ACTION_KEEP_ALIVE ={(byte) 0xF0, 0x4D, 0x4C, 0x4C, 0x02, 0x00, 0x00, (byte) 0xF7};
    //开启静音协议
    public static byte[] ACTION_MUTE ={ 0x1b, (byte)0xbF, 0x07, 0x00};
    //关闭静音
    public static byte[] ACTION_UNMUTE = { 0x1b, (byte)0xbF, 0x07, 0x7f };
    /*开启打击乐*/
    public static byte[] open_djy = { 0x04, (byte)0xf0, 0x4d, 0x4c, 0x04, 0x4c, 0x53, 0x01, 0x06, 0x00, 0x00, (byte)0xf7 };
    /*关闭打击乐*/
    public static byte[] close_djy = { 0x04, (byte)0xf0, 0x4d, 0x4c, 0x04, 0x4c, 0x53, 0x00, 0x06, 0x00, 0x00, (byte)0xf7 };


    //-----服务器返回的图片名称---------------------------------------------------------------------------------------------
    public static final String PIC_NAME_1 = "1-1-3-4.png";
    public static final String PIC_NAME_2 = "1-2-3-5.png";
    public static final String PIC_NAME_3 = "1-3-3-2.png";
    public static final String PIC_NAME_4 = "1-4-3-2.png";

    public static final String PIC_NAME_5 = "1-5-3-2.png";
    public static final String PIC_NAME_6 = "1-6-3-2.png";
    public static final String PIC_NAME_7 = "1-7-3-2.png";
    public static final String PIC_NAME_8 = "1-8-3-2.png";

    public static final String PIC_NAME_9_1 = "1-9-3-4.png";
    public static final String PIC_NAME_9_2 = "1-9-3-5.png";
    public static final String PIC_NAME_10 = "1-10-3-2.png";
    public static final String PIC_NAME_11 = "1-11-3-3.png";
    public static final String PIC_NAME_12 = "1-12-3-2.png";
    public static final String PIC_NAME_13 = "1-13-3-2.png";
    public static final String PIC_NAME_14 = "1-14-3-3.png";
    public static final String PIC_NAME_15 = "1-15-3-3.png";
    public static final String PIC_NAME_16 = "1-16-3-3.png";
    public static final String PIC_NAME_17 = "1-17-3-3.png";
    public static final String PIC_NAME_18 = "1-18-3-3.png";
    public static final String PIC_NAME_19 = "1-19-3-2.png";

    public static final String PIC_NAME_20 = "1-20-3-2.png";
    public static final String PIC_NAME_21 = "1-21-3-3.png";
    public static final String PIC_NAME_22 = "1-22-3-3.png";
    public static final String PIC_NAME_23 = "1-23-3-2.png";
    public static final String PIC_NAME_24 = "1-24-3-2.png";
    public static final String PIC_NAME_25 = "1-25-3-2.png";
    public static final String PIC_NAME_26 = "1-26-3-3.png";
    public static final String PIC_NAME_27 = "1-27-3-3.png";
    public static final String PIC_NAME_28 = "1-28-3-3.png";
    public static final String PIC_NAME_29 = "1-29-3-3.png";
    public static final String PIC_NAME_30 = "1-30-3-3.png";
    public static final String PIC_NAME_31 = "1-31-3-3.png";
    public static final String PIC_NAME_32 = "1-32-3-3.png";


    //示范课
    public static final String PIC_NAME_D1 = "D1-1-5-1.png";

    public static String[] PICS  = {PIC_NAME_1,PIC_NAME_2,PIC_NAME_3,PIC_NAME_4,PIC_NAME_5,PIC_NAME_6,PIC_NAME_7,PIC_NAME_8,PIC_NAME_9_1,PIC_NAME_9_2,
            PIC_NAME_10,PIC_NAME_11,PIC_NAME_12,PIC_NAME_13,PIC_NAME_14,PIC_NAME_15,PIC_NAME_16,PIC_NAME_17,PIC_NAME_18,PIC_NAME_19,PIC_NAME_20,
            PIC_NAME_21,PIC_NAME_22,PIC_NAME_23,PIC_NAME_24,PIC_NAME_25,PIC_NAME_26,PIC_NAME_27,PIC_NAME_28,PIC_NAME_29,PIC_NAME_30,
            PIC_NAME_31,PIC_NAME_32,};

    //-----数据段start---------------------------------------------------------------------------------------------
    public static int[] t = {1240,1000,170,1000,170,
                                     170, 170,1090,180,180,
                                     180,180,180,180,180,
                                     180,180,180,180,180,
                                     150,180,180,180,180,
                                     180,180,180,180,180,
                                     180,180,180,};

    public static long[] d_starttime_1 = {5000};
    public static long[] d_duringtime_1 = {2000,2000,2000,2000,2000,2000,2000,2000,
            2000,2000,2000,2000,2000*4,2000,2000,2000,2000,
            2000,2000,2000,2000,2000,2000,2000,2000,2000*4,};
    public static int[] d_note_1 = {39,40,41,42,43,44,45,46,
            47 ,48 ,49 ,50 ,51 , 51 , 50 ,49 ,48,
            47 ,46 ,45, 44, 43 ,42 ,41 ,40 ,39};
    public static int[] d_color_1 = {1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,1,
            1, 1, 1, 1, 1, 1, 1, 1,1};


    public static long[] d_starttime_2 = {6720};
    public static long[] d_duringtime_2 = {697,697,697*2, 697,697,697*2, 697,697,697*2, 697,697,697*2,};
    public static int[] d_note_2 = {39,43,46,   43,43,46,   43,39,43,  46,43,39};
    public static int[] d_color_2 = {1, 1, 1,    1,1,1,  1,1,1,  1,1,1};

    public static long[] d_starttime_3 = {20000,36760};
    public static long[] d_duringtime_3 = {697,697,697, 697,697*4,    697, 697,697,697, 697*4};
    public static int[] d_note_3 = {39,39,46,46,43,   46,46,43,43,39};
    public static int[] d_color_3 = {1, 1, 1, 1,1,   1, 1, 1,1,1};

    public static long[] d_starttime_4 = {13960,38080,61880};
    public static long[] d_duringtime_4 = {1000,1000,1000*2, 1000,1000,1000*2, 1000,1000,1000*2, 1000,1000,1000*2,};
    public static int[] d_note_4 = {39,39,43,    43,43,46,    48,46,43,   41,43,39};
    public static int[] d_color_4 = {1, 1, 1, 1,    1, 1, 1, 1,  1,1,1,1};

    public static long[] d_starttime_5 = {17720,};
    public static long[] d_duringtime_5 = {t[4], t[4], t[4]*2, t[4], t[4], t[4]*2, t[4], t[4], t[4], t[4],
            t[4], t[4], t[4]*2, t[4], t[4], t[4]*2, t[4], t[4], t[4]*2, t[4],
            t[4], t[4], t[4], t[4]*4, t[4],t[4], t[4], t[4], t[4], t[4],
            t[4]*2, t[4], t[4], t[4], t[4],t[4], t[4], t[4]*2, t[4], t[4],
            t[4]*2, t[4], t[4], t[4]*2, t[4],t[4], t[4], t[4], t[4]*4, };
    public static int[] d_note_5 = {46, 43, 43, 44, 41, 41,    39, 41, 43, 44, 46, 46, 46,
            46, 43, 43,  44, 41, 41, 39, 43, 46, 46, 43,
            41, 41, 41, 41, 41, 43, 44,    43, 43, 43, 43, 43, 44, 46,
            46, 43, 43,  44, 41, 41, 39, 43, 46, 46, 39};
    public static int[] d_color_5 = null;

    public static long[] d_starttime_6 = {};
    public static long[] d_duringtime_6 = {t[5], t[5], t[5], t[5], t[5], t[5]*2, t[5], t[5], t[5], t[5],
            t[5], t[5], t[5], t[5], t[5], t[5], t[5], t[5], t[5], t[5],
            t[5], t[5], t[5], t[5], t[5],t[5], t[5], t[5], t[5], t[5],
            t[5], t[5], t[5], t[5], t[5],t[5], t[5], t[5], t[5], t[5],
            t[5], t[5]};
    public static int[] d_note_6 = {39, 39, 46, 46, 48, 48, 46,   44, 44, 43, 43, 41, 41, 39,   46, 46, 44, 44, 43, 43, 41,
            46, 46, 44, 44, 43, 43, 41,   39, 39, 46, 46, 48, 48, 46,  44, 44, 43, 43, 41, 41, 39};
    public static int[] d_color_6 = null;

    public static long[] d_starttime_7 = {};
    public static long[] d_duringtime_7 = {t[6], t[6], t[6], t[6], t[6], t[6], t[6], t[6], t[6], t[6],
            t[6], t[6], t[6], t[6], t[6], t[6], t[6], t[6], t[6], t[6],
            t[6], t[6], t[6], t[6], t[6],t[6], t[6], t[6]};
    public static int[] d_note_7 = {46, 46, 46, 46,    46, 44, 43, 41,  39, 39, 41, 41,  43, 44, 46,
            46, 46, 46, 46,    46, 44, 43, 41,   39, 41, 43, 41,        39};
    public static int[] d_color_7 = null;

    public static long[] d_starttime_8 = {};
    public static long[] d_duringtime_8 = {t[7], t[7], t[7], t[7], t[7], t[7], t[7], t[7], t[7], t[7],
            t[7], t[7], t[7], t[7], t[7], t[7], t[7], t[7], t[7], t[7],
            t[7], t[7]};
    public static int[] d_note_8 = {39, 41, 43,     41, 43, 44,    48, 46, 46, 48,   43,
            48, 46, 44,    44, 43, 41,     39, 41, 43, 41,   39};
    public static int[] d_color_8 = null;

    public static long[] d_starttime_9_1 = {};
    public static long[] d_duringtime_9_1 = {t[8], t[8], t[8], t[8], t[8], t[8], t[8], t[8], t[8], t[8],
            t[8], t[8], t[8], t[8], t[8], t[8]};
    public static int[] d_note_9_1 = {39,39,39,39,  39,39,39,39, 39,39,39,39,  39,39,39,39,};
    public static int[] d_color_9_1 = {1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0};

    public static long[] d_starttime_9_2 = {};
    public static long[] d_duringtime_9_2 = {};
    public static int[] d_note_9_2 = {39,39,39,39,  39,39,39,39,};
    public static int[] d_color_9_2 = {1,1,0,0,1,1,0,0,};

    public static long[] d_starttime_10 = {};
    public static long[] d_duringtime_10 = {};
    public static int[] d_note_10 = {38 ,38 ,38 ,38,  38, 38, 38,      41,41,43,41,39, 43, 43, 44, 43,  48, 46, 43, 38,38,38,43,39 };
    public static int[] d_color_10 = {0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,};

    public static long[] d_starttime_11 = {};
    public static long[] d_duringtime_11 = {t[10], t[10], t[10], t[10], t[10], t[10], t[10], t[10], t[10], t[10],
            t[10], t[10], t[10], t[10], t[10], t[10], t[10], t[10], t[10], t[10],
            t[10], t[10], t[10], t[10], t[10],t[10], t[10], t[10], t[10], t[10],
            t[10]};
    public static int[] d_note_11 = {39, 41, 43, 41,39,41,41, 39,  38,  36,  38,  39, 38, 38,
            39, 41, 43,    41,  39,   39, 41, 43,    41,  39,   43, 41, 39, 41, 43, 39, 39};
    public static int[] d_color_11 = {1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,};

    public static long[] d_starttime_12 = {};
    public static long[] d_duringtime_12 = {t[11], t[11], t[11], t[11], t[11], t[11], t[11], t[11], t[11], t[11],
            t[11], t[11], t[11], t[11], t[11], t[11], t[11]};
    public static int[] d_note_12 = {46, 43, 39,  46,   46, 43, 39,  46, 46,43,39,34,46,43,46,43,39};
    public static int[] d_color_12 = {1,1,1,1,1,1,1,1,1,1,1,  0,1, 1,1,1,1,};

    public static long[] d_starttime_13 = {};
    public static long[] d_duringtime_13 = {};
    public static int[] d_note_13 = {39, 41, 43, 39, 41, 43, 44, 34, 36, 38, 34, 36, 38, 39, 39, 41, 43, 39,   43, 44, 46, 34, 36, 38, 34, 36, 38, 39,};
    public static int[] d_color_13 = {1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1, 0,0,0,0,0,0,0,};

    public static long[] d_starttime_14 = {};
    public static long[] d_duringtime_14 = {};
    public static int[] d_note_14 = {39, 41, 43, 44,  43, 46,   44, 43, 41, 43,  39, 41, 43,  39, 41, 43, 44,  43, 41,   39, 41, 43, 41,  39, 39, };
    public static int[] d_color_14 = {1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,};

    public static long[] d_starttime_15 = {};
    public static long[] d_duringtime_15 = {};
    public static int[] d_note_15 = {39, 41, 43, 44,    46, 44, 43, 41,    39, 43, 46,   39, 38, 36, 34,   32, 34, 36, 38,    39,  38,  39,};
    public static int[] d_color_15 = {1,1,1,1,1,1,1,1,1,1,1, 0,0,0,0,0,0,0,0,0,0,0,};

    public static long[] d_starttime_16 = {};
    public static long[] d_duringtime_16 = {};
    public static int[] d_note_16 = {39, 41, 43,   39, 41, 43,   41, 43,  44, 39, 38, 36,   34, 36, 38,   39, 38, 39, };
    public static int[] d_color_16 = {1,1,1,1,1,1,1,1,1,  0,0,0,0,0,0,0,0,0,};

    public static long[] d_starttime_17 = {};
    public static long[] d_duringtime_17 = {};
    public static int[] d_note_17 = {39,38,36,34,32,34,36,38,39,38,39,   39,41,43,44,46,44,43,41,39,43,39};
    public static int[] d_color_17 = {};

    public static long[] d_starttime_18 = {};
    public static long[] d_duringtime_18 = {};
    public static int[] d_note_18 = {34,39,41,43,34,39,41,43,44,43,41,41,   34,41,43,44,34,41,43,44,46,44,43,43,
            48,50,48,46,39,44,46,44,43,43,       41,43,41,46,43,39,36,39,41,39,39,};
    public static int[] d_color_18 = {};

    public static long[] d_starttime_19 = {};
    public static long[] d_duringtime_19 = {};
    public static int[] d_note_19 = {3927,4129,4331,4432,4634,4634,4634, 4634,4432,4331,4129,3927,3927,3927};
    public static int[] d_color_19 = {};

    public static long[] d_starttime_20 = {};
    public static long[] d_duringtime_20 = {};
    public static int[] d_note_20 = {27, 29, 31, 28, 27, 32, 32, 31, 29, 34, 32, 31, 29, 27, 29, 31, 29, 27, 32, 32, 31, 29, 34, 34, 27};
    public static int[] d_color_20 = {};

    public static long[] d_starttime_21 = {};
    public static long[] d_duringtime_21 = {};
    public static int[] d_note_21 = {3927, 4129, 4331, 4432, 4331, 4129, 4331, 4432, 4634, 4432, 4331, 4129, 3927};
    public static int[] d_color_21 = {};

    public static long[] d_starttime_22 = {};
    public static long[] d_duringtime_22 = {};
    public static int[] d_note_22 = {4327, 41, 39, 4429, 43, 41, 4631, 4429, 4327, 29, 31, 4132, 34, 32, 3931, 4129,
            4327, 41, 39, 4429, 43, 41, 4631, 44, 43, 4132, 46, 44, 4334, 4132,34, 32,3931, 34,31, 3927 };
    public static int[] d_color_22 = {};

    public static long[] d_starttime_23 = {};
    public static long[] d_duringtime_23 = {};
    public static int[] d_note_23 = {3931,4132,3931,4132,4334,4132,3931,4132,4334,3931,4334,4132,
                                     3931,4132,3931,4132,4334,4132,3931,4334,4132,4334,3931,3931};
    public static int[] d_color_23 = {};

    public static long[] d_starttime_24 = {};
    public static long[] d_duringtime_24 = {};
    public static int[] d_note_24 = {3927,43,43,4627, 46, 3927, 4129, 4331, 4432, 4634, 3927, 43, 43, 4627, 46, 4634, 4432, 4331, 3129, 3927
    };
    public static int[] d_color_24 = {};

    public static long[] d_starttime_25 = {};
    public static long[] d_duringtime_25 = {};
    public static int[] d_note_25 = {};
    public static int[] d_color_25 = {};

    public static long[] d_starttime_26 = {};
    public static long[] d_duringtime_26 = {};
    public static int[] d_note_26 = {39, 41, 43, 41, 39, 34, 36, 39, 34, 39, 41, 43, 44, 46, 39, 43, 41, 39, 38, 39};
    public static int[] d_color_26 = {};

    public static long[] d_starttime_27 = {};
    public static long[] d_duringtime_27 = {};
    public static int[] d_note_27 = {        4627, 43, 46, 43, 4631, 43, 39, 4132, 44, 43, 41, 4631, 34,
            4627, 43, 46, 43, 4631, 43, 39, 4132, 44, 43, 41, 3931, 27,
            4132, 41, 44, 44, 4334, 39, 46, 4132, 44, 43, 41, 4634,
            4627, 43, 46, 43, 4631, 43, 39, 4132, 44, 4334, 41, 3927};
    public static int[] d_color_27 = {};

    public static long[] d_starttime_28 = {};
    public static long[] d_duringtime_28 = {};
    public static int[] d_note_28 = {	51,50, 48, 46, 5139, 38, 36, 34, 4639, 48, 50, 51, 5034, 36, 38, 39,
            4638, 48, 50, 48, 4634, 36, 38, 36, 5034, 46, 4838, 5034, 5139};
    public static int[] d_color_28 = {};

    public static long[] d_starttime_29 = {};
    public static long[] d_duringtime_29 = {};
    public static int[] d_note_29 = {27, 29, 31,  29, 27, 32,  32, 31, 29,  34, 32, 31, 29,
            27, 29, 31, 29, 27, 32, 32, 31, 29, 34, 34, 27};
    public static int[] d_color_29 = {};

    public static long[] d_starttime_30 = {};
    public static long[] d_duringtime_30 = {};
    public static int[] d_note_30 = {	39,43,46,46,44,43,41,39,41,41,38,34,
            46,46,43,39,41,41,38,34,46,46,43,39,
            3927,4129,4331,4432,4634,4432,4331,4129,3927};
    public static int[] d_color_30 = {};

    public static long[] d_starttime_31 = {};
    public static long[] d_duringtime_31 = {};
    public static int[] d_note_31 = {	5139,5139,4634,4634,4836,4836,4634,5139,5139,4634,4634,4836,4836,5038,
            5139,5139,4634,4634,4836,4836,4634,4432,4836,4634,8039,4836,5038,5139};
    public static int[] d_color_31 = {};

    public static long[] d_starttime_32 = {};
    public static long[] d_duringtime_32 = {};
    public static int[] d_note_32 = {	4327,43,43,4429,43,44,4631,43,39,4327,43,43,4429,43,44,4631,
            4327,43,43,4429,43,44,4631,43,39,4327,43,43,4429,44,43,4432,41,3931 };
    public static int[] d_color_32 = {};

    public static int[] d_note_d1 = {39 ,39, 46, 46, 48, 48, 46,   44, 44, 43, 43, 41, 41, 39,
            46, 46, 44, 44, 43, 43, 41, 46, 46, 44, 44, 43, 43, 41,
            39, 39, 46, 46, 48, 48, 46,  44, 44, 43, 43, 41, 41, 39};
    public static int[] d_color_d1 = {1, 1, 1, 1,    1, 1, 1,  1, 1, 1, 1,    1, 1, 1,
            1, 1, 1, 1,    1, 1, 1,  1, 1, 1, 1,    1, 1, 1,1, 1, 1, 1,    1, 1, 1,  1, 1, 1, 1,    1, 1, 1};


    public static ArrayList<NoteInfo> course_1 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_2 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_3 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_4 = new ArrayList<>();

    public static ArrayList<NoteInfo> course_5 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_6 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_7 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_8 = new ArrayList<>();

    public static ArrayList<NoteInfo> course_9_1 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_9_2 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_10 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_11 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_12 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_13 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_14 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_15 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_16 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_17 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_18 = new ArrayList<>();

    public static ArrayList<NoteInfo> course_19 = new ArrayList<>();

    public static ArrayList<NoteInfo> course_20 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_21 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_22 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_23 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_24 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_25 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_26 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_27 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_28 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_29 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_30 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_31 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_32 = new ArrayList<>();


    public static ArrayList<NoteInfo> course_d1 = new ArrayList<>();

    static {

        //左右手
        d_color_5 = new int[d_note_5.length];
        d_color_6 = new int[d_note_6.length];
        d_color_7 = new int[d_note_7.length];
        d_color_8 = new int[d_note_8.length];
        for(int i=0; i<d_note_5.length;i++){
            d_color_5[i] = 1;
        }
        for(int i=0; i<d_note_6.length;i++){
            d_color_6[i] = 1;
        }
        for(int i=0; i<d_note_7.length;i++){
            d_color_7[i] = 1;
        }
        for(int i=0; i<d_note_8.length;i++){
            d_color_8[i] = 1;
        }

        course_1.addAll(setNoteList(d_note_1, d_color_1));
        course_2.addAll(setNoteList(d_note_2, d_color_2));
        course_3.addAll(setNoteList(d_note_3, d_color_3));
        course_4.addAll(setNoteList(d_note_4, d_color_4));

        course_5.addAll(setNoteList(d_note_5, d_color_5));
        course_6.addAll(setNoteList(d_note_6, d_color_6));
        course_7.addAll(setNoteList(d_note_7, d_color_7));
        course_8.addAll(setNoteList(d_note_8, d_color_8));

        course_9_1.addAll(setNoteList(d_note_9_1, d_color_9_1));
        course_9_2.addAll(setNoteList(d_note_9_2, d_color_9_2));
        course_10.addAll(setNoteList(d_note_10, d_color_10));
        course_11.addAll(setNoteList(d_note_11, d_color_11));
        course_12.addAll(setNoteList(d_note_12, d_color_12));
        course_13.addAll(setNoteList(d_note_13, d_color_13));
        course_14.addAll(setNoteList(d_note_14, d_color_14));
        course_15.addAll(setNoteList(d_note_15, d_color_15));
        course_16.addAll(setNoteList(d_note_16, d_color_16));
        course_17.addAll(setNoteList(d_note_17, d_color_17));
        course_18.addAll(setNoteList(d_note_18, d_color_18));
        course_19.addAll(setNoteList(d_note_19, d_color_19));

        course_20.addAll(setNoteList(d_note_20, d_color_20));
        course_21.addAll(setNoteList(d_note_21, d_color_21));
        course_22.addAll(setNoteList(d_note_22, d_color_22));
        course_23.addAll(setNoteList(d_note_23, d_color_23));
        course_24.addAll(setNoteList(d_note_24, d_color_24));
        course_25.addAll(setNoteList(d_note_25, d_color_25));
        course_26.addAll(setNoteList(d_note_26, d_color_26));
        course_27.addAll(setNoteList(d_note_27, d_color_27));
        course_28.addAll(setNoteList(d_note_28, d_color_28));
        course_29.addAll(setNoteList(d_note_29, d_color_29));
        course_30.addAll(setNoteList(d_note_30, d_color_30));
        course_31.addAll(setNoteList(d_note_31, d_color_31));
        course_32.addAll(setNoteList(d_note_32, d_color_32));

        course_d1.addAll(setNoteList(d_note_d1, d_color_d1));

    }

    //-----数据段end---------------------------------------------------------------------------------------------

    private MidiOutputDevice mOutputDevice;

    /**
     *
     *  判断 (对应谱子)的(对应音符)信息的正误
     *
     *
     * playIndex      从0开始
     *
     * @param note  音符号
     * @param resId 资源id
     * @return 返回下一个音符信息
     */
    public static NoteInfo checkInputX(int note, int playIndex, String resId) {
        NoteInfo result = null;
        ArrayList<NoteInfo> noteList = searchNotes(resId);

        if (true) {
            playIndex = playIndex % noteList.size();

            //判断正误
            if (note != noteList.get(playIndex).getNote()) {
                return null;
            }

            //返回下一个
            if (playIndex == (noteList.size() - 1)) {
                playIndex = 0;
            } else {
                playIndex++;
            }
            result = noteList.get(playIndex);
        }
        return result;
    }

    public static boolean checkInputXX(int note, int playIndex, String resId) {
        boolean result = false;

        ArrayList<NoteInfo> noteList = searchNotes(resId);
        if(noteList==null){
            return result;
        }
        playIndex = playIndex % noteList.size();

        //判断正误
        if (!noteList.get(playIndex).checkUsed(note)) {
            result = true;
        }

        return result;
    }

    public static NoteInfo getInfoByIndex(int playIndex, String resId) {

        NoteInfo result = null;
        ArrayList<NoteInfo> noteList = searchNotes(resId);

        playIndex = playIndex % noteList.size();
        result = noteList.get(playIndex);

        return result;
    }

    /**
     * 查找对应课程的 弹奏音符集
     * @param resId
     * @return
     */
    public static ArrayList<NoteInfo> searchNotes(String resId) {
        ArrayList<NoteInfo> result = null;
        if(PIC_NAME_1.equals(resId)){
            result = course_1;
        }else if(PIC_NAME_2.equals(resId)){
            result = course_2;
        }else if(PIC_NAME_3.equals(resId)){
            result = course_3;
        }else if(PIC_NAME_4.equals(resId)){
            result = course_4;
        }else if(PIC_NAME_5.equals(resId)){
            result = course_5;
        }else if(PIC_NAME_6.equals(resId)){
            result = course_6;
        }else if(PIC_NAME_7.equals(resId)){
            result = course_7;
        }else if(PIC_NAME_8.equals(resId)){
            result = course_8;
        }

        else if(PIC_NAME_9_1.equals(resId)){
            result = course_9_1;
        }else if(PIC_NAME_9_2.equals(resId)){
            result = course_9_2;
        }else if(PIC_NAME_10.equals(resId)){
            result = course_10;
        }else if(PIC_NAME_11.equals(resId)){
            result = course_11;
        }else if(PIC_NAME_12.equals(resId)){
            result = course_12;
        }else if(PIC_NAME_13.equals(resId)){
            result = course_13;
        }else if(PIC_NAME_14.equals(resId)){
            result = course_14;
        }else if(PIC_NAME_15.equals(resId)){
            result = course_15;
        }else if(PIC_NAME_16.equals(resId)){
            result = course_16;
        }else if(PIC_NAME_17.equals(resId)){
            result = course_17;
        }else if(PIC_NAME_18.equals(resId)){
            result = course_18;
        }

        else if(PIC_NAME_19.equals(resId)){
            result = course_19;
        }
        else if(PIC_NAME_20.equals(resId)){
            result = course_20;
        }else if(PIC_NAME_21.equals(resId)){
            result = course_21;
        }else if(PIC_NAME_22.equals(resId)){
            result = course_22;
        }else if(PIC_NAME_23.equals(resId)){
            result = course_23;
        }else if(PIC_NAME_24.equals(resId)){
            result = course_24;
        }else if(PIC_NAME_25.equals(resId)){
            result = course_25;
        }else if(PIC_NAME_26.equals(resId)){
            result = course_26;
        }else if(PIC_NAME_27.equals(resId)){
            result = course_27;
        }else if(PIC_NAME_28.equals(resId)){
            result = course_28;
        }else if(PIC_NAME_29.equals(resId)){
            result = course_29;
        }else if(PIC_NAME_30.equals(resId)){
            result = course_30;
        }else if(PIC_NAME_31.equals(resId)){
            result = course_31;
        }else if(PIC_NAME_32.equals(resId)){
            result = course_32;
        }

        else if(PIC_NAME_D1.equals(resId)){
            result = course_d1;
        }

        return result;
    }

    /**
     * 返回对应课程布局
     * @return
     */
    public int[] getPlayLayouts(String resName){
        int[] ls = null;
        if(resName.equals(PIC_NAME_1)){
            ls = new int[]{R.layout.include_score};

        }else if(resName.equals(PIC_NAME_2)){
            ls = new int[]{R.layout.score_2_1};

        }else if(resName.equals(PIC_NAME_3)){
            ls = new int[]{R.layout.score_3_1};

        }else if(resName.equals(PIC_NAME_4)){
            ls = new int[]{R.layout.score_4_1};

        }else if(resName.equals(PIC_NAME_5)){
            ls = new int[]{R.layout.score_5_1};

        }else if(resName.equals(PIC_NAME_6)){
            ls = new int[]{R.layout.score_6_1};

        }else if(resName.equals(PIC_NAME_7)){
            ls = new int[]{R.layout.score_7_1};

        }else if(resName.equals(PIC_NAME_8)){
            ls = new int[]{R.layout.score_8_1};

        }

        else if(resName.equals(PIC_NAME_9_1)){
            ls = new int[]{R.layout.score_9_1};

        }else if(resName.equals(PIC_NAME_9_2)){
            ls = new int[]{R.layout.score_9_2};

        }else if(resName.equals(PIC_NAME_10)){
            ls = new int[]{R.layout.score_10_1};

        }else if(resName.equals(PIC_NAME_11)){
            ls = new int[]{R.layout.score_11_1};

        }else if(resName.equals(PIC_NAME_12)){
            ls = new int[]{R.layout.score_12_1};

        }else if(resName.equals(PIC_NAME_13)){
            ls = new int[]{R.layout.score_13_1};

        }else if(resName.equals(PIC_NAME_14)){
            ls = new int[]{R.layout.score_14_1};

        }else if(resName.equals(PIC_NAME_15)){
            ls = new int[]{R.layout.score_15_1};

        }else if(resName.equals(PIC_NAME_16)){
            ls = new int[]{R.layout.score_16_1};

        }else if(resName.equals(PIC_NAME_17)){
            ls = new int[]{R.layout.score_17_1};

        }else if(resName.equals(PIC_NAME_18)){
            ls = new int[]{R.layout.score_18_1};

        }

        else if(resName.equals(PIC_NAME_19)){
            ls = new int[]{R.layout.score_19_1};

        }else if(resName.equals(PIC_NAME_20)){
            ls = new int[]{R.layout.score_20_1};

        }else if(resName.equals(PIC_NAME_21)){
            ls = new int[]{R.layout.score_21_1};

        }else if(resName.equals(PIC_NAME_22)){
            ls = new int[]{R.layout.score_22_1, R.layout.score_22_2};

        }else if(resName.equals(PIC_NAME_23)){
            ls = new int[]{R.layout.score_23_1,R.layout.score_23_2};

        }else if(resName.equals(PIC_NAME_24)){
            ls = new int[]{R.layout.score_24_1};

        }else if(resName.equals(PIC_NAME_25)){
            ls = new int[]{R.layout.score_19_1};

        }else if(resName.equals(PIC_NAME_26)){
            ls = new int[]{R.layout.score_26_1};

        }else if(resName.equals(PIC_NAME_27)){
            ls = new int[]{R.layout.score_27_1,R.layout.score_27_2};

        }else if(resName.equals(PIC_NAME_28)){
            ls = new int[]{R.layout.score_28_1,R.layout.score_28_2};

        }else if(resName.equals(PIC_NAME_29)){
            ls = new int[]{R.layout.score_29_1};

        }else if(resName.equals(PIC_NAME_30)){
            ls = new int[]{R.layout.score_30_1,R.layout.score_30_2};

        }else if(resName.equals(PIC_NAME_31)){
            ls = new int[]{R.layout.score_31_1,R.layout.score_31_2};

        }else if(resName.equals(PIC_NAME_32)){
            ls = new int[]{R.layout.score_32_1,R.layout.score_32_2};

        }

        else if(resName.equals(PIC_NAME_D1)){
            ls = new int[]{R.layout.score_d_1_1};

        }

        return ls;
    }

    /**
     *      动态操作UI元素
     *
     *      音符部分： 找到对应TAG,进行相关操作
     *      琴键部分：
     *
     */
    private ArrayList<ImageView> noteList = null;

    public void setNoteAndKey(Context context, ViewGroup vg, int noteIndex, boolean isNoteRed, int keyIndex, boolean isKeyRed) {

        getNotes(vg);

        //设置对应音符位置的颜色
        for (ImageView iv : noteList) {
            String index = (String) iv.getTag();
            if (index.equals(noteIndex + "")) {
                if (isNoteRed) {
                    iv.setBackgroundResource(R.mipmap.kc_red_puzi_bg);
                } else {
                    iv.setBackgroundResource(R.mipmap.kc_blue_puzi_bg);
                }
            } else {
                iv.setBackgroundResource(0);
            }

        }

        //改变琴键的颜色
        LinearLayout gg = (LinearLayout) vg.findViewById(R.id.white_keys);
        for (int i = 0; i < gg.getChildCount(); i++) {
            ImageView iv = (ImageView) gg.getChildAt(i);
            if (keyIndex - 1 == i) {
                if (isKeyRed) {
                    iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, 0xFFFB5555));
                } else {
                    iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, 0xFF34B4FF));
                }
            } else {
                iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, Color.WHITE));
            }
        }

        //改变黑键键的颜色
        LinearLayout bk = (LinearLayout) vg.findViewById(R.id.black_keys);
        for (int i = 0; i < bk.getChildCount(); i++) {
            ImageView iv = (ImageView) bk.getChildAt(i);
            if (keyIndex  - 1015 - 1 == i) {
                if (isKeyRed) {
                    iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.key_red_right));
                } else {
                    iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.key_blue_left));
                }
            } else {
                iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.kc_black_key));
            }
        }

        noteList = null;
    }

    public void setNoteAndKey1(Context context, ViewGroup vg, NoteInfo info) {

        NoteInfo nii = null;
        if(info.getInfo()!=null){
            nii = info.getInfo();
        }

        int noteIndex = info.getNoteIndex();
        boolean isNoteRed = info.isIdNoteRed();

        int keyIndex1 = info.getKeyIndex();
        int keyIndex2 = nii.getKeyIndex();

        getNotes(vg);

        //设置对应音符位置的颜色
        for (ImageView iv : noteList) {
            String index = (String) iv.getTag();
            if (index.equals(noteIndex + "")) {
                if (isNoteRed) {
                    iv.setBackgroundResource(R.mipmap.kc_red_puzi_bg);
                } else {
                    iv.setBackgroundResource(R.mipmap.kc_blue_puzi_bg);
                }
            } else {
                iv.setBackgroundResource(0);
            }

        }

        //改变琴键的颜色
        LinearLayout gg = (LinearLayout) vg.findViewById(R.id.white_keys);
        for (int i = 0; i < gg.getChildCount(); i++) {
            ImageView iv = (ImageView) gg.getChildAt(i);
            if (keyIndex1 - 1 == i) {
                iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, 0xFFFB5555));
            }else if(keyIndex2 - 1 == i){
                iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, 0xFF34B4FF));
            }else {
                iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, Color.WHITE));
            }
        }

        //改变黑键键的颜色
        LinearLayout bk = (LinearLayout) vg.findViewById(R.id.black_keys);
        for (int i = 0; i < bk.getChildCount(); i++) {
            ImageView iv = (ImageView) bk.getChildAt(i);
            if (keyIndex1  - 1015 - 1 == i) {
                iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.key_red_right));
            } else if(keyIndex2  - 1015 - 1 == i){
                iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.key_blue_left));
            }else {
                iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.kc_black_key));
            }
        }

        noteList = null;
    }

    public void offAllLight(MidiOutputDevice mOutputDevice){
//        if(mOutputDevice!=null){
//            for (int i = 21; i < 109; i++) {
//                mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(i, true, false));
//                mOutputDevice.sendMidiSystemExclusive(0, MelodyU.getlightCode(i, false, false));
//            }
//        }
    }

    /**
     * F0 4D 4C 4C 45 15 01 F7    键盘左起第一键对应的红灯亮
     * F0 4D 4C 4C 45 15 00 F7    键盘左起第一键对应的红灯熄灭
     * F0 4D 4C 4C 45 15 11 F7    键盘左起第一键对应的蓝灯亮
     * F0 4D 4C 4C 45 15 10 F7    键盘左起第一键对应的蓝灯熄灭
     *
     * @param note(21 - 108)
     * @param isRed
     * @return
     */
    public static byte[] getlightCode(int note, boolean isRed, boolean isOn) {

        byte mNote = (byte) note;
        byte mOn;
        if (isRed) {
            if (isOn) {
                mOn = 0x01;
            } else {
                mOn = 0x00;
            }
        } else {
            if (isOn) {
                mOn = 0x11;
            } else {
                mOn = 0x10;
            }
        }

        byte[] codes = {(byte) 0xF0, 0x4D, 0x4C, 0x4C, 0x45, mNote, mOn, (byte) 0xF7};

        return codes;
    }

    /***
     * 根据乐谱亮灯
     *
     * @param context
     * @param dur
     * @param color
     * @param index
     * @throws InterruptedException
     */
    public void lightTempo(MidiOutputDevice outPut, long[] dur, int[] color, int[] index) throws InterruptedException {

        if(outPut==null) {
            return;
        }

        this.mOutputDevice = outPut;
        final long[] idur = dur;      //音符间隔       音符个数
        final int[] icolor = color;   //色值判断
        final int[] iindex = index;  //亮灯位置
        for (int n = 0; n < idur.length; n++) {

            if (iindex[n] != -1) {
                //每个音符亮灯时长
                if (icolor[n] == 1) {
                    beat(iindex[n], true, idur[n]);
                } else if (icolor[n] == 0) {
                    beat(iindex[n], false, idur[n]);
                }
                //和下个音符间隔时长
                Thread.sleep(2);
            } else {
                Thread.sleep(1000);
            }
        }
    }

    //闪烁一次灯
    private void beat(int index, final boolean isRed, final long time) throws InterruptedException{
        if(mOutputDevice==null){
            return;
        }

        index = index + 21;
        mOutputDevice.sendMidiSystemExclusive(0,MelodyU.getlightCode(index,isRed,true));
        Thread.sleep(time);
        mOutputDevice.sendMidiSystemExclusive(0,MelodyU.getlightCode(index,isRed,false));

    }

    public void showLight(MidiOutputDevice outPut){
        mOutputDevice = outPut;
        Random random = new Random();
        int max=60;
        int min=29;
        try {
            for(int i = 0; i<10; i++){
                int p = random.nextInt(max)%(max-min+1) + min;
                beat(p,true,600);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void open_DJY(MidiOutputDevice outPut, boolean on){
        if(outPut==null){
            return;
        }
        if(on){
            outPut.sendMidiSystemExclusive(0,new byte[]{ (byte)0xf0, 0x4d, 0x4c, 0x4c, (byte) 0xC0, 0x00,     (byte)0xf7 });
        }else{
            outPut.sendMidiSystemExclusive(0,new byte[]{ (byte)0xf0, 0x4d, 0x4c, 0x4c, (byte) 0xC0, 0x76,     (byte)0xf7 });
        }
    }

    /**
     * 生成 跟奏 需要的乐谱
     *
     * @param color   1 上右手红色  0 下左手蓝色
     * @param note
     * @return
     */
    private static ArrayList<NoteInfo> setNoteList(int[] note, int[] color) {
        ArrayList<NoteInfo> al = new ArrayList<NoteInfo>();
        for (int i = 0, m = 1; i < note.length; i++) {
            if (note[i] == -1) {
                continue;
            }

            NoteInfo ni = new NoteInfo();

            //检测是否双手弹奏
            if(note[i]>100){
                int[] notes = getParseNote2(note[i]);

                ni.setNote(notes[0]);
                ni.setNoteIndex(m);
                ni.setKeyIndex(getKeyIndex(notes[0]));
                ni.setIdNoteRed(true);

                NoteInfo nii = new NoteInfo();
                nii.setNote(notes[1]);
                nii.setNoteIndex(m);
                nii.setKeyIndex(getKeyIndex(notes[1]));
                nii.setIdNoteRed(false);

                ni.setInfo(nii);

            }else{
                ni.setNote(note[i]);
                ni.setNoteIndex(m);
                ni.setKeyIndex(getKeyIndex(note[i]));
                if(color.length==0){
                    ni.setIdNoteRed(true);
                }else{
                    ni.setIdNoteRed(color[i] == 1 ? true : false);
                }
            }

            al.add(ni);
            m++;

        }
        return al;
    }

    //解析双手弹奏
    private static int[] getParseNote(int note){
        int[] result = new int[2];
        String[] str = (note + "").split("123");
        result[0] = Integer.parseInt(str[0]);
        result[1]= Integer.parseInt(str[1]);
        return result;
    }

    //解析双手弹奏
    private static int[] getParseNote2(int note){
        int[] result = new int[2];
        String s = note + "";
        result[0] = Integer.parseInt(s.substring(0,2));
        result[1]= Integer.parseInt(s.substring(2,4));
        return result;
    }

    /**
     * 根据音符号 获取琴键 index
     *
     * @param noteIndex
     * @return
     */
    public static int getKeyIndex(int noteNum) {
        int result = -1;
        switch (noteNum) {
            case 27:
                result = 1;
                break;
            case 29:
                result = 2;
                break;
            case 31:
                result = 3;
                break;
            case 32:
                result = 4;
                break;
            case 34:
                result = 5;
                break;
            case 36:
                result = 6;
                break;
            case 38:
                result = 7;
                break;
            case 39:
                result = 8;
                break;
            case 41:
                result = 9;
                break;
            case 43:
                result = 10;
                break;
            case 44:
                result = 11;
                break;
            case 46:
                result = 12;
                break;
            case 48:
                result = 13;
                break;
            case 50:
                result = 14;
                break;
            case 51:
                result = 15;
                break;


            case 28:
                result = 1016;
                break;
            case 30:
                result = 1017;
                break;
            case 33:
                result = 1018;
                break;
            case 35:
                result = 1019;
                break;
            case 37:
                result = 1020;
                break;
            case 40:
                result = 1021;
                break;
            case 42:
                result = 1022;
                break;
            case 45:
                result = 1023;
                break;
            case 47:
                result = 1024;
                break;
            case 49:
                result = 1025;
                break;



        }
        return result;
    }


    private void getNotes(ViewGroup vg) {
        if (noteList == null) {
            noteList = new ArrayList<>();
        }
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof ViewGroup) {
                getNotes((ViewGroup) vg.getChildAt(i));
            } else {
                if (vg.getChildAt(i).getTag() != null && !TextUtils.isEmpty(vg.getChildAt(i).getTag().toString())) {
                    noteList.add((ImageView) vg.getChildAt(i));
                }
            }
        }
    }

    /**
     * 改变图片颜色
     */
    private Drawable getTintPic(Context context, int image, int color) {
        Drawable drawable = ContextCompat.getDrawable(context, image);
        Drawable.ConstantState constantState = drawable.getConstantState();
        Drawable newDrawable = DrawableCompat.wrap(constantState == null ? drawable : constantState.newDrawable()).mutate();
        DrawableCompat.setTint(newDrawable, color);
        return newDrawable;
    }


    private static MelodyU instance;

    // 私有化构造方法，变成单例模式
    private MelodyU() {
    }

    // 对外提供一个该类的实例，考虑多线程问题，进行同步操作
    public static MelodyU getInstance() {
        if (instance == null) {
            synchronized (MelodyU.class) {
                if (instance == null) {
                    instance = new MelodyU();
                }
            }
        }
        return instance;
    }

    public BeatThread bt;
    public class BeatThread extends Thread{
        private MidiOutputDevice midi;
        public BeatThread(MidiOutputDevice mOutputDevice){
            midi = mOutputDevice;
        }
        public volatile boolean exit = false;
        public void run()
        {
            while (!exit){
                try {
                    Thread.sleep(2000);
                    if(midi!=null) {
                        midi.sendMidiSystemExclusive(0, ACTION_KEEP_ALIVE);
                        //midi.sendMidiNoteOn(0x1b, (byte)0xbF, 0x07, 0x00);

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startBeatThread(MidiOutputDevice mO){
        bt = new BeatThread(mO);
        bt.start();
    }

    public void stopBeatThread(MidiOutputDevice mO){
        if(bt!=null) {
            bt.exit = true;
            bt.interrupt();
            try {
                bt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}













