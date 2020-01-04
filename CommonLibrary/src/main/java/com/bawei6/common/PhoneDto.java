package com.bawei6.common;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/1 20:25
 * @Email 1151403054@qq.com
 */
public class PhoneDto implements MultiItemEntity{
    private String name;        //联系人姓名
    private String telPhone;    //电话号码
    private String PhoneBookLael;
    private int flag;

    public PhoneDto(String name, String telPhone, String phoneBookLael, int flag) {
        this.name = name;
        this.telPhone = telPhone;
        PhoneBookLael = phoneBookLael;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getPhoneBookLael() {
        return PhoneBookLael;
    }

    public void setPhoneBookLael(String phoneBookLael) {
        PhoneBookLael = phoneBookLael;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public int getItemType() {
        return flag;
    }
}