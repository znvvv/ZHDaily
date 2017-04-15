package com.znv.zhdaily.moudle.main.stories.theme;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseRxFragment;
import com.znv.zhdaily.model.entity.ThemeStoriesEntity;
import com.znv.zhdaily.utils.NetWorkUtils;

import butterknife.BindView;

/**
 * themefragment 主题fragment的界面
 *
 * Created by znv on 2017/4/3.
 */

public class ThemeFragment extends BaseRxFragment<ThemePresenter> implements ThemeContract.View {

    @BindView(R.id.theme_recyclerView)
    RecyclerView mThemeRecyclerView;
    @BindView(R.id.theme_layout)
    SwipeRefreshLayout mThemeLayout;
    private int mThemeId;

    private ThemeAdapter mThemeAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_theme;
    }

    public static ThemeFragment newInstance(Bundle args) {
        ThemeFragment fragment = new ThemeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        super.init();
        mThemeAdapter = new ThemeAdapter();
        mThemeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mThemeRecyclerView.setAdapter(mThemeAdapter);

        mThemeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!NetWorkUtils.isNetWorkAvailable()){
                    setRefreshing(false);
                }
                mPresenter.refreshThemeStroies(mThemeId);
            }
        });
        mThemeLayout.setColorSchemeResources(R.color.blue);

        mPresenter.loadThemeStories(mThemeId);

    }


    @Override
    public void showThemeStroies(ThemeStoriesEntity themeStoriesEntity) {


        mThemeRecyclerView.scrollToPosition(0);
        mThemeAdapter.setThemeStoriesEntity(themeStoriesEntity);
        mThemeAdapter.notifyDataSetChanged();

    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mThemeLayout.setRefreshing(refreshing);
    }

    @Override
    public void refreshUI(int themeId) {
        mThemeId = themeId;
        mPresenter.loadThemeStories(mThemeId);
    }

    @Override
    public void clearUI() {
        mThemeAdapter.setThemeStoriesEntity(null);
        mThemeAdapter.notifyDataSetChanged();
    }


    public int getThemeId() {
        return mThemeId;
    }

    public void setThemeId(int themeId) {
        mThemeId = themeId;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.registBusForCloesRefresh();
    }
}

