package com.sdut.soft.ireciteword.translate;

import com.sdut.soft.ireciteword.bean.TranslateResultBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateService {

    @GET("/translate?doctype=json&type=AUTO&")
    Call<TranslateResultBody> translateString(@Query("i") String sequence);
}
