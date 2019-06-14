package com.sdut.soft.ireciteword.translate;

import android.os.Handler;
import android.os.Message;

import com.sdut.soft.ireciteword.api.BaseApi;
import com.sdut.soft.ireciteword.bean.TranslateResult;
import com.sdut.soft.ireciteword.bean.TranslateResultBody;
import com.sdut.soft.ireciteword.utils.Const;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YouDaoTranslateService {
    public static void translateString(String sequence, final Handler handler) {
        TranslateService translateService = BaseApi.retrofit("http://fanyi.youdao.com").create(TranslateService.class);
        Call<TranslateResultBody> call = translateService.translateString(sequence);
        call.enqueue(new Callback<TranslateResultBody>() {
            @Override
            public void onResponse(Call<TranslateResultBody> call, Response<TranslateResultBody> response) {
                Message message  = new Message();
                message.what = Const.TRA_SUCCESS;
                TranslateResult translateResult = response.body().getTranslateResult().get(0).get(0);
                message.obj = translateResult.getTgt();
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<TranslateResultBody> call, Throwable t) {
                Message message  = new Message();
                message.what = Const.TRA_FAIL;
                message.obj = t.getMessage();
                handler.sendMessage(message);

            }
        });
    }
}
