package com.frain.myapplication.SixTestDemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin on 2016/11/11.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper{
    String CRATE_SQL="create table diary(_id integer primary key autoincrement,topic varchar(100)," +
            "content varchar(1000))";
    String DROP_SQL="drop table diary";
    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * 当没有表的时候都会重新创建，但是只是在打开openHelper对象的时候去执行
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CRATE_SQL);
    }

    /**
     * 当新版本大于旧版本的时候调用
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            sqLiteDatabase.execSQL(DROP_SQL);
            Log.i("onUpgrade","onUpgrade");
            onCreate(sqLiteDatabase);
        }
    }
}
