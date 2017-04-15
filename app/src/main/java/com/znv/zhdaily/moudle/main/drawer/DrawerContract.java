package com.znv.zhdaily.moudle.main.drawer;

import com.znv.zhdaily.app.BaseRxPresenter;
import com.znv.zhdaily.app.BaseView;
import com.znv.zhdaily.model.entity.ThemesEntity;

import java.util.List;

/**
 * 侧边栏显示的Contract类
 *
 * Created by znv on 2017/3/29.
 */



public interface DrawerContract {


    interface View extends BaseView{
        /**
         * 显示DrawerFragment 所有主题
         * @param beans 数据
         */
        void showThemes(List<ThemesEntity.OthersBean> beans);

        /**
         * 日夜间模式切换
         */
        void changeTheme();
    }

    abstract class Presenter<R> extends BaseRxPresenter<R,View>{
        /**
         * 加载知乎日报的所有主题类型
         */
       abstract void loadThemes();

        /**
         * 注册日夜间切换的rxbus事件
         */
        abstract void registBusForChangeTheme();
    }
}
