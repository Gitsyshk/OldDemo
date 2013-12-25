package com.zoe.data;

import com.google.gson.Gson;

public class DataTool {
	public static MyData formJson(String str) {
		Gson gson = new Gson();
		MyData model = gson.fromJson(str, MyData.class);
		return model;
	}
}
