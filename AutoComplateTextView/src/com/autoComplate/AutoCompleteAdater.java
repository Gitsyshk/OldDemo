package com.autoComplate;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

/**
 * 
 * @author lai_zs
 * @date：2012-2-16 下午3:49:25
 */
public class AutoCompleteAdater extends SimpleCursorAdapter {

	private DBHelper dbHelper = null;
	private Context context;
	// 查询字段
	private String queryField;

	// 参数
	//
	// context 应用程序上下文，具体来说就是ListView所在的上下文当中。
	//
	// layout 布局文件的资源定位标识符，也就是说标识了ListView中的item。那么这个布局文件至少包含了参数“to”中的传进来值。
	//
	// c 数据库游标，如果游标不可用则为null。
	//
	// from 列名字列表，表示着你要绑定到UI上的列。如果游标不可用则为null。
	//
	// to
	// 展示参数“from”中的列，也就是说ListView中的视图显示的是参数“from”的列值，这些视图应该都是TextView。如果游标不可用则为null。
	public AutoCompleteAdater(Context context, int layout, Cursor c, String from, int to) {
		super(context, layout, c, new String[] { from }, new int[] { to });
		this.context = context;
		this.queryField = from;
	}

	/**
	 * 动态查询数据库
	 */
	@Override
	public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
		if (constraint != null) {
			return getDbHelper().query((String) constraint);
		} else {
			return null;
		}
	}

	/**
	 * 这里设置在弹出的提示列表中点击某一项后的返回值,返回值将被显示在文本框中
	 */
	@Override
	public CharSequence convertToString(Cursor cursor) {
		return cursor.getString(cursor.getColumnIndex(queryField));
	}

	public DBHelper getDbHelper() {
		if (dbHelper == null) {
			dbHelper = new DBHelper(this.context);
		}
		return dbHelper;
	}

}
