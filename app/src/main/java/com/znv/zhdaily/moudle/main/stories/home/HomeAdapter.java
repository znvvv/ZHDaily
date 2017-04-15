package com.znv.zhdaily.moudle.main.stories.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.model.entity.LatestStoriesEntity;
import com.znv.zhdaily.model.entity.StoryBean;
import com.znv.zhdaily.moudle.main.stories.StoryViewHolder;
import com.znv.zhdaily.moudle.main.stories.home.holder.DateViewHolder;
import com.znv.zhdaily.moudle.main.stories.home.holder.TopStoriesViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 首页homefragment的adapter，学习了https://github.com/hefuyicoder/ZhihuDaily的实现思路，很棒
 * 表示谢意
 *
 * Created by znv on 2017/4/4.
 */


public class HomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<ItemBean> mItemBeans;

    public static final int TYPE_TOP_STORIES = 0;
    public static final int TYPE_DATE = 1;
    public static final int TYPE_STORY = 2;


    public HomeAdapter() {
        mItemBeans = new ArrayList<ItemBean>();
    }

    public HomeAdapter(LatestStoriesEntity storiesEntity) {
        this();
        updateData(storiesEntity);
    }


    public void appendData(LatestStoriesEntity storiesEntity) {

        updateData(storiesEntity);

        notifyDataSetChanged();

    }

    private void updateData(LatestStoriesEntity storiesEntity) {
        checkNotNull(storiesEntity);
        ItemBean bean = null;
        if (mItemBeans.isEmpty() && !storiesEntity.getTop_stories().isEmpty()) {
            bean = new ItemBean();
            bean.type = TYPE_TOP_STORIES;
            bean.topStoriesBeans = storiesEntity.getTop_stories();
            mItemBeans.add(bean);
        }

        bean = new ItemBean();
        bean.type = TYPE_DATE;
        bean.date = storiesEntity.getDate();
        mItemBeans.add(bean);


        for (StoryBean entity : storiesEntity.getStories()) {
            bean = new ItemBean();
            bean.type = TYPE_STORY;
            bean.story = entity;
            bean.date = storiesEntity.getDate();
            mItemBeans.add(bean);
        }


    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseViewHolder holder = null;
        View itemView = null;
        switch (viewType) {
            case TYPE_TOP_STORIES:

                holder = TopStoriesViewHolder.newInstance(parent);
                break;
            case TYPE_DATE:

                holder = DateViewHolder.newInstance(parent);

                break;
            case TYPE_STORY:

                holder = StoryViewHolder.newInstance(parent);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_TOP_STORIES:
                if (!mItemBeans.isEmpty()) {
                    holder.bindData(position, type, mItemBeans.get(position).topStoriesBeans);
                }
                break;
            case TYPE_DATE:
                holder.bindData(position, type, mItemBeans.get(position).date);
                break;
            case TYPE_STORY:
                holder.bindData(position, type, mItemBeans.get(position).story);
                break;
        }


    }




    @Override
    public int getItemCount() {
        return mItemBeans.isEmpty() ? 1 : mItemBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!mItemBeans.isEmpty()) {
            return mItemBeans.get(position).type;
        }
        return super.getItemViewType(position);
    }


    public String getDateText(int position) {

        int type = getItemViewType(position);

        if (type != TYPE_STORY && type != TYPE_DATE) {

            return null;
        }

        ItemBean itemBean = mItemBeans.get(position);
        return DateViewHolder.formatDate(itemBean.date);

    }

    private class ItemBean {
        int type;
        String date;
        StoryBean story;
        List<LatestStoriesEntity.TopStoriesBean> topStoriesBeans;
    }

    public void clear() {
        if (mItemBeans.isEmpty()) {
            return;
        }

        mItemBeans.clear();

    }
}



