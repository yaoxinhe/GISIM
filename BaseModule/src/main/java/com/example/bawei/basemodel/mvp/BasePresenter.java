package com.example.bawei.basemodel.mvp;


/***
 * @Author yxh
 * @CreateDate 2019-12-02
 * @Desc P 层的基类
 * @Version 1.0
 */
public class BasePresenter<V extends IView, M extends IModel> {

    public V mView;
    public M mModel;
    //绑定方法
    public void attachView(V view){
        mView=view;
    }
    //解绑方法
    public void dettachView(){
        if(mView!=null){
            mView=null;
        }
    }


}
