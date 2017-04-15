package com.znv.zhdaily.moudle.main.stories.theme;

import com.znv.zhdaily.app.BaseRxPresenter;
import com.znv.zhdaily.app.BaseView;
import com.znv.zhdaily.model.entity.ThemeStoriesEntity;

/**
 * themefragment的contract
 * Created by znv on 2017/4/3.
 */


public interface ThemeContract {
    interface View extends BaseView {
        /**
         * 显示主题界面
         * @param themeStoriesEntity 数据
         */
        void showThemeStroies(ThemeStoriesEntity themeStoriesEntity);

        /**
         * 显示是否刷新
         * @param refreshing 是否刷新
         */
        void setRefreshing(boolean refreshing);

        /**
         * 根据传进来的themeId 刷新ui
         * @param themeId 主题id
         */
        void refreshUI(int themeId);

        /**
         * 清除当前的ui
         */
        void clearUI();
    }

    abstract class Presenter<R> extends BaseRxPresenter<R,View> {

        /**
         * 根据themeId加载主题数据
         * @param themeId 主题id
         */
        abstract void loadThemeStories(int themeId);

        /**
         * 根据themeId刷新主题数据
         * @param themeId 主题id
         */
        abstract void refreshThemeStroies(int themeId);


        /**
         * 注册drawerItem点击的rxbus事件
         */
        abstract void registBusForCloesRefresh();
    }
}
