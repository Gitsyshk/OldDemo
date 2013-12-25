package com.ideaspace.data;

import org.json.JSONObject;


public class DataTool {
	public static DataModel formJson(String str){
		DataModel data = new DataModel();
		try {
			JSONObject jsonObject = new JSONObject(str.toString()) 
	        .getJSONObject("DataModel"); 
			data.value1 = jsonObject.getString("value1");
			data.value2 = jsonObject.getString("value2");
			data.value3 = jsonObject.getString("value3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
