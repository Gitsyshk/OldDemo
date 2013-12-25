package com.zoe.date;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

/**
 * 
 * @author lai_zs
 * @date��2012-2-16 ����3:49:25
 */
public class AutoCompleteAdater extends SimpleCursorAdapter {

	private Context context;
	private DataBaseHelper helper;

	public AutoCompleteAdater(Context context, int layout, Cursor c,
			String from, int to, DataBaseHelper helper) {
		super(context, layout, c, new String[] { from }, new int[] { to });
		this.context = context;
		this.helper = helper;
	}

	/**
	 * ��̬��ѯ���ݿ�
	 */
	@Override
	public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
		if (constraint != null) {
			return helper.query((String) constraint);
		} else {
			return null;
		}
	}

	/**
	 * ���������ڵ�������ʾ�б��е��ĳһ���ķ���ֵ,����ֵ������ʾ���ı�����
	 */
	@Override
	public CharSequence convertToString(Cursor cursor) {
		return cursor.getString(cursor.getColumnIndex("name"));
	}
}