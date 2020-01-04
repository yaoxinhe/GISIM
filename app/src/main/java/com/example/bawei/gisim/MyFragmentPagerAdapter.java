package com.example.bawei.gisim;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/28 08:25
 * @Email 1151403054@qq.com
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list=new ArrayList<>();
    public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public void addFragment(Fragment fragment){
        list.add(fragment);
    }
}
