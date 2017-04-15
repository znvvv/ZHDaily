package com.znv.zhdaily.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferences的工具类
 *
 * Created by znv on 2017/4/11.
 */

public class SpUtils {


    public static void putString(Context context,String key,String value){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(key,value).commit();
    }

    public static String getString(Context context,String key,String defaultValue){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key,defaultValue);
    }

    public static void putBoolean(Context context,String key,boolean value){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(key,value).commit();
    }


    public static boolean getBoolean(Context context,String key,boolean defaultValue){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
       return sharedPreferences.getBoolean(key,defaultValue);
    }


    public static void putLong(Context context,String key,long value){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        sharedPreferences.edit().putLong(key,value).commit();
    }


    public static long getLong(Context context,String key,long defaultValue){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getLong(key,defaultValue);
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
