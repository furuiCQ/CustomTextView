package com.frain.myapplication.ContentProvider.TestContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentHelper extends SQLiteOpenHelper{
	public static final String DB_NAME = "student.db";
	public static final int DB_VERSION = 2;
	public static final String TABLE_STUDENT = "student";
	public static final String TABLE_COURSE = "course";
	public static final String TABLE_CHOICE_COURSE = "choice_course";
	public static final String VIEW_STUDENT_CHOICE_COURSE = "student_choice_course";
	
	public String TABLE_STUDENT_SQL = 
			"create table " + TABLE_STUDENT +" (" +
			"stu_id integer primary key autoincrement," +
			"stu_name char(32) not null," +
			"stu_age integer not null" +
			");"; 
	public String TABLE_COURSE_SQL = 
			"create table " + TABLE_COURSE + " (" +
			"c_id integer primary key autoincrement," +
			"c_name char(32) not null" +
			");";
	public String TABLE_CHOICE_COURSE_SQL = 
			"create table " + TABLE_CHOICE_COURSE + " (" +
			"stu_id integer not null," +
			"c_id integer not null," +
			"score integer default 0," +
			"primary key (stu_id, c_id)" +
			");";
	public String VIEW_STUDENT_CHOICE_COURSE_SQL = 
			"create view " + VIEW_STUDENT_CHOICE_COURSE 
			+ " as select student.stu_id, student.stu_name, student.stu_age, course.c_id, course.c_name, choice_course.score " +
			"from student, course, choice_course " +
			"where student.stu_id = choice_course.stu_id and course.c_id = choice_course.c_id";

	public StudentHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 * 当第一次创建的时候，回调onCreate
	 * 一般做一些创建表的操作
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_STUDENT_SQL);
		db.execSQL(TABLE_COURSE_SQL);
		db.execSQL(TABLE_CHOICE_COURSE_SQL);
		db.execSQL(VIEW_STUDENT_CHOICE_COURSE_SQL);
	}

	/**
	 * 当数据库升级的时候，回调此方法
	 * 先删掉以前的表，然后新创建
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion > oldVersion) {
			//先删除
			db.execSQL("drop table if exists student_choice_course");
			db.execSQL("drop table if exists choice_course");
			db.execSQL("drop table if exists course");
			db.execSQL("drop table if exists student");
			onCreate(db); //重建
		}
	}

}
