package com.sdut.soft.ireciteword.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sdut.soft.ireciteword.bean.User;
import com.sdut.soft.ireciteword.bean.Word;
import com.sdut.soft.ireciteword.db.DBOpenHelper;
import com.sdut.soft.ireciteword.utils.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordDao {
    private DBOpenHelper mDBOpenHelper;

    //Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example, Word_Unit;
    public WordDao(Context context) {
        mDBOpenHelper = DBOpenHelper.getInstance(context);
    }

    public int getTotalCnt(String meta) {
        String sql = null;
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        sql = " select count(*) from " + Const.DEFAULT_META;
        Cursor cursor = db.rawQuery(sql, null);
        int count = 0;
        if (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    /**
     * 查找单词
     *
     * @param str
     * @return
     */
    public List<Word> searchWords(String str) {
        List<Word> words = null;
        String sql = null;
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        //是英文
        Cursor cursor = null;
        if (Character.isUpperCase(str.toUpperCase().charAt(0))) {
            sql = " select Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example ,Word_Unit from " + Const.DEFAULT_META + " where Word_Key like ? ";
            cursor = db.rawQuery(sql, new String[]{String.valueOf(str + "%")});
        } else {
            sql = " select Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example ,Word_Unit  from " + Const.DEFAULT_META + " where Word_Trans like ? ";
            cursor = db.rawQuery(sql, new String[]{String.valueOf("%" + str + "%")});
        }
        if (cursor.moveToFirst()) {
            words = new ArrayList<>(cursor.getCount());
            Word word;
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Word_Id"));
                String key = cursor.getString(cursor.getColumnIndex("Word_Key"));
                String phono = cursor.getString(cursor.getColumnIndex("Word_Phono"));
                String trans = cursor.getString(cursor.getColumnIndex("Word_Trans"));
                String exam = cursor.getString(cursor.getColumnIndex("Word_Example"));
                Integer unit = cursor.getInt(cursor.getColumnIndex("Word_Unit"));
                word = new Word(id, key, phono, trans, exam, unit);
                words.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return words;
    }

    /**
     * 背诵新词的时候应该是 [rcindex ,rcindex + perday]
     * offset : rcindex
     * limit : perday
     *
     * @param metaKey
     * @param user
     * @return
     */
    public List<Word> getNewWords(String metaKey, User user) {
        List<Word> words = null;
        String sql = "select Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example ,Word_Unit from " + metaKey + " limit ? OFFSET ?";
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        String[] paras = {String.valueOf(user.getPerday()), String.valueOf(user.getRcindex())};
        Cursor cursor = db.rawQuery(sql, paras);
        if (cursor.moveToFirst()) {
            words = new ArrayList<>(cursor.getCount());
            Word word;
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Word_Id"));
                String key = cursor.getString(cursor.getColumnIndex("Word_Key"));
                String phono = cursor.getString(cursor.getColumnIndex("Word_Phono"));
                String trans = cursor.getString(cursor.getColumnIndex("Word_Trans"));
                String exam = cursor.getString(cursor.getColumnIndex("Word_Example"));
                Integer unit = cursor.getInt(cursor.getColumnIndex("Word_Unit"));
                word = new Word(id, key, phono, trans, exam, unit);
                words.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return words;
    }

    /**
     * 获取干扰项
     * @param metaKey
     * @param main
     * @return
     */
    public List<Word> getOptionsFromUnit(String metaKey, Word main) {
        List<Word> words = null;
        String sql = "select Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example ,Word_Unit from " + metaKey + " where Word_Id != ? and Word_Unit = ? ORDER BY RANDOM() limit 3";
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{
                String.valueOf(main.getId()),
                String.valueOf(main.getUnit())
        });
        if (cursor.moveToFirst()) {
            words = new ArrayList<>(cursor.getCount());
            Word word;
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Word_Id"));
                String key = cursor.getString(cursor.getColumnIndex("Word_Key"));
                String phono = cursor.getString(cursor.getColumnIndex("Word_Phono"));
                String trans = cursor.getString(cursor.getColumnIndex("Word_Trans"));
                String exam = cursor.getString(cursor.getColumnIndex("Word_Example"));
                Integer unit = cursor.getInt(cursor.getColumnIndex("Word_Unit"));
                word = new Word(id, key, phono, trans, exam, unit);
                words.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return words;
    }

    /**
     * 复习词汇时是 [rvindex, rcindex]
     * offset: rvindex
     * limit: rcindex - rvindex > 0
     *
     * @param metaKey
     * @param user
     * @return
     */
    public List<Word> getReviewWords(String metaKey, User user) {
        List<Word> words = null;
        String sql = "select Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example ,Word_Unit from " + metaKey + " limit ? OFFSET ?";
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        long size = user.getRcindex() - user.getRvindex();
        size = size >= 0 ? size : 0;
        size = size <user.getPerday() ? size : user.getPerday();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(size), String.valueOf(user.getRvindex())});
        if (cursor.moveToFirst()) {
            words = new ArrayList<>(cursor.getCount());
            Word word;
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Word_Id"));
                String key = cursor.getString(cursor.getColumnIndex("Word_Key"));
                String phono = cursor.getString(cursor.getColumnIndex("Word_Phono"));
                String trans = cursor.getString(cursor.getColumnIndex("Word_Trans"));
                String exam = cursor.getString(cursor.getColumnIndex("Word_Example"));
                Integer unit = cursor.getInt(cursor.getColumnIndex("Word_Unit"));
                word = new Word(id, key, phono, trans, exam, unit);
                words.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return words;
    }

    /**
     * 根据词汇表和单词id获得单词
     * @param metaKey
     * @param wid
     * @return
     */
    public Word getWordById(String metaKey, int wid) {
        Word word = null;
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        Cursor cursor = db.query(metaKey, null, "Word_Id = ?", new String[]{String.valueOf(wid)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Word_Id"));
                String key = cursor.getString(cursor.getColumnIndex("Word_Key"));
                String phono = cursor.getString(cursor.getColumnIndex("Word_Phono"));
                String trans = cursor.getString(cursor.getColumnIndex("Word_Trans"));
                String exam = cursor.getString(cursor.getColumnIndex("Word_Example"));
                Integer unit = cursor.getInt(cursor.getColumnIndex("Word_Unit"));
                word = new Word(id, key, phono, trans, exam, unit);
            } while (cursor.moveToNext());
        }
        return word;
    }
}
