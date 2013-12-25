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
 * 在应用程序被创建时被调用
 */
	@Override
	public void onCreate() {

		super.onCreate();
		setMsg("初始值");
	}
}
