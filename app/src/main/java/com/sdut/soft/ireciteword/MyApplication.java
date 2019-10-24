package com.sdut.soft.ireciteword;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.initial(this);
    }

}
