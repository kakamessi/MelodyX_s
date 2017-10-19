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

/**
 * Created by wangshuai on 2017/10/18.
 */

public class MelodyU {

    //-----数据段start---------------------------------------------------------------------------------------------

    public static int[] note_1 = {38, 39, 41, 43, 44, 46, 48, 50};
    public static int[] color_1 = { 1, 1, 1, 1, 1, 1, 1, 1};




    public static ArrayList<NoteInfo> course_1 = new ArrayList<>();
    public static ArrayList<NoteInfo> course_2 = new ArrayList<>();

    static {
        course_1.addAll(setNoteList(note_1,color_1));
    }

    //-----数据段end---------------------------------------------------------------------------------------------

    /**
     * playIndex      从0开始
     * @param note   音符号
     * @param resId  资源id
     * @return       返回下一个音符信息
     */
    public static NoteInfo checkInputX(int note,int playIndex,int resId){
        NoteInfo result = null;

        if(resId==-1){

            playIndex = playIndex%course_1.size();

            //判断正误
            if(note != course_1.get(playIndex).getNote()){
                return null;
            }

            //返回下一个
            if(playIndex==(course_1.size()-1)){
                playIndex = 0;
            }else{
                playIndex++;
            }
            result = course_1.get(playIndex);
        }
        return result;
    }

    /**
     *      动态操作UI元素
     *
     *      根据跟奏[数据] 设置对应的音符，琴键界面变化
     *      音符位置，音符颜色
     *
     *      音符Tag从左至右升序排列 0 - 10  上下都有的序号相同
     *
     *
     *      音符蓝色背景 R.mipmap.kc_blue_puzi_bg 无背景 0
     *
     *  ((ImageView) whiteKeyLl2.getChildAt(i - 1)).setImageDrawable(getTintPic(this, R.mipmap.kc_white_key, 0xFFFB5555));
     ((ImageView) whiteKeyLl2.getChildAt(i - 1)).setImageDrawable(getTintPic(this, R.mipmap.kc_white_key, 0xFF34B4FF));
     ((ImageView) whiteKeyLl2.getChildAt(i - 1)).setImageDrawable(getTintPic(this, R.mipmap.kc_white_key, Color.WHITE));
     *
     *
     */
    private ArrayList<ImageView> noteList = null;
    public void setNoteAndKey(Context context, ViewGroup vg, int noteIndex, boolean isNoteRed, int keyIndex, boolean isKeyRed){

        getNotes(vg);

        //设置对应音符位置的颜色
        for(ImageView iv : noteList){
            String index = (String) iv.getTag();
            if(index.equals(noteIndex + "")) {
                if(isNoteRed){
                    iv.setBackgroundResource(R.mipmap.kc_red_puzi_bg);
                }else{
                    iv.setBackgroundResource(R.mipmap.kc_blue_puzi_bg);
                }
            }else{
                iv.setBackgroundResource(0);
            }

        }

        //改变琴键的颜色
        LinearLayout gg = (LinearLayout) vg.findViewById(R.id.white_keys);
        for(int i=0;i<gg.getChildCount(); i++){
            ImageView iv = (ImageView) gg.getChildAt(i);
            if(keyIndex - 1 == i){
                if(isKeyRed){
                    iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, 0xFFFB5555));
                }else{
                    iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, 0xFF34B4FF));
                }
            }else{
                iv.setImageDrawable(getTintPic(context, R.mipmap.kc_white_key, Color.WHITE));
            }
        }

        noteList = null;
    }

    /**
     F0 4D 4C 4C 45 15 01 F7    键盘左起第一键对应的红灯亮
     F0 4D 4C 4C 45 15 00 F7    键盘左起第一键对应的红灯熄灭
     F0 4D 4C 4C 45 15 11 F7    键盘左起第一键对应的蓝灯亮
     F0 4D 4C 4C 45 15 10 F7    键盘左起第一键对应的蓝灯熄灭
     * @param note
     * @param isRed
     * @return
     */
    public static byte[] getlightCode(int note, boolean isRed,boolean isOn){

        byte mNote = (byte) note;
        byte mOn;
        if(isRed){
            if(isOn){
                mOn = 0x01;
            }else{
                mOn = 0x00;
            }
        }else{
            if(isOn){
                mOn = 0x11;
            }else{
                mOn = 0x10;
            }
        }

        byte[] codes = {(byte) 0xF0,0x4D,0x4C,0x4C,0x45,mNote,mOn,(byte) 0xF7};

        return codes;
    }


    /**
     * 生成 跟奏 需要的乐谱
     * @param color
     * @param note
     * @return
     */
    private static ArrayList<NoteInfo> setNoteList(int[] note, int[] color){
        ArrayList<NoteInfo> al = new ArrayList<NoteInfo>();
        for(int i=0,m=1; i<note.length;i++){
            if(note[i]==-1){
                continue;
            }
            NoteInfo ni = new NoteInfo();
            ni.setNote(note[i]);
            ni.setNoteIndex(m);
            ni.setKeyIndex(getKeyIndex(note[i]));
            ni.setIdNoteRed(color[i]==1?true:false);
            al.add(ni);
            m++;
        }
        return al;
    }

    /**
     * 根据音符号 获取琴键 index
     * @param noteIndex
     * @return
     */
    public static int getKeyIndex(int noteNum){
        int result  = -1;
        switch(noteNum) {
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
        }
        return result;
    }


    private void getNotes(ViewGroup vg){
        if(noteList==null){
            noteList = new ArrayList<>();
        }
        for(int i=0;i<vg.getChildCount();i++){
            if(vg.getChildAt(i) instanceof ViewGroup){
                getNotes((ViewGroup) vg.getChildAt(i));
            }else{
                if(vg.getChildAt(i).getTag()!=null && !TextUtils.isEmpty(vg.getChildAt(i).getTag().toString())){
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

}