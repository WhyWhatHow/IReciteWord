package com.sdut.soft.ireciteword.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Clock on 2016/2/3.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//
    }
    //TODO
    // [drawable-xxxhdpi-v4/ic_launcher_background]
    // /home/initial-mind/AndroidStudioProjects/IReciteWord/app/src/main/res/drawable/ic_launcher_background.xml
    // [drawable-xxxhdpi-v4/ic_launcher_background]
    // /home/initial-mind/AndroidStudioProjects/IReciteWord/app/src/main/res/drawable-xxxhdpi/
    // ic_launcher_background.xml:
    // Error: Duplicate resources
}
