package com.znv.zhdaily.moudle.main.stories;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.engine.image.ImageEngine;
import com.znv.zhdaily.model.entity.StoryBean;
import com.znv.zhdaily.moudle.story.StoryDetailActivity;
import com.znv.zhdaily.utils.ContextUtils;
import com.znv.zhdaily.utils.SpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * themeFragment和homeFragment的story条目的viewholder
 * <p>
 * Created by znv on 2017/4/4.
 */

public class StoryViewHolder extends BaseViewHolder<StoryBean> {
    @BindView(R.id.list_item_image)
    public ImageView mListItemImage;

    @BindView(R.id.list_item_multipic)
    public ImageView mListItemMultipic;
    @BindView(R.id.list_item_image_layout)
    public RelativeLayout mListItemImageLayout;


    @BindView(R.id.list_item_title)
    public TextView mListItemTitle;


    @BindView(R.id.list_item_intro)
    public TextView mListItemIntro;
    @BindView(R.id.card_view)
    public CardView mCardView;

    private Context mContext;

    private String mStoryId;


    public static StoryViewHolder newInstance(ViewGroup parent) {
        View view = ContextUtils.inflate(R.layout.item_story, parent);

        return new StoryViewHolder(view);
    }

    private StoryViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(int position, int viewType, final StoryBean storyBean) {
        if (storyBean.isMultipic()) {
            mListItemMultipic.setVisibility(View.VISIBLE);
        } else {
            mListItemMultipic.setVisibility(View.INVISIBLE);
        }


        TypedValue typeValue = new TypedValue();
        itemView.getContext().getTheme().resolveAttribute(R.attr.image_small_default, typeValue, true);
        List<String> images = storyBean.getImages();

        if (images == null) {
            mListItemImageLayout.setVisibility(View.GONE);
        } else {
            mListItemImageLayout.setVisibility(View.VISIBLE);
            ImageEngine.displayImage(mContext, mListItemImage, images.get(0), typeValue.resourceId);
        }


        mListItemTitle.setText(storyBean.getTitle());


        mStoryId = String.valueOf(storyBean.getId());

        initTitleColor();


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STORY_ID, storyBean.getId());
                ContextUtils.startActivity(mContext, StoryDetailActivity.class, bundle);

                updateStoryState(storyBean.getId());
                changeTitleColor();
            }
        });


    }

    public void initTitleColor() {

        initTitleColor(mStoryId);
    }


    /**
     * 根据sp储存设置不同title样式，这里应该用数据库存储好点，为了省事不管了
     *
     * @param id story的id
     */
    private void initTitleColor(String id) {
        String readStoryIds = SpUtils.getString(mContext, Constants.READED_STORY, "");
        String[] readedIds = readStoryIds.split(";");

        boolean isRead = false;

        for (String i : readedIds) {
            if (i.equals(id)) {
                isRead = true;
                break;
            }
        }

        if (isRead) {
            mListItemTitle.setTextAppearance(mContext, R.style.Daily_TextAppearance_List_Item_Title_Read);
        } else {
            mListItemTitle.setTextAppearance(mContext, R.style.Daily_TextAppearance_List_Item_Title_Unread);
        }


    }

    /**
     * 保存已读id到sp，这里用数据库好
     *
     * @param id
     */
    private void updateStoryState(int id) {
        String readStoryIds = SpUtils.getString(mContext, Constants.READED_STORY, "");

        String[] readedIds = readStoryIds.split(";");
        String currId = String.valueOf(id);

        for (String i : readedIds) {
            if (i.equals(currId)) {
                return;
            }
        }

        StringBuilder ids = new StringBuilder(readStoryIds);

        ids.append(id).append(";");

        SpUtils.putString(mContext, Constants.READED_STORY, ids.toString());

    }

    private void changeTitleColor() {

        mListItemTitle.setTextAppearance(mContext, R.style.Daily_TextAppearance_List_Item_Title_Read);
    }


}
