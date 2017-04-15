package com.znv.zhdaily.moudle.main.stories.theme.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.engine.image.ImageEngine;
import com.znv.zhdaily.utils.ContextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主题fragment的顶部的viewholder 图片的移动学习了https://github.com/flavioarfaria/KenBurnsView
 *
 * Created by znv on 2017/4/14.
 */



public class ThemeHeaderViewHolder extends BaseViewHolder<ThemeHeaderViewHolder.ThemeHeaderBean> {

    @BindView(R.id.theme_image)
    ImageView mThemeImage;
    @BindView(R.id.theme_intro)
    TextView mThemeIntro;

    private String currImgUrl = "";

    public ThemeHeaderViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public static ThemeHeaderViewHolder newInstance(ViewGroup parent) {
        View view = ContextUtils.inflate(R.layout.item_theme_header, parent);

        return new ThemeHeaderViewHolder(view);
    }


    @Override
    public void bindData(int position, int viewType, ThemeHeaderViewHolder.ThemeHeaderBean data) {


        if (!TextUtils.isEmpty(data.imgUrl) && currImgUrl.equals(data.imgUrl)) {
            mThemeIntro.setText(data.title);
            return;
        }

        mThemeIntro.setText(data.title);
        ImageEngine.displayImage(itemView.getContext(), mThemeImage, data.imgUrl);

        if (!TextUtils.isEmpty(data.imgUrl)) {
            currImgUrl = data.imgUrl;
        } else {
            currImgUrl = "";
        }


    }


    public  static  class ThemeHeaderBean {
        String imgUrl;
        String title;

        public ThemeHeaderBean() {
        }

        public ThemeHeaderBean(String imgUrl, String title) {
            this.imgUrl = imgUrl;
            this.title = title;
        }
    }


}