package com.znv.zhdaily.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.znv.zhdaily.R;
import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.utils.SpUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity的基类
 * Created by znv on 2017/3/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBinder;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        beforeSetContentView();
        setContentView(getContentViewId());
        mBinder = ButterKnife.bind(this);
        init();
    }

    /**
     * 根据在sp中保存的值初始化日/夜间模式
     */
    private void initTheme() {
        if(SpUtils.getBoolean(this, Constants.THEME_NIGHT,false)){
            setTheme(R.style.AppTheme_NightTheme);
        }else {
            setTheme(R.style.AppTheme_DayTheme);
        }
    }


    /**
     * 在setContentView之前调用此方法
     */
    protected void beforeSetContentView(){};

    /**
     *setContentView()时调用此方法，返回值作为setContentView()的参数
     * @return 视图的id
     */
    protected abstract int getContentViewId();

    /**
     * Activity的初始化操作，在setContentView后调用
     */
    protected abstract void init();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
            mBinder=null;
        }
    }
}
