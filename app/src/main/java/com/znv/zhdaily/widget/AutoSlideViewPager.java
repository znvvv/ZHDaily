package com.znv.zhdaily.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 *
 *自动移动的viewpager
 * Created by znv on 2017/4/3.
 */

public class AutoSlideViewPager extends ViewPager {

    private Disposable mDisposable;

    public AutoSlideViewPager(Context context) {
        this(context, null);
    }

    public AutoSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    public void startAutoSlide() {

        PagerAdapter adapter = getAdapter();


        final int count = adapter.getCount();
        if (adapter==null || adapter.getCount() < 1) {

            return;
        }

        mDisposable = Observable.interval(5, 5, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map(new Function<Long, Long>() {
            @Override
            public Long apply(@NonNull Long aLong) throws Exception {
                return (getCurrentItem() + new Long(1)) % count;
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                setCurrentItem(aLong.intValue());
            }
        });
    }

    public void stopAutoSlide() {
        if (mDisposable == null || mDisposable.isDisposed()) {
            return;
        }
        mDisposable.dispose();
        mDisposable = null;
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoSlide();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoSlide();
    }
}
