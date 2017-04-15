package com.znv.zhdaily.moudle.story;

import com.znv.zhdaily.app.BaseRxPresenter;
import com.znv.zhdaily.app.BaseView;
import com.znv.zhdaily.model.entity.StoryDetailEntity;
import com.znv.zhdaily.model.entity.StoryExtralEntity;

/**
 * storyDetailFragment（详情界面）的StoryDetailContract
 *
 * Created by znv on 2017/4/6.
 */

public interface StoryDetailContract {
    interface View extends BaseView{
        /**
         * 显示详情内容
         * @param entity 数据
         */
        void showStoryDetail(StoryDetailEntity entity);

        /**
         * 显示story的额外数据,评论数量等
         * @param entity 数据
         */
        void showStoryExtra(StoryExtralEntity entity);
    }

    abstract class Presenter<R> extends BaseRxPresenter<R,View> {
        /**
         * 加载详情数据
         * @param storyId
         */
        abstract void loadStoryDetail(int storyId);
    }



}
