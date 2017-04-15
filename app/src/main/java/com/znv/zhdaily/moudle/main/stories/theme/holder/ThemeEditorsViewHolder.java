package com.znv.zhdaily.moudle.main.stories.theme.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.engine.image.ImageEngine;
import com.znv.zhdaily.model.entity.ThemeStoriesEntity;
import com.znv.zhdaily.utils.ContextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主题fragment显示主编的viewholder、
 *
 * Created by znv on 2017/4/14.
 */


public class ThemeEditorsViewHolder extends BaseViewHolder<List<ThemeStoriesEntity.EditorsBean>> {

    @BindView(R.id.theme_editor_layout)
    LinearLayout mThemeEditorLayout;


    public ThemeEditorsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static ThemeEditorsViewHolder newInstance(ViewGroup parent) {
        View view = ContextUtils.inflate(R.layout.item_theme_editors, parent);

        return new ThemeEditorsViewHolder(view);
    }

    @Override
    public void bindData(int position, int viewType, List<ThemeStoriesEntity.EditorsBean> editorsBeans) {
        mThemeEditorLayout.removeAllViews();

        if (editorsBeans == null) {
            return;
        }
        for (ThemeStoriesEntity.EditorsBean editorBean : editorsBeans) {

            View view = ContextUtils.inflate(R.layout.iv_theme_editors, mThemeEditorLayout);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ImageView imageView = (ImageView) view.findViewById(R.id.theme_editor_avatar);

            mThemeEditorLayout.addView(view);

            ImageEngine.displayCircleImage(itemView.getContext(), imageView, editorBean.getAvatar(), R.drawable.editor_profile_avatar);
        }


    }


}
