package com.angelmusic.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.angelmusic.student.R;
import com.angelmusic.student.adpater.QuKuAdapter;
import com.angelmusic.student.base.BaseActivity;
import com.angelmusic.student.course_download.infobean.QuBean;
import com.angelmusic.student.course_download.infobean.QukuListInfo;

import java.util.ArrayList;

/**
 * Created by wangshuai on 2018/4/27.
 */

public class ImageListActivity extends BaseActivity {

    private QuKuAdapter qkAdapter;
    private ImageView iv_back;
    private RecyclerView mRecyclerView;
    private QukuListInfo qList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView = (RecyclerView)findViewById(R.id.recy_list);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageListActivity.this.finish();
            }
        });

        initData();
        setList();
    }

    private void initData() {
        qList = new QukuListInfo();
        ArrayList list = new ArrayList();

        QuBean q1 = new QuBean("Do-Re-Mi","1");
        QuBean q2 = new QuBean("G大调小步舞曲","2");
        QuBean q3 = new QuBean("虫儿飞","3");
        QuBean q4 = new QuBean("春天在哪里","4");
        QuBean q5 = new QuBean("大风车","5");
        QuBean q6 = new QuBean("丢手绢","6");
        QuBean q7 = new QuBean("读书郎","7");
        QuBean q8 = new QuBean("对不起","8");
        QuBean q9 = new QuBean("粉刷匠","9");
        QuBean q10 = new QuBean("歌声与微笑","10");

        QuBean q11 = new QuBean("葫芦娃","11");
        QuBean q12 = new QuBean("快乐父子俩","12");
        QuBean q13 = new QuBean("两只老虎","13");
        QuBean q14 = new QuBean("铃儿响叮当","14");
        QuBean q15 = new QuBean("玛丽有只小羊羔","15");
        QuBean q16 = new QuBean("卖报歌","16");
        QuBean q17 = new QuBean("排排坐","17");
        QuBean q18 = new QuBean("让我们荡起双桨","18");
        QuBean q19 = new QuBean("闪闪的红星","19");
        QuBean q20 = new QuBean("上学歌","20");

        QuBean q21 = new QuBean("世上只有妈妈好","21");
        QuBean q22 = new QuBean("数鸭子","22");
        QuBean q23 = new QuBean("睡吧，宝宝","23");
        QuBean q24 = new QuBean("送别","24");
        QuBean q25 = new QuBean("淘气的小鬼","25");
        QuBean q26 = new QuBean("同一首歌","26");
        QuBean q27 = new QuBean("我们多么幸福","27");
        QuBean q28 = new QuBean("五只小鸭","28");
        QuBean q29 = new QuBean("小放牛","29");
        QuBean q30 = new QuBean("小毛驴","30");

        QuBean q31 = new QuBean("小蜜蜂","31");
        QuBean q32 = new QuBean("小娃娃","32");
        QuBean q33 = new QuBean("小燕子","33");
        QuBean q34 = new QuBean("新年好","34");
        QuBean q35 = new QuBean("匈牙利舞曲","35");
        QuBean q36 = new QuBean("雪绒花","36");
        QuBean q37 = new QuBean("洋娃娃和小熊跳舞","37");
        QuBean q38 = new QuBean("一分钱","38");
        QuBean q39 = new QuBean("一只青蛙","39");
        QuBean q40 = new QuBean("运动员进行曲","40");




        list.add(q1);list.add(q2);list.add(q3);list.add(q4);list.add(q5);list.add(q6);list.add(q7);list.add(q8);list.add(q9);list.add(q10);
        list.add(q11);list.add(q12);list.add(q13);list.add(q14);list.add(q15);list.add(q16);list.add(q17);list.add(q18);list.add(q19);list.add(q20);
        list.add(q21);
        list.add(q22);
        list.add(q23);
        list.add(q24);
        list.add(q25);
        list.add(q26);
        list.add(q27);
        list.add(q28);
        list.add(q29);
        list.add(q30);
        list.add(q31);
        list.add(q32);
        list.add(q33);
        list.add(q34);
        list.add(q35);
        list.add(q36);
        list.add(q37);
        list.add(q38);
        list.add(q30);
        list.add(q40);



        qList.setDetail(list);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_qu_ku;

    }

    @Override
    protected void setTAG() {

    }


    private void setList(){

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            qkAdapter = new QuKuAdapter(this,qList);
            qkAdapter.setOnItemClickLitener(new QuKuAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent=new Intent(ImageListActivity.this,ImageActivity.class);
                    intent.putExtra("index",Integer.parseInt(qList.getDetail().get(position).getId()));
                    startActivity(intent);


                }
                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
            mRecyclerView.setAdapter(qkAdapter);

    }


}
