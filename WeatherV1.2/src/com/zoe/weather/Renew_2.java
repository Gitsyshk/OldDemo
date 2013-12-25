package com.zoe.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.zoe.date.DataTool;

public class Renew_2 extends Thread {
	private String path = "http://www.weather.com.cn/data/cityinfo/";
	private MyApplication application;

	public Renew_2(MyApplication application) {

		this.application = application;
	}

	@Override
	public void run() {
		while(true)
			try {		
				StringBuffer html = new StringBuffer();
				URL url = new URL(path + application.city_id + ".html"); // 根据String表示形式创建URL
																// 对象。
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();// 返回一个
											// URLConnection对象，它表示到URL所引用的远程对象的连接。
				conn.setConnectTimeout(5000);
				InputStreamReader isr = new InputStreamReader(
						conn.getInputStream());// 返回从此打开的连接读取的输入流。
				BufferedReader br = new BufferedReader(isr);// 创建一个使用默认大小输入缓冲区的缓冲字符输入流。
				String temp;
				while ((temp = br.readLine()) != null) { // 按行读取输出流
					// if (!temp.trim().equals("")) {
					html.append(temp).append("\n"); // 读完每行后换行
					// }
				}
				br.close(); // 关闭
				isr.close(); // 关闭
				application.myData_2 = DataTool.formJson_2(html.toString());
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
