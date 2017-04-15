package com.znv.zhdaily.utils;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * rxbus，来自网上
 *
 * Created by znv on 2017/4/11.
 */

public class RxBus {
    private static volatile RxBus defaultInstance;

    private FlowableProcessor<Object> bus ;

    public RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance ;
    }

    public void post (Object o) {
        bus.onNext(o);

    }


    public <T> Flowable<T> toObservable (Class<T> eventType) {
        return bus.ofType(eventType);

    }
}
