package com.znv.zhdaily.moudle.main.stories.home;

import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.model.entity.LatestStoriesEntity;
import com.znv.zhdaily.model.repository.ZHDailyRepository;
import com.znv.zhdaily.utils.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by znv on 2017/4/3.
 */

/**
 * homefragment的prenster
 */
public class HomePresenter extends HomeContract.Presenter<ZHDailyRepository> {


    @Override
    public void subscribe() {

        loadLatestStories();
    }



    @Override
    void loadLatestStories() {


        Disposable disposable = mRepository.getLatestStories()
                .filter(new Predicate<LatestStoriesEntity>() {
                    @Override
                    public boolean test(@NonNull LatestStoriesEntity entity) throws Exception {
                        return mView != null;
                    }
                }).doAfterNext(new Consumer<LatestStoriesEntity>() {
                    @Override
                    public void accept(@NonNull LatestStoriesEntity entity) throws Exception {
                        mView.setRefreshing(false);
                    }
                }).subscribe(new Consumer<LatestStoriesEntity>() {
                    @Override
                    public void accept(@NonNull LatestStoriesEntity entity) throws Exception {
                        mView.showLatestStories(entity);
                    }
                });

        addDisposable(disposable);
    }

    @Override
    void loadBeforeStories(String date) {
        mRepository.getBeforeStories(date)

                .subscribe(new Consumer<LatestStoriesEntity>() {
                    @Override
                    public void accept(@NonNull LatestStoriesEntity entity) throws Exception {
                        mView.showBeforeStories(entity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    void registBusForChangeTheme() {
        Disposable disposable = RxBus.getDefault().toObservable(String.class).observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return Constants.EVENT_CHANGE_THEME.equals(s) && mView != null;
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mView.changeTheme();
                    }
                });
        addDisposable(disposable);

    }

    @Override
     void registBusForCloesRefresh() {

        Disposable disposable = RxBus.getDefault().toObservable(String.class).observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return Constants.EVENT_CLOSE_REFRESH.equals(s) && mView != null;
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mView.setRefreshing(false);
                    }
                });
        addDisposable(disposable);
    }




}
