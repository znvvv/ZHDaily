package com.znv.zhdaily.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.znv.zhdaily.app.App;

/**
 * 网络的工具类
 *
 * Created by znv on 2017/3/28.
 */
public class NetWorkUtils {

    /**
     * 检测是否有网络
     * @return
     */
    public static final boolean isNetWorkAvailable() {
        Context context = App.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();
            if (networkInfos != null) {
                for (NetworkInfo info : networkInfos) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
