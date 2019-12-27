package com.example.bawei.basemodel.ui;

import androidx.fragment.app.Fragment;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/27 19:05
 * @Email 1151403054@qq.com
 */
public abstract class BaseFragment extends Fragment {




    /***
     * 获取布局文件 id
     * @return R.layout
     */
    protected abstract int getLayoutId();

    /***
     * 初始化控件
     */
    protected abstract void initView();

    /***
     * 初始化数据
     */
    protected abstract void initData();

    /***
     * 点击事件
     */
    protected abstract void doEvent();
}

