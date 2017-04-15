package com.znv.zhdaily.moudle.story;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseRxFragment;
import com.znv.zhdaily.config.Constants;
import com.znv.zhdaily.engine.image.ImageEngine;
import com.znv.zhdaily.model.entity.StoryDetailEntity;
import com.znv.zhdaily.model.entity.StoryExtralEntity;
import com.znv.zhdaily.utils.HtmlUtils;
import com.znv.zhdaily.utils.SpUtils;

import butterknife.BindView;

/**
 * 显示story详情的fragment
 *
 * Created by znv on 2017/4/6.
 */

public class StoryDetailFragment extends BaseRxFragment<StoryDetailPresenter> implements StoryDetailContract.View, View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.session_photo)
    ImageView mSessionPhoto;
    @BindView(R.id.news_image_mask)
    View mNewsImageMask;
    @BindView(R.id.story_title)
    TextView mStoryTitle;
    @BindView(R.id.story_image_source)
    TextView mStoryImageSource;
    @BindView(R.id.session_photo_container)
    FrameLayout mSessionPhotoContainer;
    @BindView(R.id.story_webview)
    WebView mStoryWebview;

    CustomActionProvider mCommentActionProvider;

    CustomActionProvider mVoteActionProvider;

    public static StoryDetailFragment newInstance(Bundle args) {
        StoryDetailFragment fragment = new StoryDetailFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_story_detail;
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        int storyId = bundle.getInt(Constants.STORY_ID);
        initToolbar();
        mPresenter.loadStoryDetail(storyId);
        initWebView();

    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        mToolBar.setNavigationIcon(R.drawable.back);
        mToolBar.setNavigationOnClickListener(this);

        mToolBar.inflateMenu(R.menu.menu_story);
        Menu menu = mToolBar.getMenu();
        mCommentActionProvider = (CustomActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_comment));
        mVoteActionProvider = (CustomActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_vote));

        mCommentActionProvider.setIcon(R.drawable.comment);
        mVoteActionProvider.setIcon(R.drawable.praise);
    }

    /**
     * 初始化webview的设置
     */
    private void initWebView() {
        WebSettings settings = mStoryWebview.getSettings();


        settings.setBuiltInZoomControls(false);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);


        settings.setJavaScriptEnabled(true);

        settings.setLoadWithOverviewMode(true);

        settings.setSupportZoom(true);


        mStoryWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                startChorme(url);
                return true;
            }

        });
    }

    /**
     * 运用chorme custom tab
     * @param url
     */
    private void startChorme(String url) {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        TypedValue tv = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, tv, true);

        builder.setToolbarColor(ContextCompat.getColor(getActivity(), tv.resourceId));

        builder.build().launchUrl(getActivity(), Uri.parse(url));
    }


    @Override
    public void showStoryDetail(StoryDetailEntity entity) {


        String images = entity.getImage();
        if (images == null) {
            mSessionPhotoContainer.setVisibility(View.GONE);
        } else {
            mSessionPhotoContainer.setVisibility(View.VISIBLE);
            ImageEngine.displayImage(getActivity(), mSessionPhoto, images);
            mStoryTitle.setText(entity.getTitle());
            mStoryImageSource.setText(entity.getImage_source());
        }

        if (TextUtils.isEmpty(entity.getBody()) && !TextUtils.isEmpty(entity.getShare_url())) {
            mStoryWebview.loadUrl(entity.getShare_url());
            return;
        }


        boolean isNightTheme = SpUtils.getBoolean(getActivity(), Constants.THEME_NIGHT, false);

        String html = HtmlUtils.addHtmlStyleNightMode(entity.getBody(), entity.getCss(), entity.getJs(), isNightTheme);


        mStoryWebview.loadData(html, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);


    }

    @Override
    public void showStoryExtra(StoryExtralEntity entity) {
        mCommentActionProvider.setText(String.valueOf(entity.getShort_comments()));
        mVoteActionProvider.setText(String.valueOf(entity.getPopularity()));
    }


    @Override
    public void onClick(View v) {
        getActivity().finish();
    }
}
