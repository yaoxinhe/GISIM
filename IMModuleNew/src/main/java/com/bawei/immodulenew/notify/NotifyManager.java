package com.bawei.immodulenew.notify;

import com.bawei.immodulenew.entity.BaseMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:57
 * @Email 1151403054@qq.com
 */
public class NotifyManager {
    private static NotifyManager instance=new NotifyManager();
    private NotifyManager(){}
    public static NotifyManager getInstance(){
        return instance;
    }
    private List<IObserver> list=new ArrayList<>();

    public void addObserver(IObserver observer){
        if (list.contains(observer)){
            return;
        }
        list.add(observer);
    }

    public void removeObserver(IObserver observer){
        if (list.contains(observer)){
            list.remove(observer);
        }
    }

    public void notifyAllObserver(BaseMsg msg){
        for(IObserver observer:list){
            observer.nodify(msg);
        }
    }
}
