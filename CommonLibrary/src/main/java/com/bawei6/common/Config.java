package com.bawei6.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 11:06
 * @Email 1151403054@qq.com
 */
public class Config {
    private static Config instance=new Config();
    private Config(){}
    public static Config getInstance(){
        if(instance!=null){
            return instance;
        }
        return null;
    }
    public static final int GETGROUP=300;
    public static String username;
    public static String usercode;
    public static  String fileName = usercode+System.currentTimeMillis()+".jpg";
    public static final String imgremotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/img/" + fileName;
    public static final String imagefile="img/" + fileName;
    public static final String SPACENAME ="baweitest6";
    public  void getSP(Context context){
        SharedPreferences yxh = context.getSharedPreferences("yxh", 0);
        if(yxh!=null){
            String spusername = yxh.getString("username", "");
            username=spusername;
            LogUtils.d(username);
            String spusercode=yxh.getString("username","");
            usercode=spusercode;
        }
    }
}
