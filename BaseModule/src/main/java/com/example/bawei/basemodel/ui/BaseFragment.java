package com.example.bawei.basemodel.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/27 19:05
 * @Email 1151403054@qq.com
 */
public abstract class BaseFragment extends Fragment {

    protected View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(getLayoutId(), container, false);
        initView(savedInstanceState);
        initData();
        doEvent();
        return mRoot;
    }


    /***
     * 获取布局文件 id
     * @return R.layout
     */
    protected abstract int getLayoutId();

    /***
     * 初始化控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /***
     * 初始化数据
     */
    protected abstract void initData();

    /***
     * 点击事件
     */
    protected abstract void doEvent();
}

