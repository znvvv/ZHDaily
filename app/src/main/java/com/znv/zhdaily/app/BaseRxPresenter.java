package com.znv.zhdaily.app;


import com.znv.zhdaily.utils.ClazzUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 一个basePresenter的封装,主要目的是用CompositeDisposable来全局控制订阅的取消
 * Created by znv on 2017/3/29.
 */

public  abstract class BaseRxPresenter<R,V extends BaseView> implements BasePresenter<V>{

    protected R mRepository;

    protected V mView;

    /**
     * 学习了https://github.com/Freelander/Elephant 在构造器中获取泛型反射出对象的实现
     */
    public BaseRxPresenter() {

        mRepository = ClazzUtils.getGenericInstance(this, 0);

    }

    private CompositeDisposable composite = new CompositeDisposable();


    /**
     * 清除所有的订阅，防止内存泄漏
     */
    @Override
    public void unsubscribe() {
        composite.clear();
    }


    public boolean addDisposable(Disposable d){
        return composite.add(d);
    }


    @Override
    public void setView(V view) {
        mView = view;
    }
}
