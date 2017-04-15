package com.znv.zhdaily.moudle.main.stories.home.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.znv.zhdaily.R;
import com.znv.zhdaily.app.BaseViewHolder;
import com.znv.zhdaily.utils.ContextUtils;
import com.znv.zhdaily.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页fragemnet的显示时间的iewHolder
 *
 * Created by znv on 2017/4/14.
 */



public class DateViewHolder extends BaseViewHolder<String> {

    @BindView(R.id.home_news_date)
    public TextView tvHomeNewsDate;

    public DateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static DateViewHolder newInstance(ViewGroup parent) {
        View view = ContextUtils.inflate(R.layout.item_home_news_date, parent);

        return new DateViewHolder(view);
    }


    @Override
    public void bindData(int position, int viewType,
                         String dateStr) {

        tvHomeNewsDate.setText(formatDate(dateStr));
    }





    public static String formatDate(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        //
        if (DateUtils.isToday(dateStr, "yyyyMMdd")) {
            return "今日新闻";
        }

        Date date = DateUtils.str2date(dateStr, "yyyyMMdd");

        return DateUtils.date2str(date, DateUtils.ZH_DATE_PATTERN);
    }


}



