package com.example.bawei.addfriend.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.bawei.addfriend.R;
import com.example.bawei.addfriend.adapter.MyPagerAdapter;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.basemodel.widget.CustomTitleBar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends BaseMVPActivity {
    private CustomTitleBar friend_ctb;
    private TabLayout tab;
    private ViewPager vp;
    private MyPagerAdapter pagerAdapter;
    private List<String> titlwlist=new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        titlwlist.add("好友");
        titlwlist.add("分组");
        titlwlist.add("群聊");
        friend_ctb = findViewById(R.id.friend_ctb);
        tab = findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        friend_ctb.rightivOnclick(v -> startActivity(new Intent(FriendActivity.this, FindFriendActivity.class)));
        pagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),titlwlist);
        pagerAdapter.setFrgment(new FridentFragment());
        pagerAdapter.setFrgment(new FridentFragment());
        pagerAdapter.setFrgment(new FridentFragment());
        vp.setAdapter(pagerAdapter);
        tab.setupWithViewPager(vp);

    }



    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }
}
