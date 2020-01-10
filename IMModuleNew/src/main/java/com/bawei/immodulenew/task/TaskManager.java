package com.bawei.immodulenew.task;

import java.util.concurrent.Executors;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:27
 * @Email 1151403054@qq.com
 */
public class TaskManager {
    private static TaskManager instance=new TaskManager();
    private TaskManager(){}
    public static TaskManager getInstance(){
        return instance;
    }

    /**
     * 执行任务
     * @param runnable
     */
    public void doTask(Runnable runnable){
        Executors.newCachedThreadPool().execute(runnable);

    }
}
