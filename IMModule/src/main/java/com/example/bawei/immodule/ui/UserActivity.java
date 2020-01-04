package com.example.bawei.immodule.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;

import com.bawei6.immodule.R;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.contract.IXmppUser;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.immodule.Bean.MyLoginBean;
import com.example.bawei.immodule.Bean.RegisterBean;

import com.example.bawei.immodule.contract.Contract;
import com.example.bawei.immodule.presenter.Presenter;

import entity.BaseBeanEntity;


public class UserActivity extends BaseMVPActivity<Contract.Presenter> implements Contract.View {

    private EditText et_username;
    private EditText et_psw;
    private Button btn_zc;
    private Button btn_dl;
    IXmppUser xmppUserManager;
    String username;
    String psw;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView(Bundle savedInstanceState) {
        et_username = (EditText) findViewById(R.id.et_username);
        et_psw = (EditText) findViewById(R.id.et_psw);
        btn_zc = (Button) findViewById(R.id.btn_zc);
        btn_dl = (Button) findViewById(R.id.btn_dl);
        presenter = new Presenter();
        presenter.attachView(this);
        btn_zc.setOnClickListener(v -> {
            LogUtils.d(et_username.getText().toString());
            submit(false);
        });
        btn_dl.setOnClickListener(v -> submit(true));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void submit(Boolean islogin) {
        // validate
        username = et_username.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "username不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "psw不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        xmppUserManager = XmppManager
                .getInstance()
                .getXmppUserManager();
        if (!islogin) {
            presenter.register(new RegisterBean(1, "1", username, psw, "男", "1", "1", "1", 1, username, "1", 1, 1));
        } else {
            MyLoginBean myLoginBean = new MyLoginBean(username, psw);
            presenter.login(myLoginBean);
        }
        // TODO validate success, do something
    }


    @Override
    public <T> void Success(T bean, int flag) {
        if (flag == 0) {
            BaseBeanEntity<RegisterBean> registerBean = (BaseBeanEntity<RegisterBean>) bean;
            Toast.makeText(this, registerBean.getMsg(), Toast.LENGTH_SHORT).show();
        } else if (flag == 1) {

                BaseBeanEntity<RegisterBean> requestlogin = (BaseBeanEntity<RegisterBean>) bean;
                if(requestlogin.getMsg().equals("用户登录成功")){
                    LogUtils.e(bean + "");
                    Intent intent = new Intent();
                    //把返回数据存入Intent
                    intent.putExtra("boolean", true);
                    //设置返回数据
                    intent.putExtra("username", username);
                    UserActivity.this.setResult(RESULT_OK, intent);
                    //关闭Activity
                    UserActivity.this.finish();
                    SharedPreferences sharedPreferences = getSharedPreferences("yxh", 0);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putBoolean("isDL", true);
                    edit.putString("username", username);
                    edit.putString("psw",psw);

                    edit.putString("usercode", requestlogin.getData().getUsercode());
                    edit.commit();
                    Toast.makeText(UserActivity.this, "欢迎用户" + username, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserActivity.this, "登陆失败" , Toast.LENGTH_SHORT).show();
            }



        }

    }

    @Override
    public void Failed() {
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showHideIng() {

    }

    @Override
    public LifecycleOwner getOwner() {
        return this;
    }
}
