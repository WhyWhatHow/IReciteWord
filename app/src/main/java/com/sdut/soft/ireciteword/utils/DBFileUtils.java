package com.sdut.soft.ireciteword.utils;

import android.content.Context;

import com.sdut.soft.ireciteword.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBFileUtils {
    private DBFileUtils() {
    }

    /**
     * 拷贝数据库文件
     * @param context
     */
    public static void copyDBData(Context context) {
        String dbPath = context.getDir(Const.DB_DIR, Context.MODE_PRIVATE) + File.separator + Const.DB_NAME;
        File dbFile = new File(dbPath);
        //如果存在数据库文件，则不拷贝
        //if (!dbFile.exists())
        {
            InputStream inputStream = context.getResources().openRawResource(R.raw.words);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(dbFile);
                int len;
                byte[] bytes = new byte[1024];
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
