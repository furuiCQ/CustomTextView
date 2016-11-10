package com.frain.myapplication.MySqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/11/8.
 */
public class MySQliteOpenHelper extends SQLiteOpenHelper{

    public MySQliteOpenHelper(Context context,int version){
            super(context, "Android31.db", null, version);
    }


    public MySQliteOpenHelper(Context context,//上下文
                              String name,//数据库的名字
                              SQLiteDatabase.CursorFactory factory,//游标对象
                              int version) {//版本号
        super(context, name, factory, version);
    }

    public MySQliteOpenHelper(Context context,//上下文
                              String name,//数据库的名字
                              SQLiteDatabase.CursorFactory factory,//游标对象
                              int version,//版本号
                              DatabaseErrorHandler errorHandler) {//异常handler
        super(context, name, factory, version, errorHandler);
    }
    String CREATE_TABLE_SQL="create table Student (id integer primary key AUTOINCREMENT,name varchar(200),chengji varchar(200))";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//数据库的对象
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);//执行创建表结构的语句
    }
    //SQLiteDatabase 数据库操作类
    //execSQL 直接执行sql语句
    //sqLiteDatabase.execSQL("select table Student where name='王老五'")
    //sqLiteDatabase.execSQL(String str,Object[] objs);//直接执行sql语句，并把里面的？替换成后面的对象
            //                 {"select table Student where name=? and chengji=?",new String[]{"王老五","50"}

    //insert 插入方法 【android封装好的插入数据的方法】
    //update 更细方法 【更新数据的方法，封装好的】
    //query() 查询方法
    //rawQuery 未加工的查询方法
    //delete 删除方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,//数据库的对象
                          int oldVerison, //旧版本号
                          int newVersion) {//新版本

    }
}
