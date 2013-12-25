package com.zoe.sqlitetest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	@SuppressLint("SdCardPath")
	private String DB_PATH = "/data/data/com.zoe.sqlitetest/databases/";

	public static  String DB_NAME = "weather.db3";

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
		} else {
			this.getReadableDatabase();

			try {
				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	private boolean checkDataBase() {

		// SQLiteDatabase checkDB = null;
		//
		// try{
		// String myPath = DB_PATH + DB_NAME;
		// checkDB = SQLiteDatabase.openDatabase(myPath, null,
		// SQLiteDatabase.OPEN_READONLY);
		//
		// }catch(SQLiteException e){
		//
		// }
		//
		// if(checkDB != null){
		//
		// checkDB.close();
		//
		// }
		//
		// return checkDB != null ? true : false;
		File file = new File(DB_PATH + DB_NAME);
		return file.exists();
	}

	public void copyDataBase() throws IOException {
		
			// �򿪱��ص����ݿ⵽��������
			InputStream myInput = myContext.getAssets().open(DB_NAME);

			// �������ݿ���λ��
			String outFileName = DB_PATH + DB_NAME;

			// ���ļ��������
			OutputStream myOutput = new FileOutputStream(outFileName);

			// �����������������������APK�Դ����ݿ⴫��ָ��λ��
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}

			// �ر���
			myOutput.flush();
			myOutput.close();
			myInput.close();
		
	}

	public void openDataBase() throws SQLException {

		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	public Cursor query(String name) {
	       SQLiteDatabase db = this.getReadableDatabase();
	       return db.rawQuery("select* from user where name like '%" + name + "%'",null);
	    }
	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
