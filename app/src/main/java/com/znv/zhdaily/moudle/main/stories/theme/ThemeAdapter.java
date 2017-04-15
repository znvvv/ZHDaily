package com.znv.zhdaily.moudle.main.stories.theme;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.model.entity.StoryBean;
import com.znv.zhdaily.model.entity.ThemeStoriesEntity;
import com.znv.zhdaily.moudle.main.stories.StoryViewHolder;
import com.znv.zhdaily.moudle.main.stories.theme.holder.ThemeEditorsViewHolder;
import com.znv.zhdaily.moudle.main.stories.theme.holder.ThemeHeaderViewHolder;

import java.util.List;

/**
 * themeFragment的viewholder
 *
 * Created by znv on 2017/4/5.
 */


public class ThemeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ThemeStoriesEntity mThemeStoriesEntity;

    private static final int TYPE_ITEM_HEADER = 0;
    private static final int TYPE_ITEM_EDITORS = 1;
    private static final int TYPE_ITEM_STORY = 2;


    public ThemeAdapter() {
    }

    public ThemeAdapter(ThemeStoriesEntity themeStoriesEntity) {
        mThemeStoriesEntity = themeStoriesEntity;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;

        switch (viewType) {
            case TYPE_ITEM_HEADER:
                holder = ThemeHeaderViewHolder.newInstance(parent);

                break;
            case TYPE_ITEM_EDITORS:

                holder = ThemeEditorsViewHolder.newInstance(parent);

                break;
            case TYPE_ITEM_STORY:

                holder = StoryViewHolder.newInstance(parent);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);


        switch (viewType) {
            case TYPE_ITEM_HEADER:
                ThemeHeaderViewHolder.ThemeHeaderBean themeHeaderBean = null;

                if (mThemeStoriesEntity != null) {
                    themeHeaderBean = new ThemeHeaderViewHolder.ThemeHeaderBean(mThemeStoriesEntity.getBackground(), mThemeStoriesEntity.getDescription());
                    holder.bindData(position, viewType, themeHeaderBean);

                } else {
                    themeHeaderBean = new ThemeHeaderViewHolder.ThemeHeaderBean();
                }
                holder.bindData(position, viewType, themeHeaderBean);

                break;
            case TYPE_ITEM_EDITORS:
                List<ThemeStoriesEntity.EditorsBean> editors = null;

                if (mThemeStoriesEntity != null) {
                    editors = mThemeStoriesEntity.getEditors();
                }
                holder.bindData(position, viewType, editors);
                break;
            case TYPE_ITEM_STORY:
                StoryBean bean = mThemeStoriesEntity.getStories().get(position - 2);
                holder.bindData(position, viewType, bean);
                break;

        }
    }


    @Override
    public int getItemCount() {
        return mThemeStoriesEntity == null ? 2 : mThemeStoriesEntity.getStories().size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 2 ? position : TYPE_ITEM_STORY;
    }


    public ThemeStoriesEntity getThemeStoriesEntity() {
        return mThemeStoriesEntity;
    }

    public void setThemeStoriesEntity(ThemeStoriesEntity themeStoriesEntity) {
        mThemeStoriesEntity = themeStoriesEntity;
    }





}
