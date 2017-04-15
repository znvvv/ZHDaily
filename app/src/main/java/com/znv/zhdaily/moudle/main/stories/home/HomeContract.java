package com.znv.zhdaily.moudle.main.stories.home;

import com.znv.zhdaily.app.BaseRxPresenter;
import com.znv.zhdaily.app.BaseView;
import com.znv.zhdaily.model.entity.LatestStoriesEntity;

/**
 * Created by znv on 2017/4/3.
 */


public interface HomeContract {
    interface View extends BaseView {
        /**
         * 显示首页homefragment最新数据
         * @param entity 数据
         */
        void showLatestStories(LatestStoriesEntity entity);

        /**
         * 显示首页homefragment以前的数据
         * @param entity 数据
         */
        void showBeforeStories(LatestStoriesEntity entity);

        /**
         * 显示首页homefragment刷新/不刷新
         */
        void setRefreshing(boolean refreshing);

        /**
         * homefragment UI的重新刷新
         */
        void refreshUI();


        /**
         * 日夜间模式切换
         */
        void changeTheme();

    }

    abstract class Presenter<R> extends BaseRxPresenter<R,View> {
        /**
         *加载首页homefragment最新数据
         */
        abstract void loadLatestStories();

        /**
         * 首页homefragment以前的数据
         * @param date 时间
         */
        abstract void loadBeforeStories(String date);

        /**
         * 注册切换日夜间模式的rxbus
         */
        abstract void registBusForChangeTheme();

        /**
         * 注册draweFragment item 点击的rxbus
         */
        abstract void registBusForCloesRefresh();


    }
}
