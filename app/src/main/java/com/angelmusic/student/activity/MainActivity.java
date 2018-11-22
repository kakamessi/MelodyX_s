package com.angelmusic.student.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angelmusic.stu.u3ddownload.utils.GsonUtil;
import com.angelmusic.student.R;
import com.angelmusic.student.adpater.SeatAdapter;
import com.angelmusic.student.base.BaseMidiActivity;
import com.angelmusic.student.constant.Constant;
import com.angelmusic.student.core.ActionBean;
import com.angelmusic.student.core.ActionProtocol;
import com.angelmusic.student.core.ActionResolver;
import com.angelmusic.student.core.MelodyU;
import com.angelmusic.student.infobean.SeatDataInfo;
import com.angelmusic.student.utils.Encrypter;
import com.angelmusic.student.utils.NetworkUtil;
import com.angelmusic.student.utils.SharedPreferencesUtil;
import com.angelmusic.student.utils.Utils;
import com.angelmusic.student.version_update.ApkManager;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import jp.kshoji.driver.midi.device.MidiInputDevice;

/**
 * 主页
 */
public class MainActivity extends BaseMidiActivity {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_wifi_name)
    TextView tvWifiName;
    @BindView(R.id.tv_classroom_name)
    TextView tvClassroomName;
    @BindView(R.id.tv_seat_id)
    TextView tvSeatId;
    @BindView(R.id.tv_connection_status)
    TextView tvConnectionStatus;
    @BindView(R.id.ib_download)
    ImageButton ibDownload;
    @BindView(R.id.tv_blackboard)
    TextView tvBlackboard;
    @BindView(R.id.layout_main_01)
    RelativeLayout layoutMain01;
    @BindView(R.id.iv_quku)
    ImageView iv_quku;

    private String wifiName;
    private String schoolName;
    private String roomName;
    private String seatId;
    private String pianoConStatus;
    private SeatDataInfo seatDataInfo;
    private PopupWindow popupWindow;

    private QuKuFragment qukuFragment  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApkManager.getInstance(this).checkVersionInfo();//检查版本更新
        initData();
        initView();
        initMidi();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MelodyU.getInstance().stopBeatThread(null);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 数据初始化
     */
    private void initData() {
        wifiName = NetworkUtil.getWifiName(this);//获取当前pad连接的wifi名称
        seatId = SharedPreferencesUtil.getString("seatNo", "00");
        roomName = SharedPreferencesUtil.getString("roomName", "00");
        schoolName = SharedPreferencesUtil.getString("schoolName", "智慧音乐课堂");
        pianoConStatus = "未连接";//钢琴是否连接
    }

    private void initView() {
        tvBlackboard.setText(schoolName);
        tvWifiName.setText(Html.fromHtml("<u>" + wifiName + "</u>"));
        tvClassroomName.setText(Html.fromHtml("<u>" + roomName + "</u>"));
        tvSeatId.setText(Html.fromHtml("<u>" + seatId + "</u>"));
        tvConnectionStatus.setText(Html.fromHtml("<u>" + pianoConStatus + "</u>"));
    }

    @OnClick({R.id.ib_download, R.id.tv_wifi_name, R.id.tv_classroom_name, R.id.seatId_ll, R.id.tv_connection_status, R.id.iv_quku})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_download:
                //跳转到下载页
                startActivity(new Intent(MainActivity.this, DownloadActivity.class));
                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);

                break;
            case R.id.tv_wifi_name:
                wifiName = NetworkUtil.getWifiName(this);//获取当前pad连接的wifi名称
                tvWifiName.setText(Html.fromHtml("<u>" + wifiName + "</u>"));
                break;
            case R.id.tv_classroom_name:
                //预留
                break;
            case R.id.seatId_ll:

                break;
            case R.id.tv_connection_status:
                //预留

                break;
            case R.id.iv_quku:
                //addFragment();
                startActivity(new Intent(MainActivity.this, ImageListActivity.class));

                break;
            default:

                break;
        }
    }



    /**
     * 接收消息
     */
    @Override
    protected void handleMsg(Message msg) {

        doAction((String) msg.obj);
    }

    private ActionBean ab;
    private void doAction(String str) {
        ab = ActionResolver.getInstance().resolve(str);

        if(ab.getCodeByPositon(0)== ActionProtocol.CODE_ACTION_COURSE){
            if(ab.getCodeByPositon(1)==1 && ab.getCodeByPositon(2)==1){

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //解密操作
                            String s4 = ab.getCodes()[3];
                            final String[] names = s4.split("_");
                            for(int i =0; i<names.length; i++){
                                File f1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FILE_PATH_CACHE + names[i]);
                                File f2 = new File(Utils.getVideoPath() + names[i]);
                                Encrypter.decode(f1,f2,"xmelody");
                            }
                        }catch (Exception e){
                        }
                    }
                }).start();

                Intent intent=new Intent(MainActivity.this,VideoActivity.class);
                startActivity(intent);
            }
        }else if(ab.getCodeByPositon(0)== 2){
            if(ab.getCodeByPositon(1)== 1){
                Intent intent=new Intent(MainActivity.this,H5Activity.class);
                startActivity(intent);
            }
        }
    }

    /*
    *
    * Fragment相关-------------------------------------------------------------------------
    *
    * */
    public void addFragment() {
        FragmentManager fm = getSupportFragmentManager();
        qukuFragment = (QuKuFragment) fm.findFragmentById(R.id.layout_main_01);
        if(qukuFragment == null )
        {
            qukuFragment = new QuKuFragment();
            fm.beginTransaction().add(R.id.layout_main_01,qukuFragment).addToBackStack(null).commit();
        }
    }

    public void popFragment() {

        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();

    }


    //-----其他-----------------------------------------------------------------------------------------------------------------------------------

    /**
     * 显示所有座位号列表的弹框
     */
    private void showSeatPopup(View view) {
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.seat_layout, null);
        GridView gridView = (GridView) contentView.findViewById(R.id.gv_seat);
        if (seatDataInfo == null) {
            String seatInfoJson = SharedPreferencesUtil.getString("seatInfoJson", null);
            seatDataInfo = GsonUtil.jsonToObject(seatInfoJson, SeatDataInfo.class);
        }
        if (seatDataInfo != null) {
            gridView.setNumColumns(seatDataInfo.getColumnNo());//这里动态设置列数
        }
        SeatAdapter adapter = new SeatAdapter(this, seatDataInfo);
        gridView.setAdapter(adapter);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        final WindowManager.LayoutParams wlBackground = getWindow().getAttributes();
        wlBackground.alpha = 0.5f;// 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        getWindow().setAttributes(wlBackground);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                wlBackground.alpha = 1.0f;// 当PopupWindow消失时,恢复其为原来的颜色
                getWindow().setAttributes(wlBackground);
            }
        });
        //关闭popupWindow的按钮
        contentView.findViewById(R.id.ib_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSeatId.setClickable(true);//防止重复点击
                popupWindow.dismiss();
            }
        });
        //设置PopupWindow进入和退出动画
        popupWindow.setAnimationStyle(R.style.PopupAnim);
        // 设置PopupWindow显示在中间
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }


    /**
     * 弹框显示当前pad的座位号
     *
     * @param seatId 当前PAD座位号
     */
    private void showSeatIdPopupWindow(String seatId) {
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.seat_id_layout, null);
        TextView tvSeatNum = (TextView) contentView.findViewById(R.id.tv_seat_num);
        tvSeatNum.setText(seatId);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        final WindowManager.LayoutParams wlBackground = getWindow().getAttributes();
        wlBackground.alpha = 0.5f;// 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        getWindow().setAttributes(wlBackground);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                wlBackground.alpha = 1.0f;// 当PopupWindow消失时,恢复其为原来的颜色
                getWindow().setAttributes(wlBackground);
            }
        });
        //设置PopupWindow进入和退出动画
        popupWindow.setAnimationStyle(R.style.PopupAnim);
        // 设置PopupWindow显示在中间
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }


    //-------其他---------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void onMidiInputDeviceAttached(@NonNull MidiInputDevice midiInputDevice) {
        super.onMidiInputDeviceAttached(midiInputDevice);
        tvConnectionStatus.setText("已连接");

    }

    @Override
    public void onMidiInputDeviceDetached(@NonNull MidiInputDevice midiInputDevice) {
        super.onMidiInputDeviceDetached(midiInputDevice);
        tvConnectionStatus.setText("未连接");

    }

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            mBaseApp.finishAllActivity();
            return true;//不执行父类点击事件
        }
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }

}
