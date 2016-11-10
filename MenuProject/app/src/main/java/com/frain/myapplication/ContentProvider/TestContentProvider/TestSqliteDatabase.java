package com.frain.myapplication.ContentProvider.TestContentProvider;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.frain.myapplication.R;


public class TestSqliteDatabase extends FragmentActivity implements OnClickListener{
	SQLiteDatabase database;
	StudentHelper helper;
	ListView listView;
	StudentAdapter adapter;
	List<StudentBean> list = new ArrayList<StudentBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		helper = new StudentHelper(this);
		database = helper.getWritableDatabase();
		//database = this.openOrCreateDatabase("myDatabase.db", MODE_PRIVATE, null);
		/*database = SQLiteDatabase.openOrCreateDatabase("/storage/sdcard/mydata.db", null);*/
		setContentView(R.layout.test_databases);
		listView = (ListView) findViewById(R.id.listview);
		findViewById(R.id.insert).setOnClickListener(this);
		findViewById(R.id.delete).setOnClickListener(this);
		findViewById(R.id.update).setOnClickListener(this);
		findViewById(R.id.query).setOnClickListener(this);
		
		adapter = new StudentAdapter(this, list);
		listView.setAdapter(adapter);
		query();
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.insert:
			insert();
			break;
		case R.id.delete:
			delete();
			break;
		case R.id.update:
			update();
			break;
		case R.id.query:
			query();
			break;
		}
	}
	
	public void insert(){
		for(int i=0; i< 20; i++) {
			ContentValues values = new ContentValues();
			values.put("stu_name", "zhangsan" + i);
			values.put("stu_age", 20 + i);
			long rowId = database.insert(StudentHelper.TABLE_STUDENT, null, values);
			System.out.println("insert rowId=" + rowId);
		}
		query();
	}
	
	public void query(){
		List<StudentBean> tmpList = null;
		//查询所以的数据
		Cursor cursor = database.query(StudentHelper.TABLE_STUDENT, null, null, null, null, null, null);
		if(cursor != null && cursor.getCount() > 0) {
			//一次性分配到位
			tmpList = new ArrayList<StudentBean>(cursor.getCount());
			while(cursor.moveToNext()) {
				StudentBean bean = new StudentBean();
				bean.stuId = cursor.getInt(cursor.getColumnIndex("stu_id"));
				bean.stuName = cursor.getString(cursor.getColumnIndex("stu_name"));
				bean.stuAge = cursor.getInt(cursor.getColumnIndex("stu_age"));
				tmpList.add(bean);
			}
			//关闭游标
			cursor.close();
		}
		if(tmpList != null && tmpList.size() > 0) {
			adapter.setDataSource(tmpList);
		} else {
			adapter.setDataSource(list);
		}
	}
	
	public void delete(){
		int count = database.delete(StudentHelper.TABLE_STUDENT, null, null);
		System.out.println("删除的记录条数为 ：" + count);
		query();
	}
	;
	public void update(){
		ContentValues values = new ContentValues();
		values.put("stu_name", "Lisi");
		int count = database.update(StudentHelper.TABLE_STUDENT, values, null, null);
		System.out.println("受影响的记录的条数为:" + count);
		query();
	}
}
