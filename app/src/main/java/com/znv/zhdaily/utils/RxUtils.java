package com.znv.zhdaily.utils;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 提供一些rxjava 的Transformer
 *
 * Created by znv on 2017/3/29.
 */

public class RxUtils {
    public static <T> ObservableTransformer<T, T> normalSchedulerTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                            @Override
                            public ObservableSource<? extends T> apply(@NonNull Throwable throwable) throws Exception {
                                return Observable.empty();
                            }
                        });

            }
        };
    }


}
