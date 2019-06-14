package com.sdut.soft.ireciteword.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsUtils {
    public static String getMeta(Context context) {
        SharedPreferences settings = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        String  property = settings.getString("meta", Const.DEFAULT_META);
        return property;
    }
    public static void setMeta(Context context,String value) {
        SharedPreferences settings = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("meta", Const.DEFAULT_META);
        editor.commit();
    }

}
