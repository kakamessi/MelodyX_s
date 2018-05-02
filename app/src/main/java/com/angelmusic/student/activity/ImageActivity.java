package com.angelmusic.student.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.angelmusic.student.R;
import com.angelmusic.student.base.BaseActivity;
import com.angelmusic.student.customview.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import uk.co.senab.photoview.PhotoView;


public class ImageActivity extends BaseActivity {

    private RollPagerView mRollViewPager;

    private int num = -1;
    public int[] imgsss = {1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initD();
        
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);

        //设置播放时间间隔
        //mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

        
    }

    private void initD() {
        num = getIntent().getIntExtra("index",10);
        switch(num){
            case 1: imgsss[0] = R.mipmap.z1;break;
            case 2: imgsss[0] = R.mipmap.z2;break;
            case 3: imgsss[0] = R.mipmap.z3;break;
            case 4: imgsss[0] = R.mipmap.z4;break;
            case 5: imgsss[0] = R.mipmap.z5;break;
            case 6: imgsss[0] = R.mipmap.z6;break;
            case 7: imgsss[0] = R.mipmap.z7;break;
            case 8: imgsss[0] = R.mipmap.z8;break;
            case 9: imgsss[0] = R.mipmap.z9;break;
            case 10: imgsss[0] = R.mipmap.z10;break;

            case 11: imgsss[0] = R.mipmap.z11;break;
            case 12: imgsss[0] = R.mipmap.z12;break;
            case 13: imgsss[0] = R.mipmap.z13;break;
            case 14: imgsss[0] = R.mipmap.z14;break;
            case 15: imgsss[0] = R.mipmap.z15;break;
            case 16: imgsss[0] = R.mipmap.z16;break;
            case 17: imgsss[0] = R.mipmap.z17;break;
            case 18: imgsss[0] = R.mipmap.z18;break;
            case 19: imgsss[0] = R.mipmap.z19;break;
            case 20: imgsss[0] = R.mipmap.z20;break;

            case 21: imgsss[0] = R.mipmap.z21;break;
            case 22: imgsss[0] = R.mipmap.z22;break;
            case 23: imgsss[0] = R.mipmap.z23;break;
            case 24: imgsss[0] = R.mipmap.z24;break;
            case 25: imgsss[0] = R.mipmap.z25;break;
            case 26: imgsss[0] = R.mipmap.z26;break;
            case 27: imgsss[0] = R.mipmap.z27;break;
            case 28: imgsss[0] = R.mipmap.z28;break;
            case 29: imgsss[0] = R.mipmap.z29;break;
            case 30: imgsss[0] = R.mipmap.z30;break;

            case 31: imgsss[0] = R.mipmap.z31;break;
            case 32: imgsss[0] = R.mipmap.z32;break;
            case 33: imgsss[0] = R.mipmap.z33;break;
            case 34: imgsss[0] = R.mipmap.z34;break;
            case 35: imgsss[0] = R.mipmap.z35;break;
            case 36: imgsss[0] = R.mipmap.z36;break;
            case 37: imgsss[0] = R.mipmap.z37;break;
            case 38: imgsss[0] = R.mipmap.z38;break;
            case 39: imgsss[0] = R.mipmap.z39;break;
            case 40: imgsss[0] = R.mipmap.z40;break;

        }

    }

    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.mipmap.a1235,
                R.mipmap.a1332,
                R.mipmap.a1432,
                R.mipmap.a1532,
        };


        @Override
        public View getView(ViewGroup container, int position) {
            //ImageView view = new ImageView(container.getContext());
            PhotoView view = new PhotoView(container.getContext());
            view.setImageResource(imgsss[position]);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return imgsss.length;
        }
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_image;

    }

    @Override
    protected void setTAG() {

    }
}
