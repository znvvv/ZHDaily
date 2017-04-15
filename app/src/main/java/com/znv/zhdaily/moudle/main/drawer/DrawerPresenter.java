package com.znv.zhdaily.moudle.main.drawer;

import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.model.entity.ThemesEntity;
import com.znv.zhdaily.model.repository.ZHDailyRepository;
import com.znv.zhdaily.utils.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 侧边栏的Presenter类
 *
 * Created by znv on 2017/3/29.
 */



public class DrawerPresenter extends DrawerContract.Presenter<ZHDailyRepository> {


    @Override
    public void subscribe() {
        loadThemes();
    }

    @Override
    void loadThemes() {

        Disposable disposable = mRepository.getThemes()
                .filter(new Predicate<ThemesEntity>() {
            @Override
            public boolean test(@NonNull ThemesEntity themeEntity) throws Exception {
                return themeEntity != null && themeEntity.getOthers().size() > 0 && mView != null;
            }
        }).subscribe(new Consumer<ThemesEntity>() {
            @Override
            public void accept(@NonNull ThemesEntity themeEntity) throws Exception {
                mView.showThemes(themeEntity.getOthers());
            }
        });

        addDisposable(disposable);


    }

    @Override
    void registBusForChangeTheme() {

        Disposable disposable = RxBus.getDefault().toObservable(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return Constants.EVENT_CHANGE_THEME.equals(s) && mView != null;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {

                        mView.changeTheme();


                    }
                });
        addDisposable(disposable);
    }


}
