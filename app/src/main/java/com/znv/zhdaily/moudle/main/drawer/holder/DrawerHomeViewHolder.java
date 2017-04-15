package com.znv.zhdaily.moudle.main.drawer.holder;

import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.moudle.main.drawer.DrawerAdapter;
import com.znv.zhdaily.moudle.main.drawer.OnDrawerItemSelectListener;
import com.znv.zhdaily.utils.ContextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 侧边栏首页item的viewholder
 *
 * Created by znv on 2017/4/14.
 */



public  class DrawerHomeViewHolder extends BaseViewHolder {

    @BindView(R.id.drawer_item_logo)
    ImageView drawerItemLogo;

    @BindView(R.id.drawer_item_title)
    TextView drawerItemTitle;

    @BindView(R.id.drawer_item_subscribe_button)
    ImageView drawerItemSubscribeButton;

    @BindView(R.id.drawer_item_layout)
    RelativeLayout drawerItemLayout;

    private DrawerAdapter mDrawerAdapter;

    private DrawerHomeViewHolder(View itemView, DrawerAdapter adapter) {
        super(itemView);
        mDrawerAdapter = adapter;
        ButterKnife.bind(this, itemView);
    }



    @Override
    public void bindData(final int position, int viewType, Object data) {
        itemViewwHighLight(position);

        drawerItemLogo.setVisibility(View.VISIBLE);
        drawerItemTitle.setText("首页");
        drawerItemTitle.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.blue));

        drawerItemSubscribeButton.setVisibility(View.GONE);

       itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mDrawerAdapter.setSelection(position);
               OnDrawerItemSelectListener listener = mDrawerAdapter.getOnDrawerItemSelectListener();

               if (listener == null) {
                   return;
               }
               listener.onDrawerHomeItemSelect();
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


    public static DrawerHomeViewHolder newInstance(ViewGroup parent , DrawerAdapter adapter) {

        View view = ContextUtils.inflate(R.layout.item_drawer_normal, parent);
        return new DrawerHomeViewHolder(view,adapter);
    }


}
