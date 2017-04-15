package com.znv.zhdaily.moudle.main.drawer.holder;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.model.entity.ThemesEntity;
import com.znv.zhdaily.moudle.main.drawer.DrawerAdapter;
import com.znv.zhdaily.moudle.main.drawer.OnDrawerItemSelectListener;
import com.znv.zhdaily.utils.ContextUtils;
import com.znv.zhdaily.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 侧边栏普通item的viewholder
 * <p>
 * Created by znv on 2017/4/14.
 */


public class DrawerNormalViewHolder extends BaseViewHolder {

    @BindView(R.id.drawer_item_logo)
    public ImageView drawerItemLogo;

    @BindView(R.id.drawer_item_title)
    public TextView drawerItemTitle;

    @BindView(R.id.drawer_item_subscribe_button)
    public ImageView drawerItemSubscribeButton;

    @BindView(R.id.drawer_item_layout)
    public RelativeLayout drawerItemLayout;

    DrawerAdapter mDrawerAdapter;


    private DrawerNormalViewHolder(View itemView, DrawerAdapter adapter) {
        super(itemView);
        mDrawerAdapter = adapter;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(final int position, int viewType, Object data) {

        itemViewwHighLight(position);

        ThemesEntity.OthersBean bean = mDrawerAdapter.getThemeBeans().get(position - 2);

        ThemeUtils.setTextAppearance(itemView.getContext(), drawerItemTitle, R.attr.daily_textAppearance_drawer_item_primary);

        drawerItemLogo.setVisibility(View.GONE);
        drawerItemTitle.setText(bean.getName());
        drawerItemSubscribeButton.setVisibility(View.VISIBLE);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerAdapter.setSelection(position);
                OnDrawerItemSelectListener listener = mDrawerAdapter.getOnDrawerItemSelectListener();
                if (listener == null) {
                    return;
                }

                int selction = mDrawerAdapter.getSelction();
                ThemesEntity.OthersBean bean = selction > 1 ? mDrawerAdapter.getThemeBeans().get(selction - 2) : null;
                listener.onDrawerNormalItemSelect(bean);
            }
        });

    }


    public void itemViewwHighLight(int position) {

        TypedValue typeValue = new TypedValue();
        if (position == mDrawerAdapter.getSelction()) {
            itemView.getContext().getTheme().resolveAttribute(R.attr.drawerBackgroundHighlight, typeValue, true);
        } else {
            itemView.getContext().getTheme().resolveAttribute(R.attr.drawerBackgroundNormal, typeValue, true);
        }

        drawerItemLayout.setBackgroundResource(typeValue.resourceId);

    }

    public static DrawerNormalViewHolder newInstance(ViewGroup parent, DrawerAdapter adapter) {

        View view = ContextUtils.inflate(R.layout.item_drawer_normal, parent);
        return new DrawerNormalViewHolder(view, adapter);
    }


}
