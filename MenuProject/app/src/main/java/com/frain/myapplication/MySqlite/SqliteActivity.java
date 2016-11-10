package com.frain.myapplication.MySqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/8.
 */
public class SqliteActivity extends Activity {
    Button button;
    Button addButton;
    Button updateButton;
    Button deleteButton;
    Button selectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        button = (Button) findViewById(R.id.button1);
        addButton = (Button) findViewById(R.id.button2);
        updateButton = (Button) findViewById(R.id.button3);
        deleteButton=(Button)findViewById(R.id.button4);
        selectButton=(Button)findViewById(R.id.button5);
        button.setOnClickListener(onClickListener);
        addButton.setOnClickListener(onClickListener);
        updateButton.setOnClickListener(onClickListener);
        deleteButton.setOnClickListener(onClickListener);
        selectButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    createASQliteDataBase();
                    break;
                case R.id.button2:
                    insert();
                    break;
                case R.id.button3:
                    update();
                    break;
                case R.id.button4:
                    delete();
                    break;
                case R.id.button5:
                    select();
                    break;
            }
        }
    };
    SQLiteDatabase sqLiteDatabase = null;

    public void createASQliteDataBase() {
        //本句执行后，会判断data/data/包名/database中是否含有指定名字的数据库，
        // 如果有则返回该数据库的对象
        //如果没有则创建并返回数据库的对象放在帮助类的对象中
        MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(
                this,//上下文
                "Android31.db",//数据库的名字+后缀
                null,//可以为null
                1);//版本号
        //通过open对象get方法拿到写入/读取权限的数据库操作类
         sqLiteDatabase = mySQliteOpenHelper.getWritableDatabase();
        //执行数据库的增查删改的操作
    }
    int i=15;
    public void insert() {
        //语句 INSERT INTO 表名称 VALUES (值1, 值2,....)
        String inserSql = "insert into Student values(12,'王老五','56')";
        if (sqLiteDatabase != null) {
            //sqLiteDatabase.execSQL(inserSql);
                                            //表名   //允许为null的列名    //类似于Map的一个类用于保存数据库列名以及对应的值
           // sqLiteDatabase.insert(String table,String nullCoulm, ContentValues contentValues);
            ContentValues contentValues=new ContentValues();//创建一个ContentValues对象
            //往对象中添加列名以及对应的值
            contentValues.put("id",i++);
            contentValues.put("name","隔壁老王");
            contentValues.put("chengji","99");
            long numb=sqLiteDatabase.insert("Student",null,contentValues);

        }
    }
    public void update(){
        // UPDATE 表名称 SET 列名称 = 新值,列名称=新值 WHERE 列名称 = 某值
     // update  Student Set name='马蓉' , chengji=13 where id=12
     // update  Student Set chengji=15 where id=12 and name='马蓉'
        String inserSql = " update  Student Set name='马蓉' , chengji=13 where id=12";
        if (sqLiteDatabase != null) {
            //sqLiteDatabase.execSQL(inserSql);
                                        //表名            //更新的值的包装类  //条件语句列如:id=? //条件语句中替换通配符的值
            //sqLiteDatabase.update(String table，ContentValues contentValues,String whereClause,String[] caluseArgs);
            ContentValues contentValues=new ContentValues();//创建一个ContentValues对象
            //往对象中添加列名以及对应的值
            contentValues.put("chengji","66");
            String [] caluseArgs={"15","马蓉"};
            int numb=sqLiteDatabase.update("Student",contentValues,"id=? and name=?",caluseArgs);
        }
    }
    public void delete(){
       // DELETE FROM 表名称 WHERE 列名称 = 值
        String delteSql="delete from Student where id=15";
        if(sqLiteDatabase!=null){
           //  sqLiteDatabase.execSQL(delteSql);
                                //表名            //条件语句      //条件语句中替换通配符的值
           // sqLiteDatabase.delete(String table,String whereClause,String[] whereArgs);
            int numb=sqLiteDatabase.delete("Student","id=?",new String[]{"15"});
        }
    }
    public void select() {
        if (sqLiteDatabase == null) {
            createASQliteDataBase();
        }
//        //Cursor 游标=                        //sql语句where id=？      //String[] whereArgs 【where条件的值】
//        Cursor cursor=sqLiteDatabase.rawQuery("select * from Student ",null);//查询方法
//        if(!cursor.isFirst()){//是否在第一行
//            cursor.moveToFirst();//将游标移动到第一行
//            int numb=cursor.getColumnCount();//获取列总数
//            for (int i=0;i<numb;i++){//循环读取每列的数据
//                String columnName=cursor.getColumnName(i);//获得每列的列名
//                String value=cursor.getString(i);//获取每列对应的值
//                Log.i("columnName",columnName);
//                Log.i("value",value);
//            }
//        }
//        while(cursor.moveToNext()){//循环移动游标到下一行，
//            int numb=cursor.getColumnCount();//获取列总数
//            for (int i=0;i<numb;i++){//循环读取每列的数据
//                String columnName=cursor.getColumnName(i);//获得每列的列名
//                String value=cursor.getString(i);//获取每列对应的值
//                Log.i("columnName",columnName);
//                Log.i("value",value);
//
//            }
       // SELECT name,SUM(chengji) FROM Stduent GROUP BY name
        //db.query(String distinct, String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having, String orderBy, String limit
          //去重       //表名          // 结果需要显示的列项 //条件语句   //条件语句对应的值   //分组条件       //分组条件下的附加条件例如小于多少 //升序/降序返回数据 //设定【一次返回多少条数据】
        Cursor cursor=sqLiteDatabase.query("Student",new String[]{"name","sum(chengji)"},null,null,"name",null,null);
      //  Cursor cursor=sqLiteDatabase.rawQuery("select name,SUM(chengji) from Student group by name ",null);
        if(!cursor.isFirst()){
            cursor.moveToFirst();
            int numb=cursor.getColumnCount();//获取列总数
            for (int i=0;i<numb;i++){//循环读取每列的数据
                String columnName=cursor.getColumnName(i);//获得每列的列名
                String value=cursor.getString(i);//获取每列对应的值
                Log.i("columnName",columnName);
                Log.i("value",value);
            }
        }
        while(cursor.moveToNext()) {//循环移动游标到下一行，
            int numb = cursor.getColumnCount();//获取列总数
            for (int i = 0; i < numb; i++) {//循环读取每列的数据
                String columnName = cursor.getColumnName(i);//获得每列的列名
                String value = cursor. getString(i);//获取每列对应的值
                Log.i("columnName", columnName);
                Log.i("value", value);

            }
        }
        //cursor操作完成以后,一定要关闭
        cursor.close();//游标关闭

    }

    //            long numb=sqLiteDatabase.insert("Student",null,contentValues);
            //返回的long类型的值为，新插入的行的id，如果返回为-1，则插入失败
   // int numb=sqLiteDatabase.update("Student",contentValues,"id=? and name=?",caluseArgs);
        //返回受影响的行数
   // int numb=sqLiteDatabase.delete("Student","id=?",new String[]{"15"});
        //返回受影响的行数，如果为0则删除失败

}
