package com.znv.zhdaily.moudle.story;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.znv.zhdaily.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义actionbar的ActionProvider，该类用于显示详情页面的action
 *
 * Created by znv on 2017/4/9.
 */


public class CustomActionProvider extends ActionProvider {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_text)
    TextView mTvText;

    View mRootView;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public CustomActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_action_provider, null, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    public void setIcon(int resId){
        mIvIcon.setImageResource(resId);
    }

    public void setText(CharSequence text){
        mTvText.setText(text);
    }

    public void setClickListener(View.OnClickListener listener){
        mRootView.setOnClickListener(listener);
    }


}
