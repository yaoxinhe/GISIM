package com.bawei6.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/1 20:26
 * @Email 1151403054@qq.com
 */
public class PhoneUtil {

    // 号码
    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

    //上下文对象
    private Context context;
    //联系人提供者的uri
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public PhoneUtil(Context context){
        this.context = context;
    }

    //获取所有联系人
    public List<PhoneDto> getPhone(){
        String fla="";
        List<PhoneDto> phoneDtos = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        //phonebook_label拿到当前联系人的大写首字母并且按照首字母排序
        Cursor cursor = cr.query(phoneUri,null,null,null,"phonebook_label");
        while (cursor.moveToNext()){
            String num = cursor.getString(cursor.getColumnIndex(NUM));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String phonebook_label = cursor.getString(cursor.getColumnIndex("phonebook_label"));
            PhoneDto phoneDto = new PhoneDto(name, num, phonebook_label, 1);
            if(fla.equals(phonebook_label)){
                phoneDtos.add(phoneDto);
            }else{
                PhoneDto phoneDto1 = new PhoneDto(null, null, phonebook_label, 0);
                fla=phonebook_label;
                phoneDtos.add(phoneDto1);
                phoneDtos.add(phoneDto);

            }
        }
        return phoneDtos;
    }
}