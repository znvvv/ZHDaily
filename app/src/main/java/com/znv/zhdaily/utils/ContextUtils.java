package com.znv.zhdaily.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.znv.zhdaily.app.App;
import com.znv.zhdaily.config.Config;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * 封装了一些常用方法的工具类
 *
 * Created by znv on 2017/3/20.
 */

public class ContextUtils {


    public static void startActivity(Activity activity, Class<?> cls){
        checkNotNull(activity);
        checkNotNull(cls);
        activity.startActivity(new Intent(activity,cls));
    }

    public static void startActivity(Context context, Class<?> cls, Bundle bundle){

        context.startActivity(new Intent(context,cls).putExtras(bundle));
    }

    public static void fullScreen(Activity activity){

        checkNotNull(activity);

        Window window = activity.getWindow();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static File getHttpCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(App.getContext().getExternalCacheDir(), Config.Network.HTTP_CACHE_DIR);
        }
        return new File(App.getContext().getCacheDir(),Config.Network.HTTP_CACHE_DIR);
    }

    /**
     * 填充布局
     * @param resId
     * @param parent
     * @return
     */
    public static View inflate(int resId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    public static void toast(Context context, CharSequence text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

}
