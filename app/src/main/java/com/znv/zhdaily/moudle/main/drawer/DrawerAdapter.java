package com.znv.zhdaily.moudle.main.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.model.entity.ThemesEntity;
import com.znv.zhdaily.moudle.main.drawer.holder.DrawerHomeViewHolder;
import com.znv.zhdaily.moudle.main.drawer.holder.DrawerHeaderViewHolder;
import com.znv.zhdaily.moudle.main.drawer.holder.DrawerNormalViewHolder;

import java.util.List;

/**
 * 控制侧边栏RecyclerView显示的adapter
 *
 * Created by znv on 2017/3/28.
 */



public class DrawerAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private List<ThemesEntity.OthersBean> mThemeBeans;

    private OnDrawerItemSelectListener mOnDrawerItemSelectistener;
    private int mSelction = 1;


    public static final int TYPE_HEADER_ITEM = 0;
    public static final int TYPE_HOME_ITEM = 1;
    public static final int TYPE_NORMAL_ITEM = 6;


    public DrawerAdapter() {

    }

    public DrawerAdapter(List<ThemesEntity.OthersBean> beans) {

        mThemeBeans = beans;
    }

    @Override
    public int getItemViewType(int position) {
        return position > 1 ? TYPE_NORMAL_ITEM : position;
    }

    @Override
    public int getItemCount() {
        return mThemeBeans == null ? 2 : mThemeBeans.size() + 2;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        switch (viewType) {
            case TYPE_HEADER_ITEM:

                holder = DrawerHeaderViewHolder.newInstance(parent);
                break;
            case TYPE_HOME_ITEM:
                holder = DrawerHomeViewHolder.newInstance(parent,this);
                break;
            case TYPE_NORMAL_ITEM:
                holder = DrawerNormalViewHolder.newInstance(parent,this);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        holder.bindData(position, viewType, null);
    }




    public void setSelection(int position) {

        int lastpos = mSelction;

        mSelction = position;

        notifyItemChanged(lastpos);

        notifyItemChanged(mSelction);

    }


    public List<ThemesEntity.OthersBean> getThemeBeans() {
        return mThemeBeans;
    }

    public void setThemeBeans(List<ThemesEntity.OthersBean> themeBeans) {
        mThemeBeans = themeBeans;
    }

    public int getSelction() {
        return mSelction;
    }

    public void setSelction(int selction) {
        mSelction = selction;
    }


    public OnDrawerItemSelectListener getOnDrawerItemSelectListener() {
        return mOnDrawerItemSelectistener;
    }

    public void setOnDrawerItemSelectListener(OnDrawerItemSelectListener onDrawerItemSelectistener) {
        mOnDrawerItemSelectistener = onDrawerItemSelectistener;
    }


}
