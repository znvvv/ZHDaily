package com.znv.zhdaily.app;

import android.app.Application;
import android.content.Context;



public class App extends Application {
    private static Context mContext;



    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();


    }
    /**
     * @return 全局的context的对象
     */
    public static Context getContext() {
        return mContext;
    }


}
