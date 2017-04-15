package com.znv.zhdaily.moudle.main.stories.theme;

import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.model.entity.ThemeStoriesEntity;
import com.znv.zhdaily.model.repository.ZHDailyRepository;
import com.znv.zhdaily.utils.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * themefragment的presenter
 *
 * Created by znv on 2017/4/3.
 */

public class ThemePresenter extends ThemeContract.Presenter<ZHDailyRepository> {
    @Override
    public void subscribe() {
        registBusForCloesRefresh();
    }


    @Override
    void loadThemeStories(int themeId) {
        mView.clearUI();


        refreshThemeStroies(themeId);
    }

    @Override
    void refreshThemeStroies(int themeId) {


        Disposable disposable = mRepository.getThemeStories(themeId)

                .filter(new Predicate<ThemeStoriesEntity>() {
                    @Override
                    public boolean test(@NonNull ThemeStoriesEntity themeStoriesEntity) throws Exception {
                        return mView != null;
                    }
                })
                .doAfterNext(new Consumer<ThemeStoriesEntity>() {
                    @Override
                    public void accept(@NonNull ThemeStoriesEntity themeStoriesEntity) throws Exception {
                        mView.setRefreshing(false);
                    }
                }).subscribe(new Consumer<ThemeStoriesEntity>() {
                    @Override
                    public void accept(@NonNull ThemeStoriesEntity themeStoriesEntity) throws Exception {
                        mView.showThemeStroies(themeStoriesEntity);
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
