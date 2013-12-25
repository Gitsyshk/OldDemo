package com.zoe.jsontest;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JsonUtils {

	public static void parseJson(String jsonData, MyApplication application) {
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				while (reader.hasNext()) {
					String tarName = reader.nextName();
					if (tarName.equals("key1")) {
						application.data.setValue1(reader.nextString());
						
					} else if (tarName.equals("key2")) {
						application.data.setValue2(reader.nextString());
						
					} else if (tarName.equals("key3")) {
						application.data.setValue3(reader.nextString());
						
					} else if (tarName.equals("key4")) {
						application.data.setValue4(reader.nextString());
						
					}
				}
				reader.endObject();
			}
			reader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void parseDataFromJson(String jsonData,MyData data)
	{
		Gson gson = new Gson();
		data = gson.fromJson(jsonData, MyData.class);
		System.out.println(data.getValue1());
		
	}
}
