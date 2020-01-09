package com.movtalent.app.http;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Huangyong on 2017/9/13.
 */

public class ApiManager {
    private static ApiService api;
    protected static final Object monitor = new Object();
    private static Retrofit retrofit;
    private static OkHttpClient client;

    private ApiManager() {
    }
    static {
        //设置应用拦截器，可用于设置公共参数，头信息，日志拦截等
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();


        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //添加应用拦截器
        client = new OkHttpClient.Builder()
                //添加应用拦截器
                .connectTimeout(35, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(UrlConfig.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }
    public static ApiService getRetrofitInstance(){

        if (api==null){
            synchronized (monitor){
                api = retrofit.create(ApiService.class);
            }
        }
        return api;
    }
    public static OkHttpClient getClientInstance(){
        return client;
    }

}
