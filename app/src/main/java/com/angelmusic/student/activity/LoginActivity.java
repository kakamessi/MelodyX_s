package com.angelmusic.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.angelmusic.stu.u3ddownload.okhttp.HttpInfo;
import com.angelmusic.stu.u3ddownload.okhttp.OkHttpUtil;
import com.angelmusic.stu.u3ddownload.okhttp.OkHttpUtilInterface;
import com.angelmusic.stu.u3ddownload.okhttp.callback.CallbackOk;
import com.angelmusic.stu.u3ddownload.utils.GsonUtil;
import com.angelmusic.student.R;
import com.angelmusic.student.base.BaseActivity;
import com.angelmusic.student.constant.Constant;
import com.angelmusic.student.infobean.LoginBean;
import com.angelmusic.student.utils.SharedPreferencesUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.angelmusic.stu.u3ddownload.okhttp.annotation.CacheLevel.FIRST_LEVEL;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private String currentUsername;
    private String currentPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.btn_login)
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:

                currentUsername = username.getText().toString();
                currentPassword = password.getText().toString();
                login();


                break;

        }

    }


    private void login() {

        OkHttpUtilInterface okHttpUtil = OkHttpUtil.Builder()
                .setCacheLevel(FIRST_LEVEL)
                .setConnectTimeout(25).build(this);
        okHttpUtil.doGetAsync(
                HttpInfo.Builder().setUrl(Constant.URL_ROOT + Constant.URL_STU_LOGIN)
                        .addParam("account", currentUsername)
                        .build(),
                new CallbackOk() {
                    @Override
                    public void onResponse(HttpInfo info) throws IOException {
                        if(HttpInfo.SUCCESS==info.getRetCode()){
                            LoginBean lb = GsonUtil.jsonToObject(info.getRetDetail(),LoginBean.class);
                            if(lb.getCode()==200) {
                                String name = lb.getDetail().getStuName();
                                String sid = lb.getDetail().getSchoolId() + "";
                                SharedPreferencesUtil.setString(Constant.CACHE_STUDENT_NAME, name);
                                SharedPreferencesUtil.setString(Constant.CACHE_SCHOOL_ID, sid);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(LoginActivity.this,"登录失败",0).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"登录失败",0).show();
                        }
                    }

                });
    }


}
