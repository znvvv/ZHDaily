package com.znv.zhdaily.engine.network;

import com.znv.zhdaily.model.entity.LatestStoriesEntity;
import com.znv.zhdaily.model.entity.StoryDetailEntity;
import com.znv.zhdaily.model.entity.StoryExtralEntity;
import com.znv.zhdaily.model.entity.ThemeStoriesEntity;
import com.znv.zhdaily.model.entity.ThemesEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 用于retrofit网络请求的ApiService
 *
 * Created by znv on 2017/3/28.
 */


public interface ApiService {

    @GET("themes")
    Observable<ThemesEntity> getThemes();

    @GET("news/latest")
    Observable<LatestStoriesEntity> getLatestStories();

    @GET("news/before/{date}")
    Observable<LatestStoriesEntity> getBeforeStories(@Path("date") String date);


    @GET("theme/{themeId}")
    Observable<ThemeStoriesEntity> getThemeStories(@Path("themeId") int themeId);



    @GET("news/{storyId}")
    Observable<StoryDetailEntity> getStoryDetail(@Path("storyId") int storyId);

    @GET("story-extra/{storyId}")
    Observable<StoryExtralEntity> getStoryExtra(@Path("storyId") int storyId);


}
