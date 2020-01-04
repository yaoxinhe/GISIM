package com.example.bawei.addfriend.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/3 09:29
 * @Email 1151403054@qq.com
 */
public class AddBean {

    /**
     * data : true
     */

    private boolean data;
    /**
     * code : 200
     * msg : 操作成功
     */

    private int code;
    private String msg;

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
