package com.znv.zhdaily.moudle.main.stories.home;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseRxFragment;
import com.znv.zhdaily.model.entity.LatestStoriesEntity;
import com.znv.zhdaily.moudle.main.stories.StoryViewHolder;
import com.znv.zhdaily.moudle.main.stories.home.holder.DateViewHolder;
import com.znv.zhdaily.utils.NetWorkUtils;
import com.znv.zhdaily.utils.ThemeUtils;
import com.znv.zhdaily.widget.LoadMoreRecyclerView;

import butterknife.BindView;

/**
 * homefragment首页的fragment
 * Created by znv on 2017/4/3.
 */



public class HomeFragment extends BaseRxFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.home_recyclerView)
    LoadMoreRecyclerView mHomeRecyclerView;

    @BindView(R.id.home_layout)
    SwipeRefreshLayout mHomeLayout;

    private HomeAdapter mAdapter;

    private String mCurrDate;
    private LinearLayoutManager mLinearLayoutManager;


    private String mCurrentDate = "";

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        mAdapter = new HomeAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mHomeRecyclerView.setLayoutManager(mLinearLayoutManager);
        mHomeRecyclerView.setAdapter(mAdapter);


        mHomeRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadBeforeStories(mCurrDate);
            }
        });

        mHomeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                changeActionBarTitle();
            }
        });

        mHomeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!NetWorkUtils.isNetWorkAvailable()){
                    setRefreshing(false);
                }
                mPresenter.loadLatestStories();
            }
        });
        mHomeLayout.setColorSchemeResources(R.color.blue);



    }

    private void changeActionBarTitle() {
        int position = mLinearLayoutManager.findFirstVisibleItemPosition();
        String text = mAdapter.getDateText(position);

        if (text == null) {
            text = getActivity().getResources().getString(R.string.home_text);
        }

        if (!mCurrentDate.equals(text)) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(text);
            mCurrentDate = text;
        }
    }

    @Override
    public void showLatestStories(LatestStoriesEntity entity) {

        mAdapter.clear();
        mHomeRecyclerView.scrollToPosition(0);
        mAdapter.appendData(entity);

        mCurrDate = entity.getDate();

    }

    @Override
    public void showBeforeStories(LatestStoriesEntity entity) {
        mCurrDate = entity.getDate();
        mAdapter.appendData(entity);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mHomeLayout.setRefreshing(refreshing);
    }

    @Override
    public void refreshUI() {
        mPresenter.loadLatestStories();
    }



    @Override
    public void changeTheme() {



        ThemeUtils.invalidateRecyclerViewCacheItem(mHomeRecyclerView);

        FragmentActivity context = getActivity();

        ThemeUtils.setViewBackground(context,mHomeRecyclerView,R.attr.activityBackgroundGray);

        int count = mHomeRecyclerView.getChildCount();

        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();


        for (int childIndex = 0; childIndex < count; childIndex++) {
            RecyclerView.ViewHolder viewHolder = mHomeRecyclerView.findViewHolderForAdapterPosition(childIndex + firstVisibleItemPosition);

            int viewType = viewHolder.getItemViewType();

            switch (viewType) {
                case HomeAdapter.TYPE_DATE:
                    DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;

                    ThemeUtils.setTextAppearance(context, dateViewHolder.tvHomeNewsDate, R.attr.listHeaderTitle);

                    break;
                case HomeAdapter.TYPE_STORY:


                    StoryViewHolder storyViewHolder = (StoryViewHolder) viewHolder;
                    ThemeUtils.setCardViewBackground(context, storyViewHolder.mCardView, R.attr.cardItemBackground);

                    storyViewHolder.initTitleColor();

                    break;
            }

        }

    }


    /**
     * 在这里注册改变日夜间模式的rxbus事件
     */

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.registBusForChangeTheme();
        mPresenter.registBusForCloesRefresh();
    }


}

