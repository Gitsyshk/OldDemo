package com.autoComplate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author lai_zs
 * @date：2012-2-16 下午4:16:02
 */
public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "autoComplete.db";
	// 根据name自动查询
	public static final String NAME = "name";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlString = "create table test (_id integer primary key autoincrement,name varchat(20) not null on conflict fail)";
		db.execSQL(sqlString);
		// 初始数据库表
		String[] nameStrArrayStr = new String[] { "aaa", "abc", "cde", "中国", "美女", "提示" };
		for (int i = 0; i < nameStrArrayStr.length; i++) {
			db.execSQL("INSERT INTO test(" + NAME + ") values(?)", new Object[] { nameStrArrayStr[i] });
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// do nothing here
	}

	/**
	 * 根据输入内容模糊查询
	 * @param name
	 * @return
	 */
	public Cursor query(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		return db.rawQuery("select * from test where name like '%" + name + "%'", null);
	}

}
