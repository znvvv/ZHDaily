package com.znv.zhdaily.moudle.main.drawer;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseRxFragment;
import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.model.entity.ThemesEntity;
import com.znv.zhdaily.moudle.main.drawer.holder.DrawerHeaderViewHolder;
import com.znv.zhdaily.moudle.main.drawer.holder.DrawerHomeViewHolder;
import com.znv.zhdaily.moudle.main.drawer.holder.DrawerNormalViewHolder;
import com.znv.zhdaily.utils.RxBus;
import com.znv.zhdaily.utils.ThemeUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 侧边栏的Fragment
 */

public class DrawerFragment extends BaseRxFragment<DrawerPresenter> implements DrawerContract.View, OnDrawerItemSelectListener {


    @BindView(R.id.nav_rv_content)
    RecyclerView mRvContent;


    private DrawerAdapter mDrawerAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_navigation_drawer;
    }

    @Override
    protected void init() {
        mDrawerAdapter = new DrawerAdapter();
        mDrawerAdapter.setOnDrawerItemSelectListener(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRvContent.setLayoutManager(mLayoutManager);
        mRvContent.setAdapter(mDrawerAdapter);

    }

    @Override
    public void showThemes(List<ThemesEntity.OthersBean> beans) {
        if (mDrawerAdapter != null) {
            mDrawerAdapter.setThemeBeans(beans);
            mDrawerAdapter.notifyDataSetChanged();
            return;
        }

    }


    /**
     * 改变日夜间模式，夜间模式实现方法来自于http://www.jianshu.com/p/3b55e84742e5
     */

    @Override
    public void changeTheme() {

        ThemeUtils.invalidateRecyclerViewCacheItem(mRvContent);

        FragmentActivity context = getActivity();

        ThemeUtils.setViewBackground(context, mRvContent, R.attr.drawer_background_item_primary);

        int count = mRvContent.getChildCount();
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();


        for (int childIndex = 0; childIndex < count; childIndex++) {

            RecyclerView.ViewHolder viewHolder = mRvContent.findViewHolderForLayoutPosition(childIndex + firstVisibleItemPosition);

            int viewType = viewHolder.getItemViewType();

            switch (viewType) {
                case DrawerAdapter.TYPE_HEADER_ITEM:

                    DrawerHeaderViewHolder headerViewHolder = (DrawerHeaderViewHolder) viewHolder;
                    ThemeUtils.setViewBackground(context, headerViewHolder.drawerHeader, R.attr.drawer_background);
                    ThemeUtils.setTextAppearance(context, headerViewHolder.drawerUserName, R.attr.daily_textAppearance_drawer_primary);
                    ThemeUtils.setTextAppearance(context, headerViewHolder.tvFavorites, R.attr.daily_textAppearance_drawer_secondary);
                    ThemeUtils.setTextAppearance(context, headerViewHolder.drawerOfflineProgress, R.attr.daily_textAppearance_drawer_secondary);

                    break;
                case DrawerAdapter.TYPE_HOME_ITEM:
                    DrawerHomeViewHolder homeViewHolder = (DrawerHomeViewHolder) viewHolder;
                    homeViewHolder.itemViewwHighLight(childIndex);
                    break;
                case DrawerAdapter.TYPE_NORMAL_ITEM:
                    DrawerNormalViewHolder normalViewHolder = (DrawerNormalViewHolder) viewHolder;
                    ThemeUtils.setTextAppearance(context, normalViewHolder.drawerItemTitle, R.attr.daily_textAppearance_drawer_item_primary);
                    normalViewHolder.itemViewwHighLight(childIndex);

                    break;


            }

        }
    }

    /**
     * 当侧边栏的首页item点击时，调用此方法，将参数再传给Activity，这样的实现并不好
     * 可能用rxbus更舒服点，懒得改了
     */

    @Override
    public void onDrawerHomeItemSelect() {
        FragmentActivity activity = getActivity();
        if (activity instanceof OnDrawerItemSelectListener) {
            OnDrawerItemSelectListener listener = (OnDrawerItemSelectListener) activity;
            listener.onDrawerHomeItemSelect();

        }
    }

    /**
     * 当侧边栏的普通item点击时，调用此方法，将参数再传给Activity，这样的实现并不好
     * 可能用rxbus更舒服点，懒得改了
     */
    @Override
    public void onDrawerNormalItemSelect(ThemesEntity.OthersBean bean) {
        FragmentActivity activity = getActivity();
        if (activity instanceof OnDrawerItemSelectListener) {
            OnDrawerItemSelectListener listener = (OnDrawerItemSelectListener) activity;
            listener.onDrawerNormalItemSelect(bean);
            RxBus.getDefault().post(Constants.EVENT_CLOSE_REFRESH);

        }
    }


    /**
     * 在这里注册改变日夜间模式的rxbus事件
     */
    @Override
    public void onStart() {
        super.onStart();
        mPresenter.registBusForChangeTheme();

    }
}
