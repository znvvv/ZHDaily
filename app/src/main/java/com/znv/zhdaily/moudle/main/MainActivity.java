package com.znv.zhdaily.moudle.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseActivity;
import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.model.entity.ThemesEntity;
import com.znv.zhdaily.moudle.main.drawer.OnDrawerItemSelectListener;
import com.znv.zhdaily.moudle.main.stories.home.HomeFragment;
import com.znv.zhdaily.moudle.main.stories.theme.ThemeFragment;
import com.znv.zhdaily.utils.RxBus;
import com.znv.zhdaily.utils.SpUtils;
import com.znv.zhdaily.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.znv.zhdaily.R.layout.activity_main;

public class MainActivity extends BaseActivity implements OnDrawerItemSelectListener {

    @BindView(R.id.main_container)
    FrameLayout mMainContainer;


    @BindView(R.id.main_drawer_layout)
    DrawerLayout mMainDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private static final String TAG_HOME_FRAGMENT = "home";
    private static final String TAG_THEME_FRAGMENT = "theme";


    private Unbinder mBinder;
    private ActionBar mActionBar;
    private FragmentManager mFragmentManager;
    private Menu mMenu;


    private String mCurFragmentTag = TAG_HOME_FRAGMENT;

    private Resources mResources;


    @Override
    protected int getContentViewId() {
        return activity_main;
    }

    @Override
    protected void init() {

        mResources = getResources();

        initToolbar();
        startLoad();
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {

        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mMainDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mMainDrawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getResources().getString(R.string.home_text));

    }

    /**
     * 第一次进入是调用，加载HomeFragment
     */
    private void startLoad() {
        mFragmentManager = getSupportFragmentManager();
        loadHomeFragment();
    }


    /**
     * 加载HomeFragment或刷新HomeFragment数据
     */
    private void loadHomeFragment() {


        Fragment themeFragment = mFragmentManager.findFragmentByTag(TAG_THEME_FRAGMENT);
        if (themeFragment != null && !themeFragment.isDetached()) {
            mFragmentManager.beginTransaction().detach(themeFragment).commit();

        }


        Fragment homeFragment = mFragmentManager.findFragmentByTag(TAG_HOME_FRAGMENT);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance(null);
            mFragmentManager.beginTransaction().add(R.id.main_container, homeFragment, TAG_HOME_FRAGMENT)
                    .show(homeFragment)
                    .commit();
            mCurFragmentTag = TAG_HOME_FRAGMENT;


            return;
        }


        if (!TAG_HOME_FRAGMENT.equals(mCurFragmentTag)) {

            mFragmentManager.beginTransaction().attach(homeFragment).commit();


        }

        ((HomeFragment) homeFragment).refreshUI();
        mCurFragmentTag = TAG_HOME_FRAGMENT;


    }

    /**
     * 根据themeId加载或刷新ThemeFragment
     *
     * @param themeId
     */

    private void loadThemeFragment(int themeId) {

        Fragment homeFragment = mFragmentManager.findFragmentByTag(TAG_HOME_FRAGMENT);
        if (homeFragment != null && !homeFragment.isDetached()) {
            mFragmentManager.beginTransaction().detach(homeFragment).commit();

        }


        Fragment themeFragment = mFragmentManager.findFragmentByTag(TAG_THEME_FRAGMENT);
        if (themeFragment == null) {
            themeFragment = ThemeFragment.newInstance(null);
            ((ThemeFragment) themeFragment).setThemeId(themeId);
            mFragmentManager.beginTransaction().add(R.id.main_container, themeFragment, TAG_THEME_FRAGMENT)
                    .show(themeFragment)
                    .commit();
            mCurFragmentTag = TAG_THEME_FRAGMENT;

            return;
        }


        if (!TAG_THEME_FRAGMENT.equals(mCurFragmentTag)) {

            mFragmentManager.beginTransaction().attach(themeFragment).commit();


        }


        ((ThemeFragment) themeFragment).refreshUI(themeId);
        mCurFragmentTag = TAG_THEME_FRAGMENT;


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isNightTheme = SpUtils.getBoolean(this, Constants.THEME_NIGHT, false);
        MenuItem modeItem = menu.findItem(R.id.action_mode);

        if (isNightTheme) {
            modeItem.setTitle(mResources.getString(R.string.light_mode_text));
        } else {
            modeItem.setTitle(mResources.getString(R.string.night_mode_text));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mode:
                //日夜间模式切换
                changeTheme();
                break;
        }
        return true;
    }

    /**
     * 日夜间模式切换 rxbus发送event
     * 夜间模式实现方法来自于http://www.jianshu.com/p/3b55e84742e5
     */

    private void changeTheme() {
        removeThemeFragment();
        showChangeThemeAnimation();

        boolean isNightTheme = refreshThemeSetting();
        SpUtils.putBoolean(this, Constants.THEME_NIGHT, !isNightTheme);


        RxBus.getDefault().post(Constants.EVENT_CHANGE_THEME);

        RefreshBarFromTheme();


    }

    /**
     * 将当前的日夜间模式同步到sp中
     *
     * @return
     */
    private boolean refreshThemeSetting() {



        boolean isNightTheme = SpUtils.getBoolean(this, Constants.THEME_NIGHT, false);

        if (isNightTheme) {
            setTheme(R.style.AppTheme_DayTheme);

        } else {
            setTheme(R.style.AppTheme_NightTheme);

        }
        return isNightTheme;
    }

    /**
     * 将ThemeFragment从FragmentManager删除，下次重新加载
     */
    private void removeThemeFragment() {
        Fragment fragment = mFragmentManager.findFragmentByTag(TAG_THEME_FRAGMENT);
        if (fragment != null) {
            mFragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    //夜间模式实现方法来自于http://www.jianshu.com/p/3b55e84742e5
    private void showChangeThemeAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }


    /**
     * 更新不同模式（日夜间）的toolbar和status模式
     */
    private void RefreshBarFromTheme() {
        ThemeUtils.setActionBarColor(this, mToolbar, R.attr.colorPrimary);
        ThemeUtils.setStatusBarColor(this, R.attr.colorPrimaryDark);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
        }
    }

    private void closeDrawer() {
        if (mMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mMainDrawerLayout.closeDrawer(GravityCompat.START);
        }

    }


    @Override
    public void onDrawerHomeItemSelect() {
        closeDrawer();
        loadHomeFragment();
        resetActionBar(mResources.getString(R.string.home_text), true);
    }


    @Override
    public void onDrawerNormalItemSelect(ThemesEntity.OthersBean bean) {
        closeDrawer();
        loadThemeFragment(bean.getId());
        resetActionBar(bean.getName(), false);
    }

    /**
     * homefragmen 和themefragmentt切换时action的显示或隐藏
     *
     * @param title
     * @param isHome
     */
    private void resetActionBar(String title, boolean isHome) {

        mActionBar.setTitle(title);
        MenuItem itemMsg = mMenu.findItem(R.id.action_message);
        MenuItem itemMode = mMenu.findItem(R.id.action_mode);
        MenuItem itemSetting = mMenu.findItem(R.id.action_setting);
        MenuItem itemAdd = mMenu.findItem(R.id.action_theme_add);
        if (isHome) {

            itemMsg.setVisible(true);
            itemMode.setVisible(true);
            itemSetting.setVisible(true);
            itemAdd.setVisible(false);

        } else {
            itemMsg.setVisible(false);
            itemMode.setVisible(false);
            itemSetting.setVisible(false);
            itemAdd.setVisible(true);
        }
    }


}
