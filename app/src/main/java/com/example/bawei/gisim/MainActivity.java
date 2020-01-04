package com.example.bawei.gisim;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.homemodule.ui.HomeFragment;

public class MainActivity extends BaseMVPActivity {

    private ViewPager vp;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private HomeFragment homeFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView(Bundle savedInstanceState) {

        vp = (ViewPager) findViewById(R.id.vp);
        homeFragment=new HomeFragment();
        fragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.addFragment(homeFragment);
        vp.setAdapter(fragmentPagerAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }
}
