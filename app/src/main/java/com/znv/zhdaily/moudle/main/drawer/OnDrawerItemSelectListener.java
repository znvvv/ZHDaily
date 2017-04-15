package com.znv.zhdaily.moudle.main.drawer;

import com.znv.zhdaily.model.entity.ThemesEntity;

/**
 * 自定义一个侧边栏点击的监听器类
 *
 * Created by znv on 2017/4/3.
 */


public interface OnDrawerItemSelectListener {

    /**
     * 当侧边栏的首页item点击时，调用此方法
     */
    public void onDrawerHomeItemSelect();


    /**
     * 当侧边栏的普通item点击时，调用此方法
     * @param bean
     */
    public void onDrawerNormalItemSelect(ThemesEntity.OthersBean bean);
}
