package com.znv.zhdaily.engine.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.znv.zhdaily.widget.CircleImageTransformation;


/**
 * 加载图片的类，封装了一些加载图片到imageview的常用方法
 *
 * Created by znv on 2017/4/4.
 */

public class ImageEngine {

    public static void displayImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }




    public static void displayImage(Context context, ImageView imageView, String url, int placeResId) {
        Glide.with(context).load(url)
                .placeholder(placeResId)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }


    public static void displayCircleImage(Context context, ImageView imageView, String url, int placeResId) {
        Glide.with(context).load(url)
                .placeholder(placeResId)
                .crossFade()
                .centerCrop()
                .transform(new CircleImageTransformation(context))
                .into(imageView);
    }

    public static void displayImage(Context context, ImageView imageView, String url, int placeResId, int errorResId) {
        Glide.with(context).load(url)
                .placeholder(placeResId)
                .error(errorResId)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

}
