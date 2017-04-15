package com.znv.zhdaily.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.znv.zhdaily.utils.ClazzUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by znv on 2017/3/29.
 */

public abstract class BaseRxFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected P mPresenter;


    protected boolean isLoad = true;


    /**
     * 学习了https://github.com/Freelander/Elephant 在构造器中获取泛型反射出对象的实现
     */
    public BaseRxFragment() {

        mPresenter = ClazzUtils.getGenericInstance(this, 0);

        if (this instanceof BaseView) {
            mPresenter.setView(this);
        }
    }

    private Unbinder mBinder;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getContentViewId(), container, false);

        if(mBinder ==null){
            mBinder = ButterKnife.bind(this, view);
        }



        return view;
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }


    /**
     * 此方法在onViewCreated()中调用
     */
    protected void init() {
    }

    ;

    /**
     * 在onCreateView调用
     *
     * @return 视图的资源id
     */
    protected abstract int getContentViewId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinder != null) {
            mBinder.unbind();
            mBinder = null;
        }
        super.onDestroy();
    }


    /**
     * onstart时立即调用Presenter的subscribe()方法，参考了google的架构案例
     * 后面发现这种方法不够灵活，每次onstart都会调用subscribe()方法
     */
    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null && isLoad) {
            mPresenter.subscribe();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
            isLoad = false;

        }
    }


}
