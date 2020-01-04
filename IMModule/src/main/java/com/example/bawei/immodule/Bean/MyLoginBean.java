package com.example.bawei.immodule.Bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/30 20:22
 * @Email 1151403054@qq.com
 */
public class MyLoginBean {
    private String username;
    private String pwd;

    public MyLoginBean(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
