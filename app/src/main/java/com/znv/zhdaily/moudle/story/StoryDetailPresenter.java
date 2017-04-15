package com.znv.zhdaily.moudle.story;

import com.znv.zhdaily.model.entity.StoryDetailEntity;
import com.znv.zhdaily.model.entity.StoryExtralEntity;
import com.znv.zhdaily.model.repository.ZHDailyRepository;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * StoryDetailFragment的Presenter
 *
 * Created by znv on 2017/4/6.
 */

public class StoryDetailPresenter extends StoryDetailContract.Presenter<ZHDailyRepository> {

    @Override
    public void subscribe() {

    }


    @Override
    void loadStoryDetail(int storyId) {
        Disposable disposable = mRepository.getStoryDetail(storyId)

                .filter(new Predicate<StoryDetailEntity>() {
            @Override
            public boolean test(@NonNull StoryDetailEntity entity) throws Exception {
                return mView != null && entity != null;
            }
        }).subscribe(new Consumer<StoryDetailEntity>() {
            @Override
            public void accept(@NonNull StoryDetailEntity entity) throws Exception {
                mView.showStoryDetail(entity);
            }
        });
        addDisposable(disposable);


        Disposable disposable2 = mRepository.getStoryExtra(storyId)

                .filter(new Predicate<StoryExtralEntity>() {
            @Override
            public boolean test(@NonNull StoryExtralEntity storyExtralEntity) throws Exception {
                return mView != null && storyExtralEntity != null;
            }
        }).subscribe(new Consumer<StoryExtralEntity>() {
            @Override
            public void accept(@NonNull StoryExtralEntity storyExtralEntity) throws Exception {
                mView.showStoryExtra(storyExtralEntity);
            }
        });

        addDisposable(disposable2);
    }
}
