package com.example.bawei.homemodule.ui;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/31 09:50
 * @Email 1151403054@qq.com
 */
public class MyActivityBean  {
    String time;
    String ActivityName;
    String neirong;

    public MyActivityBean(String time, String activityName, String neirong) {
        this.time = time;
        ActivityName = activityName;
        this.neirong = neirong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }
}
