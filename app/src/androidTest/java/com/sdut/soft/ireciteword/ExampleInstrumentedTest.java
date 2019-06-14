package com.sdut.soft.ireciteword;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

//        assertEquals("com.sdut.soft.ireciteword", appContext.getPackageName());
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource(appContext,Uri.parse("http://dict.youdao.com/dictvoice?type=0&audio=hello"));
            mp.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
