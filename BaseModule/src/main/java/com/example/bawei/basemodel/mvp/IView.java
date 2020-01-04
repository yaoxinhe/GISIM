package com.example.bawei.basemodel.mvp;

import androidx.lifecycle.LifecycleOwner;

/***
 * @Author yxh
 * @CreateDate 2019-12-02
 * @Desc V 层的基接口
 * @Version 1.0
 */
public interface IView {

    /***
     * @Desc 显示对话框
     */
    void showLoading();

    /***
     * @Desc 隐藏对话框
     */
    void showHideIng();

    /***
     * 获取LifecycleOwner
     */
    LifecycleOwner getOwner();
}
