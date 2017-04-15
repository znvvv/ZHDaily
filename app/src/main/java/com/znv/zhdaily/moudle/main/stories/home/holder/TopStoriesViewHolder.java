package com.znv.zhdaily.moudle.main.stories.home.holder;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.App;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.engine.image.ImageEngine;
import com.znv.zhdaily.model.entity.LatestStoriesEntity;
import com.znv.zhdaily.moudle.story.StoryDetailActivity;
import com.znv.zhdaily.utils.ContextUtils;
import com.znv.zhdaily.widget.AutoSlideViewPager;
import com.znv.zhdaily.widget.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 首页fragment轮播图的viewholder
 * 利用viewpager加Jake Wharton的开源项目https://github.com/JakeWharton/ViewPagerIndicator
 * Created by znv on 2017/4/4.
 */



public class TopStoriesViewHolder extends BaseViewHolder<List<LatestStoriesEntity.TopStoriesBean>> {
    @BindView(R.id.topstory_pager)
    AutoSlideViewPager mTopstoryPager;

    @BindView(R.id.topstory_pager_indicator)
    CirclePageIndicator mTopstoryPagerIndicator;


    private TopStoriesPagerAdapter mPagerAdapter;

    private boolean isFirst = true;


    public TopStoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mPagerAdapter = new TopStoriesPagerAdapter();
        mTopstoryPager.setAdapter(mPagerAdapter);
        mTopstoryPagerIndicator.setViewPager(mTopstoryPager);
        mTopstoryPagerIndicator.setVisibility(View.INVISIBLE);

    }


    public static TopStoriesViewHolder newInstance(ViewGroup parent) {
        View view = ContextUtils.inflate(R.layout.item_home_topstroies, parent);

        return new TopStoriesViewHolder(view);
    }


    @Override
    public void bindData(int position, int viewType, List<LatestStoriesEntity.TopStoriesBean> storiesBeans) {
        mTopstoryPagerIndicator.setVisibility(View.VISIBLE);

        mPagerAdapter = new TopStoriesPagerAdapter(storiesBeans);
        mTopstoryPager.setAdapter(mPagerAdapter);
        isFirst = false;
        return;


    }


    class TopStoriesPagerAdapter extends PagerAdapter {

        List<LatestStoriesEntity.TopStoriesBean> beans;

        public TopStoriesPagerAdapter() {
        }

        public TopStoriesPagerAdapter(List<LatestStoriesEntity.TopStoriesBean> storiesBeans) {
            beans = storiesBeans;
        }

        @Override
        public int getCount() {
            return beans == null ? 1 : beans.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            View pagerView = ContextUtils.inflate(R.layout.item_home_topstories_pager, container);

            pagerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();

                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.STORY_ID, beans.get(position).getId());
                    ContextUtils.startActivity(context, StoryDetailActivity.class, bundle);

                }
            });




            PagerHolder holder = new PagerHolder(pagerView);

            if (beans != null) {
                holder.bindPagerData(beans.get(position));
            }


            container.addView(pagerView);

            return pagerView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void setStoriesBeans(List<LatestStoriesEntity.TopStoriesBean> storiesBeans) {
            beans = storiesBeans;
        }


    }

    class PagerHolder {

        View itemView;
        @BindView(R.id.topstory_image)
        ImageView storyImage;

        @BindView(R.id.topstroy_mask)
        RelativeLayout stroyMask;

        @BindView(R.id.topstory_title)
        TextView storyTitle;

        public PagerHolder(View pagerView) {
            this.itemView = pagerView;

            ButterKnife.bind(this, itemView);
        }

        private void bindPagerData(LatestStoriesEntity.TopStoriesBean topStoriesBean) {


            storyTitle.setText(topStoriesBean.getTitle());

            ImageEngine.displayImage(App.getContext(), storyImage, topStoriesBean.getImage());
        }
    }


}
