package com.example.bawei.basemodel.toast;

import android.content.Context;
import android.view.ViewDebug;
import android.widget.Toast;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/10 17:34
 * @Email 1151403054@qq.com
 */
public class ToastUtils {
   private static Context context;
    private static ToastUtils toastUtils=new ToastUtils();
    public static ToastUtils getInstance(){
        return toastUtils;
    }
    public  void init(Context context){
        this.context=context;
    }
    private Boolean flag=false;
    public static void Toastu(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
