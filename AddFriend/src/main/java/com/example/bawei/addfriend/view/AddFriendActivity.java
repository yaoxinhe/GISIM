package com.example.bawei.addfriend.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.common.PhoneDto;
import com.bawei6.common.PhoneUtil;
import com.example.bawei.addfriend.R;
import com.example.bawei.addfriend.adapter.GoodsAdapter;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseMVPActivity;

import java.util.List;

public class AddFriendActivity extends BaseMVPActivity {
    private List<PhoneDto> phoneDtos;
    private RecyclerView addfriend_recyclevew;



    @Override
    protected int getLayoutId() {

        return R.layout.activity_add_friend;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        addfriend_recyclevew = (RecyclerView) findViewById(R.id.addfriend_recyclevew);
        check();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }


    /**
     * 检查权限
     */
    private void check() {
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(AddFriendActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddFriendActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 201);
        } else {
            initViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 201) {
            LogUtils.e("asdsadsadas");
            initViews();
        } else {
            return;
        }
    }

    private void initViews() {
        PhoneUtil phoneUtil = new PhoneUtil(this);
        phoneDtos = phoneUtil.getPhone();
        LogUtils.e(phoneDtos.size() + "");
        addfriend_recyclevew.setLayoutManager(new LinearLayoutManager(this));
        addfriend_recyclevew.setAdapter(new GoodsAdapter(phoneDtos));
    }


}