package com.example.bawei.basemodel.rx;

import android.annotation.SuppressLint;

import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/***
 * @Author yxh
 * @CreateDate 2019-12-06
 * @Desc Observable用于线程调度的类
 * @Version 1.0
 */
public class BaseObservable {

    public static <T> void doObserver(Observable<T> observable, BaseObserver<T> observer, LifecycleOwner owner) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(owner)))
                .subscribe(observer);
    }
    @SuppressLint("CheckResult")
    public static void  doObserver(Observable observable1, Observable observable2 , BaseObserver observer, LifecycleOwner owner) {
        Observable merge = Observable.concat(observable1, observable2);
        doObserver(merge,observer,owner);
    }
}
