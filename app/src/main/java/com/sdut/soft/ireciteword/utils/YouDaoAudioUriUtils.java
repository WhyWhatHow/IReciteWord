package com.sdut.soft.ireciteword.utils;

import android.net.Uri;

public class YouDaoAudioUriUtils {
    public static Uri getUri(String word) {
        return Uri.parse(Const.YOUDAO_PREFIX+word);
    }
}
