package com.znv.zhdaily.moudle.main.drawer.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.utils.ContextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  侧边栏头部的viewholder
 *
 * Created by znv on 2017/4/14.
 */


public class DrawerHeaderViewHolder extends BaseViewHolder {

    @BindView(R.id.drawer_user_name)
    public TextView drawerUserName;
    @BindView(R.id.tv_favorites)
    public TextView tvFavorites;
    @BindView(R.id.drawer_offline_progress)
    public TextView drawerOfflineProgress;
    @BindView(R.id.drawer_header)
    public LinearLayout drawerHeader;

    public DrawerHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }

    @Override
    public void bindData(int position, int viewType, Object data) {

    }

    public static DrawerHeaderViewHolder newInstance(ViewGroup parent) {

        View view = ContextUtils.inflate(R.layout.item_drawer_header, parent);
        return new DrawerHeaderViewHolder(view);
    }


}
