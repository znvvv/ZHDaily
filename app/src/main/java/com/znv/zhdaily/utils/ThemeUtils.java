package com.znv.zhdaily.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 切换主题（日夜间模式）的工具类
 * Created by znv on 2017/4/11.
 */

public class ThemeUtils {

    /**
     * 根据不同主题给view设置不同背景
     * @param context
     * @param view
     * @param attrId
     */

    public static void setViewBackground(Context context, View view, int attrId) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attrId, value, true);
        view.setBackgroundResource(value.resourceId);
    }

    /**
     * 根据不同主题给textview设置不同TextAppearance
     * @param context
     * @param tv
     * @param attrId
     */
    public static void setTextAppearance(Context context, TextView tv, int attrId) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attrId, value, true);
        tv.setTextAppearance(context, value.resourceId);
    }


    /**
     * 根据不同主题给CardView设置不同背景
     * @param context
     * @param cv
     * @param attrId
     */
    public static void setCardViewBackground(Context context, CardView cv, int attrId) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attrId, value, true);

        cv.setCardBackgroundColor(ContextCompat.getColor(context, value.resourceId));

    }

    /**
     * 根据不同主题设置ActionBarBar颜色
     * @param context
     * @param toolbar
     * @param attrId
     */
    public static void setActionBarColor(Context context, Toolbar toolbar, int attrId) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attrId, value, true);
        toolbar.setBackgroundColor(ContextCompat.getColor(context, value.resourceId));

    }

    /**
     * 根据不同主题设置StatusBar颜色
     * @param activity
     * @param attrId
     */
    public static void setStatusBarColor(Activity activity, int attrId) {

        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue value = new TypedValue();
            activity.getTheme().resolveAttribute(attrId, value, true);

            Window window = activity.getWindow();

            window.setStatusBarColor(ContextCompat.getColor(activity, value.resourceId));
        }

    }

    /**
     * 清除RecyclerView的缓存item
     * @param recyclerView
     */
    public static void invalidateRecyclerViewCacheItem(RecyclerView recyclerView) {
        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
        try {
            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
            declaredField.setAccessible(true);

            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);

            declaredMethod.invoke(declaredField.get(recyclerView), new Object[0]);

            RecyclerView.RecycledViewPool recycledViewPool = recyclerView.getRecycledViewPool();
            recycledViewPool.clear();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
