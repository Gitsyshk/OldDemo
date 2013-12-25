package com.ideaspace.data;

import com.google.gson.Gson;

public class DataTool {
	public static DataModel formJson(String str) {
		Gson gson = new Gson();
		DataModel model = gson.fromJson(str, DataModel.class);
		return model;
	}
}
