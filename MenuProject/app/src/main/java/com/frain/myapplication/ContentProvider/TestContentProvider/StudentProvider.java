package com.frain.myapplication.ContentProvider.TestContentProvider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class StudentProvider extends ContentProvider{
	public static final String TAG = "StudentProvider";
	//授权一般使用包名加类名，见名知义的名字
	public static final String 	AUTHORITY = "com.twentythree.myandroidlearn.database.provider.StudentProvider";
	//定义URI
	public static final Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/student");
	public static final Uri COURSE_URI = Uri.parse("content://" + AUTHORITY + "/course");
	public static final Uri CHOICE_COURSE_URI = Uri.parse("content://" + AUTHORITY + "/choice_course");
	public static final Uri STUDENT_CHOICE_COURSE_URI = Uri.parse("content://" + AUTHORITY + "/student_choice_course");
	//定义匹配码
	public static final int STUDENT_CODE = 0;
	public static final int STUDENT_ITEM_CODE = 1;
	public static final int COURSE_CODE = 2;
	public static final int COURSE_ITEM_CODE = 3;
	public static final int CHOICE_COURSE_CODE = 4;
	public static final int CHOICE_COURSE_ITEM_CODE = 5;
	public static final int STUDENT_CHOICE_COURSE_CODE = 6;
	public static final int STUDENT_CHOICE_COURSE_ITEM_CODE = 7;
	UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	{
		//# 匹配数字 *匹配所有的字符
		matcher.addURI(AUTHORITY, "student", STUDENT_CODE);
		matcher.addURI(AUTHORITY, "student/#", STUDENT_ITEM_CODE);
		
		matcher.addURI(AUTHORITY, "course", COURSE_CODE);
		matcher.addURI(AUTHORITY, "course/#", COURSE_ITEM_CODE);
		
		matcher.addURI(AUTHORITY, "choice_course", CHOICE_COURSE_CODE);
		matcher.addURI(AUTHORITY, "choice_course/#", CHOICE_COURSE_ITEM_CODE);
		
		matcher.addURI(AUTHORITY, "student_choice_course", STUDENT_CHOICE_COURSE_CODE);
		matcher.addURI(AUTHORITY, "student_choice_course/#", STUDENT_CHOICE_COURSE_ITEM_CODE);
	}

	SQLiteDatabase database;
	StudentHelper helper;
	/**
	 * 创建
	 */
	@Override
	public boolean onCreate() {
		helper = new StudentHelper(getContext());
		database = helper.getWritableDatabase();
		return database == null ? false : true;
	}
	
	/**
	 * 返回此URI所标识的资源的MIME类型
	 */
	@Override
	public String getType(Uri uri) {
		//如果是一张图片 返回 "image/*" mp3  "audio/x-mpeg";
		switch(matcher.match(uri)) {
		case STUDENT_CODE: //content://com.twentythree.myandroidlearn.database.provider.StudentProvider/student
		case COURSE_CODE:
		case CHOICE_COURSE_CODE:
		case STUDENT_CHOICE_COURSE_CODE:
			//这个是一个自定义的MIME类型，代表的是一张表的记录
			return "vnd.android.cursor.dir/com.twentythree.myandroidlearn";
		case STUDENT_ITEM_CODE:
		case COURSE_ITEM_CODE:
		case CHOICE_COURSE_ITEM_CODE:
		case STUDENT_CHOICE_COURSE_ITEM_CODE:
			//这个是一个自定义的MIME类型，代表的是一条记录
			return "vnd.android.cursor.item/com.twentythree.myandroidlearn";
		}
		return "*/*";
	}
	/**
	 * uri 
	 * values 键值对
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowId = -1; //插入数据的行ID
		Uri insertUri = null; //返回的URI
		switch(matcher.match(uri)) {
		case STUDENT_CODE:
		case STUDENT_ITEM_CODE:
			rowId = database.insert(StudentHelper.TABLE_STUDENT, null, values);
			insertUri = STUDENT_URI;
			break;
		case COURSE_CODE:
		case COURSE_ITEM_CODE:
			rowId = database.insert(StudentHelper.TABLE_COURSE, null, values);
			insertUri = COURSE_URI;
			break;
		case CHOICE_COURSE_CODE:
		case CHOICE_COURSE_ITEM_CODE:
			rowId = database.insert(StudentHelper.TABLE_CHOICE_COURSE, null, values);
			insertUri = CHOICE_COURSE_URI;
			break;
		case STUDENT_CHOICE_COURSE_CODE:
		case STUDENT_CHOICE_COURSE_ITEM_CODE:
			//View视图不能执行插入操作,所以在这里，什么都不做
			break;
		}
		if(rowId > -1) {
			//新的数据已经插入到表里面去了
			insertUri = ContentUris.withAppendedId(insertUri, rowId);
			Log.d(TAG, "insert new uri =" + insertUri.toString());
			//通知内容观察者更新数据  ContentObserver
			getContext().getContentResolver().notifyChange(insertUri, null);
		}
		return insertUri;
	}

	/**
	 * 删除
	 * uri
	 * selection where 子句
	 * selectionArgs where子句的占位符
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		String where = ""; //组合的where子句
		String[] whereArgs = null; //组合的where子句占位符
		long id;
		int count = 0; //删除的行数
		switch(matcher.match(uri)) {
		case STUDENT_CODE:
			count = database.delete(StudentHelper.TABLE_STUDENT, selection, selectionArgs);
			break;
		case STUDENT_ITEM_CODE: 
			//content://com.twentythree.myandroidlearn.database.provider.StudentProvider/student/15
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " stu_id = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and stu_id = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and stu_id = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			count = database.delete(StudentHelper.TABLE_STUDENT, where, whereArgs);
			break;
		case COURSE_CODE:
			count = database.delete(StudentHelper.TABLE_COURSE, selection, selectionArgs);
			break;
		case COURSE_ITEM_CODE:
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " c_id = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and c_id = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and c_id = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			count = database.delete(StudentHelper.TABLE_COURSE, where, whereArgs);
			break;
		case CHOICE_COURSE_CODE:
			count = database.delete(StudentHelper.TABLE_CHOICE_COURSE, selection, selectionArgs);
			break;
		case CHOICE_COURSE_ITEM_CODE:
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " rowId = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and rowId = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and rowId = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			count = database.delete(StudentHelper.TABLE_CHOICE_COURSE, where, whereArgs);
			break;
		case STUDENT_CHOICE_COURSE_CODE:
		case STUDENT_CHOICE_COURSE_ITEM_CODE:
			//视图不能执行删除操作，还是什么都不做
			break;
		}
		if(count > 0) {
			//代表数据有删除
			Log.d(TAG, "delete count =" + count);
			//通知更新
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	/**
	 * 删除
	 * uri
	 * values 键值对
	 * selection where子句
	 * selectionArgs where子句的占位符
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int count = 0; //受影响的记录的条数
		String where = ""; //组合的where子句
		String[] whereArgs = null; //组合的where子句占位符
		long id;
		switch(matcher.match(uri)) {
		case STUDENT_CODE:
			count = database.update(StudentHelper.TABLE_STUDENT, values, selection, selectionArgs);
			break;
		case STUDENT_ITEM_CODE: 
			//content://com.twentythree.myandroidlearn.database.provider.StudentProvider/student/15
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " stu_id = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and stu_id = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and stu_id = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			count = database.update(StudentHelper.TABLE_STUDENT, values, where, whereArgs);
			break;
		case COURSE_CODE:
			count = database.update(StudentHelper.TABLE_COURSE, values, selection, selectionArgs);
			break;
		case COURSE_ITEM_CODE:
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " c_id = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and c_id = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and c_id = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			count = database.update(StudentHelper.TABLE_COURSE, values, where, whereArgs);
			break;
		case CHOICE_COURSE_CODE:
			count = database.update(StudentHelper.TABLE_CHOICE_COURSE, values, selection, selectionArgs);
			break;
		case CHOICE_COURSE_ITEM_CODE:
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " rowId = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and rowId = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and rowId = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			count = database.update(StudentHelper.TABLE_CHOICE_COURSE, values, where, whereArgs);
			break;
		case STUDENT_CHOICE_COURSE_CODE:
		case STUDENT_CHOICE_COURSE_ITEM_CODE:
			//视图不能执行更新操作，还是什么都不做
			break;
		}
		if(count > 0) {
			//代表数据有删除
			Log.d(TAG, "update count =" + count);
			//通知更新
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	/**
	 * 查询
	 * uri 
	 * projection 选择的列名
	 * selection where子句
	 * selectionArgs where子句的占位符
	 * sortOrder 排序
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		long id;
		String where = ""; //组合的where子句
		String[] whereArgs = null; //组合的where子句占位符
		switch(matcher.match(uri)) {
		case STUDENT_CODE:
			cursor = database.query(StudentHelper.TABLE_STUDENT, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case STUDENT_ITEM_CODE: 
			//content://com.twentythree.myandroidlearn.database.provider.StudentProvider/student/15
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " stu_id = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and stu_id = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and stu_id = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			cursor = database.query(StudentHelper.TABLE_STUDENT, projection, where, whereArgs, null, null, sortOrder);
			break;
		case COURSE_CODE:
			cursor = database.query(StudentHelper.TABLE_COURSE, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case COURSE_ITEM_CODE:
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " c_id = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and c_id = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and c_id = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			cursor = database.query(StudentHelper.TABLE_COURSE, projection, where, whereArgs, null, null, sortOrder);
			break;
		case CHOICE_COURSE_CODE:
			cursor = database.query(StudentHelper.TABLE_CHOICE_COURSE, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case CHOICE_COURSE_ITEM_CODE:
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " rowId = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and rowId = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and rowId = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			cursor = database.query(StudentHelper.TABLE_CHOICE_COURSE, projection, where, whereArgs, null, null, sortOrder);
			break;
		case STUDENT_CHOICE_COURSE_CODE:
			cursor = database.query(StudentHelper.VIEW_STUDENT_CHOICE_COURSE, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case STUDENT_CHOICE_COURSE_ITEM_CODE:
			id = ContentUris.parseId(uri);
			if(selection == null) {
				where = " rowId = ? ";
				whereArgs = new String[] {Long.toString(id)};
			} else {
				if(selectionArgs == null) {
					where = selection + " and rowId = ? ";
					whereArgs = new String[] {Long.toString(id)};
				} else {
					where = selection + " and rowId = ? ";
					whereArgs = new String[selectionArgs.length + 1];
					//拷贝占位符数组
					for(int i=0; i< selectionArgs.length; i++) {
						whereArgs[i] = selectionArgs[i];
					}
					whereArgs[selectionArgs.length] = Long.toString(id);
				}
			}
			cursor = database.query(StudentHelper.VIEW_STUDENT_CHOICE_COURSE, projection, where, whereArgs, null, null, sortOrder);
			break;
		}
		//没有更改数据，不用通知
		return cursor;
	}
}
