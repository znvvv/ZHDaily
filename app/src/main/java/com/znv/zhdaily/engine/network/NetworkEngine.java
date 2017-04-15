package com.znv.zhdaily.engine.network;


import com.znv.zhdaily.config.Config;
import com.znv.zhdaily.utils.ContextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit网络加载的类，在这里初始化，获取单例对象
 *
 * Created by znv on 2017/3/28.
 */

/**
 *
 *
 */

public class NetworkEngine {
    private static NetworkEngine mEngine;
    private ApiService mApiService;
    private OkHttpClient mOkHttpClient;

    public NetworkEngine() {
        initClient();
        initRetrofit();
    }


    private void initClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(ContextUtils.getHttpCacheDir(), Config.Network.HTTP_CACHE_SIZE))
                .connectTimeout(Config.Network.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Config.Network.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpCacheInterceptor())
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();

    }


    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.Network.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApiService = retrofit.create(ApiService.class);
    }


    public static NetworkEngine getInstance() {

        if (mEngine == null) {
            mEngine = new NetworkEngine();
        }
        return mEngine;
    }

    public ApiService getApiService() {
        return mApiService;
    }


}
