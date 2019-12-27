package com.example.bawei.basemodel.rx;

import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/***
 * @Author Gcvition
 * @CreateDate 2019-12-06
 * @Desc Observable用于线程调度的类
 * @Version 1.0
 */
public class BaseObservable {

    public static <T> void doObserver(Observable<T> observable, BaseObserver<T> observer, LifecycleOwner owner) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider
                        .from(owner)))
                .subscribe(observer);
    }
}
