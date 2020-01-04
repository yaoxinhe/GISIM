package com.example.bawei.addfriend.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.bawei.addfriend.R;
import com.example.bawei.addfriend.adapter.MyPagerAdapter;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FindFriendActivity extends BaseMVPActivity {


    private TabLayout tab;
    private ViewPager vp;
    private List<String>list=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_friend;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        list.add("找人");
        list.add("找群");
        tab=findViewById(R.id.findfrienta_tab);
        vp=findViewById(R.id.findfrienta_vp);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), list);
        myPagerAdapter.setFrgment(new AddFragment());
        myPagerAdapter.setFrgment(new AddFragment());
        vp.setAdapter(myPagerAdapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }

}
