package com.autoComplate;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

/**
 * 
 * @author lai_zs
 * @date��2012-2-16 ����3:49:25
 */
public class AutoCompleteAdater extends SimpleCursorAdapter {

	private DBHelper dbHelper = null;
	private Context context;
	// ��ѯ�ֶ�
	private String queryField;

	// ����
	//
	// context Ӧ�ó��������ģ�������˵����ListView���ڵ������ĵ��С�
	//
	// layout �����ļ�����Դ��λ��ʶ����Ҳ����˵��ʶ��ListView�е�item����ô��������ļ����ٰ����˲�����to���еĴ�����ֵ��
	//
	// c ���ݿ��α꣬����α겻������Ϊnull��
	//
	// from �������б���ʾ����Ҫ�󶨵�UI�ϵ��С�����α겻������Ϊnull��
	//
	// to
	// չʾ������from���е��У�Ҳ����˵ListView�е���ͼ��ʾ���ǲ�����from������ֵ����Щ��ͼӦ�ö���TextView������α겻������Ϊnull��
	public AutoCompleteAdater(Context context, int layout, Cursor c, String from, int to) {
		super(context, layout, c, new String[] { from }, new int[] { to });
		this.context = context;
		this.queryField = from;
	}

	/**
	 * ��̬��ѯ���ݿ�
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
	 * ���������ڵ�������ʾ�б��е��ĳһ���ķ���ֵ,����ֵ������ʾ���ı�����
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
