package com.znv.zhdaily.moudle.splash;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseActivity;
import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.moudle.main.MainActivity;
import com.znv.zhdaily.utils.ContextUtils;
import com.znv.zhdaily.utils.SpUtils;

import java.util.Date;

import butterknife.BindView;

/**
 * Splash界面的activitu，非常简单动画效果加跳转activity，没实现知乎的酷炫效果
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_rl_content)
    RelativeLayout mRlContent;


    @Override
    protected void beforeSetContentView() {
        if (!isStartSplash()) {
            statrtMainActivity();
            return;
        }

        ContextUtils.fullScreen(this);
    }

    private boolean isStartSplash() {
        long lastSplashTime = SpUtils.getLong(this, Constants.LAST_SPLASH_TIME, -1);
        if (lastSplashTime != -1 && new Date().getTime() - lastSplashTime < 1000 * 60 * 60 * 2) {
            return false;
        }
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        startAnimation();
    }


    private void startAnimation() {
        SpUtils.putLong(this, Constants.LAST_SPLASH_TIME, new Date().getTime());

        Animation splashAnim = AnimationUtils.loadAnimation(this, R.anim.splash);
        splashAnim.setFillAfter(true);

        splashAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                statrtMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mRlContent.startAnimation(splashAnim);
    }

    private void statrtMainActivity() {
        ContextUtils.startActivity(SplashActivity.this, MainActivity.class);
        finish();
        overridePendingTransition(0, 0);
    }
}
