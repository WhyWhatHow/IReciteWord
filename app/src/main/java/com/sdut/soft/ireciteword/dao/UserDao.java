package com.sdut.soft.ireciteword.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sdut.soft.ireciteword.bean.User;
import com.sdut.soft.ireciteword.db.DBOpenHelper;

public class UserDao {
    private  DBOpenHelper mDBOpenHelper;

    public UserDao(Context context) {
        mDBOpenHelper = DBOpenHelper.getInstance(context);
    }

    public User checkUser(User u) {
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        Cursor cursor = db.rawQuery("select id ,name,password from user where name = ? and password = ?", new String[]{u.getName(), u.getPassword()});
        if(cursor.moveToNext()){
            u.setId(cursor.getInt(cursor.getColumnIndex("id")));
        } else {
            u = null;
        }
        cursor.close();
        db.close();
        return u;
    }
    public int insert(User u) {
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        ContentValues values = new ContentValues();
        values.put("name",u.getName());
        values.put("password",u.getPassword());
        values.put("rcindex",u.getRcindex());
        values.put("rvindex",u.getRvindex());
        values.put("perday",u.getPassword());
        long cnt = db.insert("user", "id", values);
        db.close();
        return (int) cnt;
    }

    public void update(User u) {
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        ContentValues values = new ContentValues();
        values.put("name",u.getName());
        values.put("password",u.getPassword());
        values.put("rcindex",u.getRcindex());
        values.put("rvindex",u.getRvindex());
        values.put("perday",u.getPassword());
        db.update("user", values, " id = ?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    public User getUserById(Integer uid) {
        User u = null;
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        Cursor cursor = db.query("user",null,"id = ?",new String[]{String.valueOf(uid)},null,null,null);
        if(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            long rcindex = cursor.getLong(cursor.getColumnIndex("rcindex"));
            long rvindex = cursor.getLong(cursor.getColumnIndex("rvindex"));
            int perday = cursor.getInt(cursor.getColumnIndex("perday"));
            u = new User(id, name, password, rcindex, rvindex, perday);
        }
        cursor.close();
        db.close();
        return u;
    }
}
