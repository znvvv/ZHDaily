package com.znv.zhdaily.utils;

import java.util.List;

/**
 * html的工具类，拼接css和js形成网页效果
 * 学习了https://github.com/hefuyicoder/ZhihuDaily
 *
 * Created by znv on 2017/4/6.
 */

public class HtmlUtils {
    public static final String CSS_LINK_PATTERN = "<link href=\"%s\" rel=\"stylesheet\" type=\"text/css\"/>";
    public static final String JS_LINK_PATTERN = "<script src=\"%s\"></script>";

    public static final String MIME_TYPE = "text/html; charset=utf-8";
    public static final String ENCODING = "utf-8";

    private static final String DIV_IMAGE_PLACE_HOLDER = "class=\"img-place-holder\"";
    private static final String DIV_IMAGE_PLACE_HOLDER_IGNORED = "class=\"img-place-holder-ignored\"";

    private static final String NIGHT_DIV_TAG_START = "<div class=\"night\">";

    private static final String NIGHT_DIV_TAG_END = "</div>";


    public static String addHtmlStyle(String html, List<String> cssList, List<String> jsList) {
        StringBuilder builder = new StringBuilder();


        for (String css : cssList) {
            builder.append(String.format(CSS_LINK_PATTERN, css));
        }

        for (String js : jsList) {
            builder.append(String.format(JS_LINK_PATTERN, js));
        }


        if (html != null) {
            builder.append(html.replace(DIV_IMAGE_PLACE_HOLDER, DIV_IMAGE_PLACE_HOLDER_IGNORED));
        }
        return builder.toString();
    }


    public static String addHtmlStyleNightMode(String html, List<String> cssList, List<String> jsList, boolean isNightMode) {
        if (html == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();


        for (String css : cssList) {
            builder.append(String.format(CSS_LINK_PATTERN, css));
        }

        for (String js : jsList) {
            builder.append(String.format(JS_LINK_PATTERN, js));
        }

        if (isNightMode) {
            builder.append(NIGHT_DIV_TAG_START);
        }

        builder.append(html.replace(DIV_IMAGE_PLACE_HOLDER, DIV_IMAGE_PLACE_HOLDER_IGNORED));

        if (isNightMode) {
            builder.append(NIGHT_DIV_TAG_END);
        }


        return builder.toString();
    }

}
