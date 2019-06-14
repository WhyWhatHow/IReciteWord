package com.sdut.soft.ireciteword.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {
    private static Retrofit mRetrofit;
    public static Retrofit retrofit(String paramString)
    {
        mRetrofit = new Retrofit.Builder().baseUrl(paramString)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return ((Retrofit)mRetrofit);
    }
}