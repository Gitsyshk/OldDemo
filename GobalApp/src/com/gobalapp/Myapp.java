package com.gobalapp;

import android.app.Application;

public class Myapp extends Application {
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
/*
 * (non-Javadoc)
 * @see android.app.Application#onCreate()
 * ��Ӧ�ó��򱻴���ʱ������
 */
	@Override
	public void onCreate() {

		super.onCreate();
		setMsg("��ʼֵ");
	}
}
