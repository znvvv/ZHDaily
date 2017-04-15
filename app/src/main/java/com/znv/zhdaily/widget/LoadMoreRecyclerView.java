package com.znv.zhdaily.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 可以加载更多的RecyclerView
 *
 * Created by znv on 2017/4/9.
 */

public class LoadMoreRecyclerView extends RecyclerView {


    private onLoadMoreListener mLoadMoreListener;

    public LoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        bindListener();
    }

    public onLoadMoreListener getLoadMoreListener() {
        return mLoadMoreListener;
    }

    public void setLoadMoreListener(onLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    private void bindListener() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LayoutManager layoutManager = getLayoutManager();
                LinearLayoutManager linearLayoutManager = null;
                if (layoutManager == null) {
                    throw new RuntimeException("must have LayoutManager");
                }
                if (layoutManager instanceof LinearLayoutManager) {
                    linearLayoutManager = (LinearLayoutManager) layoutManager;
                } else {
                    throw new RuntimeException("must be linearLayoutManager");
                }

                int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                int itemCoumt = linearLayoutManager.getItemCount();
                if (lastPosition >= itemCoumt - 1 && mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore();
                }
            }

        });


    }


    public interface onLoadMoreListener {
        void onLoadMore();
    }


}
