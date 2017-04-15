package com.znv.zhdaily.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView.ViewHolder的一个实现类，用作于这个项目的其他Vivewholder的基类
 * 封装一些方法
 * @param <T> 数据类型
 *
 * Created by znv on 2017/4/10.
 */


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }




    /**
     *根据传来的数据更新ui等操作
     * @param position item位置
     * @param viewType itemtype 可能不传过去
     * @param data 数据
     */
    public abstract void bindData(int position, int viewType,T data);

}
