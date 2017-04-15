package com.znv.zhdaily.config;


/**
 * 用来保存一些常用的参数
 *
 * Created by znv on 2017/3/28.
 */


public class Config {

    public static final class Network {
        public static final String BASE_URL = "http://news-at.zhihu.com/api/4/";

        public static final int HTTP_CACHE_SIZE = 20 * 1024 * 1024;
        public static final int HTTP_CONNECT_TIMEOUT = 5 * 1000;
        public static final int HTTP_READ_TIMEOUT = 20 * 1000;

        public static final String HTTP_CACHE_DIR = "network";
    }

}
