package com.frain.myapplication.MySqlite.AccountsPractice;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/9.
 *
 */
//数据库操作练习：
//记账本app练习 模仿该app完成如下功能
//1.收入一笔的功能
//2.支出一笔的功能
//3.编辑类别
//4.账目清单的功能
//5.数据统计的功能
//思路讲解：
//1.记账的收入和支出都属于数据库操作的insert
//2.账目清单和数据统计都属于数据库操作的select
//3.分析本地数据库，账单这张数据库表的表结构是怎样的
//A.一张表格为收入类别的表 存放字段为id 以及收入类别的字段名
//B.支出大类别的表  存放字段为id，以及支出大类别的名字 //支出小类别的表  大类别的id，小类别的id，小类别的名字
//               Date
//C.存放收入和支出的这张表中的字段 总类别的类型【收入/支出】 类别的id,类别的名字,记账日期,记账的地点，记账的备注
//4.账目清单下面的三个选项分别是筛选功能
//5.数据统计就是多去使用where条件判断语句，并把结果进行和数据库操作
public class AccoutnsActivity extends Activity{
    Button incomeButton;
    Button expandButton;
    Button typeButton;
    Button listButton;
    Button staticsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_main);
        incomeButton=(Button)findViewById(R.id.button_income);
        expandButton=(Button)findViewById(R.id.button_expand);
        typeButton=(Button)findViewById(R.id.button_type);
        listButton=(Button)findViewById(R.id.button_list);
        staticsButton=(Button)findViewById(R.id.button_statistics);

        incomeButton.setOnClickListener(clickListener);
        expandButton.setOnClickListener(clickListener);
        typeButton.setOnClickListener(clickListener);
        listButton.setOnClickListener(clickListener);
        staticsButton.setOnClickListener(clickListener);
        initSQliteDataBase();
    }
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.button_income:
                     intent=new Intent(AccoutnsActivity.this,InComeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_expand:
                    break;
                case R.id.button_type:
                    break;
                case R.id.button_list:
                    break;
                case R.id.button_statistics:
                    break;
            }
        }
    };
    SQLiteDatabase sqLiteDatabase;
    public void initSQliteDataBase(){
        AccountsSQliteOpenHelper accountsSQliteOpenHelper=new AccountsSQliteOpenHelper(this,"Accounts.db",null,1);
        sqLiteDatabase=accountsSQliteOpenHelper.getWritableDatabase();
    }

}
