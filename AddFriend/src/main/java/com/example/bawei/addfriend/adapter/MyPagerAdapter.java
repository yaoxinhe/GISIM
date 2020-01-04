package com.example.bawei.addfriend.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/2 18:37
 * @Email 1151403054@qq.com
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    List<String> titlwlist;
    List<Fragment> Fragmentlist=new ArrayList<>();

    public MyPagerAdapter(@NonNull FragmentManager fm, List<String> titlwlist) {
        super(fm);
        this.titlwlist = titlwlist;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return Fragmentlist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlwlist.get(position);
    }
    public void setFrgment(Fragment frgment){
        Fragmentlist.add(frgment);
    }
}
