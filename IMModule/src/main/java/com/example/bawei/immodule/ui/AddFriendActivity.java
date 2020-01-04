package com.example.bawei.immodule.ui;

import android.icu.text.UnicodeSet;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawei6.immodule.R;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IAddFriendCallback;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseActivity;
import com.example.bawei.basemodel.ui.BaseMVPActivity;

import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

public class AddFriendActivity extends BaseMVPActivity implements View.OnClickListener {

    private EditText et_addfriend;
    private Button btn_add;
    private String username;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addfriend;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        et_addfriend=findViewById(R.id.et_addfriend);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        username=getIntent().getStringExtra("username");


    }

    @Override
    protected void doEvent() {

    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {
            String string = et_addfriend.getText().toString();
            boolean b = XmppManager.getInstance().getXmppFriendManager().addFriend("123" + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), string);
            Log.e("asd", b + "");
            this.finish();
        }
    }
}
