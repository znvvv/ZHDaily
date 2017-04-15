package com.znv.zhdaily.model.repository;

import com.znv.zhdaily.engine.network.NetworkEngine;
import com.znv.zhdaily.model.entity.LatestStoriesEntity;
import com.znv.zhdaily.model.entity.StoryDetailEntity;
import com.znv.zhdaily.model.entity.StoryExtralEntity;
import com.znv.zhdaily.model.entity.ThemeStoriesEntity;
import com.znv.zhdaily.model.entity.ThemesEntity;
import com.znv.zhdaily.utils.RxUtils;

import io.reactivex.Observable;

/**
 * 整个项目的model层，在这里提供了封装了数据的Observable
 *
 * Created by znv on 2017/3/28.
 */



public class ZHDailyRepository {

    /**
     * 获取主题列表
     * @return
     */
    public Observable<ThemesEntity> getThemes() {
        return NetworkEngine.getInstance().getApiService().getThemes().compose(RxUtils.<ThemesEntity>normalSchedulerTransformer());
    }

    /**
     * 获取最新消息，首页
     * @return
     */
    public Observable<LatestStoriesEntity> getLatestStories() {
        return NetworkEngine.getInstance().getApiService().getLatestStories().compose(RxUtils.<LatestStoriesEntity>normalSchedulerTransformer());
    }

    /**
     * 获取过往消息，首页
     * @return
     */
    public Observable<LatestStoriesEntity> getBeforeStories(String date) {
        return NetworkEngine.getInstance().getApiService().getBeforeStories(date).compose(RxUtils.<LatestStoriesEntity>normalSchedulerTransformer());
    }


    /**
     * 获取主题消息
     * @param themeId 主题id
     * @return
     */
    public Observable<ThemeStoriesEntity> getThemeStories(int themeId) {
        return NetworkEngine.getInstance().getApiService().getThemeStories(themeId).compose(RxUtils.<ThemeStoriesEntity>normalSchedulerTransformer());
    }


    /**
     * 获取消息具体内容
     * @param storyId 消息id
     * @return
     */
    public Observable<StoryDetailEntity> getStoryDetail(int storyId) {
        return NetworkEngine.getInstance().getApiService().getStoryDetail(storyId).compose(RxUtils.<StoryDetailEntity>normalSchedulerTransformer());
    }

    /**
     * 获取对应新闻的额外信息,评论数量,赞数
     * @param storyId 消息id
     * @return
     */
    public Observable<StoryExtralEntity> getStoryExtra(int storyId) {
        return NetworkEngine.getInstance().getApiService().getStoryExtra(storyId).compose(RxUtils.<StoryExtralEntity>normalSchedulerTransformer());
    }





}
