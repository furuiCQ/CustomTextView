package com.frain.myapplication.MySqlite.AccountsPractice;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/11/9.
 */
public class AccountsSQliteOpenHelper extends SQLiteOpenHelper{
    String CRATE_ACCOUNTS_TABLE_SQL="CREATE TABLE accounts (id integer primary key autoincrement,total_type varchar(200)," +
            "type_id integer,type_name varchar(200),time date," +
            "address varchar(200),bei_zhu varchar(200), goods_name varchar(200))";
    String CRATE_EXPEND_BIG_TYPE_TABLE_SQL="CREATE TABLE expend_big_type (id integer primary key autoincrement,big_type varchar(200))";
    String CRATE_EXPEND_SAMLL_TYPE_TABLE_SQL="CREATE TABLE expend_small_type (id INTEGER PRIMARY KEY AUTOINCREMENT, big_type_id INTEGER NOT NULL, small_type VARCHAR(200))";
    String CRATE_INCOME_TYPE_TABLE_SQL="CREATE TABLE income_type (id integer primary key autoincrement,name varchar(200))";
    public AccountsSQliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AccountsSQliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CRATE_ACCOUNTS_TABLE_SQL);
        sqLiteDatabase.execSQL(CRATE_EXPEND_BIG_TYPE_TABLE_SQL);
        sqLiteDatabase.execSQL(CRATE_EXPEND_SAMLL_TYPE_TABLE_SQL);
        sqLiteDatabase.execSQL(CRATE_INCOME_TYPE_TABLE_SQL);
        initData(sqLiteDatabase);
    }
    public void initData(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("insert into income_type values(1,'工资')");
        sqLiteDatabase.execSQL("insert into income_type values(2,'资金')");
        sqLiteDatabase.execSQL("insert into income_type values(3,'兼职')");
        sqLiteDatabase.execSQL("insert into income_type values(4,'补贴')");
        sqLiteDatabase.execSQL("insert into income_type values(5,'利息')");

        sqLiteDatabase.execSQL("insert into expend_big_type values(1,'日常用品')");
        sqLiteDatabase.execSQL("insert into expend_big_type values(2,'食品')");
        sqLiteDatabase.execSQL("insert into expend_big_type values(3,'衣服')");
        sqLiteDatabase.execSQL("insert into expend_big_type values(4,'交通')");
        sqLiteDatabase.execSQL("insert into expend_big_type values(5,'饮料')");
        sqLiteDatabase.execSQL("insert into expend_big_type values(6,'其他')");

        sqLiteDatabase.execSQL("insert into expend_small_type values(1,1,'洗衣粉')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(2,1,'牙刷')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(3,1,'毛巾')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(4,1,'背包')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(5,1,'洗发水')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(6,1,'牙膏')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(7,1,'洗面奶')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(8,1,'洗衣液')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(9,1,'水杯')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(10,1,'拖把')");
        sqLiteDatabase.execSQL("insert into expend_small_type values(11,1,'钱包')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
