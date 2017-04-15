package com.znv.zhdaily.moudle.story;

import android.os.Bundle;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseActivity;

/**
 * story详情的ativity，主要显示逻辑在fragment中
 *
 * Created by znv on 2017/4/5.
 */

public class StoryDetailActivity extends BaseActivity{



    @Override
    protected int getContentViewId() {
        return R.layout.activity_story_detail;
    }

    @Override
    protected void init() {
        loadFragment();
    }


    private void loadFragment() {

        Bundle bundle = getIntent().getExtras();
        StoryDetailFragment fragment = StoryDetailFragment.newInstance(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.details_container,fragment).commit();
    }
}
