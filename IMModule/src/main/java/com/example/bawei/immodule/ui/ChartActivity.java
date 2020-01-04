package com.example.bawei.immodule.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei6.immodule.R;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.contract.IXmppMsg;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseActivity;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.immodule.contract.Contract;

public class ChartActivity extends BaseMVPActivity {

    private TextView tx;
    private EditText et_message;
    private Button btn_send;
    private Button asd;
    private Button zx;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tx = findViewById(R.id.tx);
        et_message = findViewById(R.id.et_message);
        btn_send = findViewById(R.id.btn_send);
        asd = findViewById(R.id.asd);
        zx=findViewById(R.id.zx);
        zx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean logout = XmppManager.getInstance().getXmppUserManager().deleteAccount();
                LogUtils.e(logout+"");
                if(logout){
                    Intent intent = new Intent(ChartActivity.this, UserActivity.class);
                    startActivity(intent);
                    ChartActivity.this.finish();
                }
            }
        });
        asd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChartActivity.this, AddFriendActivity.class);
                String username = intent.getStringExtra("username");
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        XmppManager.getInstance().addMessageListener(new IMsgCallback() {
            @Override
            public void Success(MsgEntity msgEntity) {
                String msg = msgEntity.getMsg();
                LogUtils.e(msg);
                tx.setText(msg);
            }

            @Override
            public void Failed(Throwable throwable) {
                LogUtils.e("asd");
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = et_message.getText().toString();
                IXmppMsg xmppMsgManager = XmppManager.getInstance().getXmppMsgManager();
                xmppMsgManager.sendSingleMessage("123@" + XmppManager.getInstance().getXmppConfig().getDomainName(), string);
            }
        });
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void doEvent() {

    }


}
