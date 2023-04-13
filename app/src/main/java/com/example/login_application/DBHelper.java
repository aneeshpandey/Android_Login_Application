package com.example.login_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static Context mContext;
    private static final String DB_NAME = "Login.db";
    private static DBHelper mInstance = null;
    private DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    public static DBHelper getInstance(Context ctx) {
        if(mInstance == null) {
            mInstance = new DBHelper(ctx);
        }
        mContext = ctx;
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");
    }

    public static Boolean insertData(String username, String password) {
        SQLiteDatabase myDB = mInstance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = myDB.insert("users",null, contentValues);
        return (result == -1) ? false : true;
    }

    public static Boolean checkUsernameExists(String username) {
        SQLiteDatabase myDB = mInstance.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username =?", new String[]{username});
        return (cursor.getCount()>0) ? true : false;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase myDB = mInstance.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username,password});
        return (cursor.getCount()>0) ? true : false;
    }
}
