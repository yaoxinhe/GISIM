package com.bawei.immodulenew.notify;

import com.bawei.immodulenew.entity.BaseMsg;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:56
 * @Email 1151403054@qq.com
 */
public interface IObserver {
    void nodify(BaseMsg msg);
}
