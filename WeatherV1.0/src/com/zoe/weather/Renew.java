package com.zoe.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.zoe.data.DataTool;

public class Renew extends Thread {
	private String path = "http://www.weather.com.cn/data/cityinfo/";
	private MyApplication application;
	private int[] array = new int[] { 101010100, 101020100, 101280101,
			101240701, 101280601, 101210501, 101240301, 101241101, 101290101,
			101300101, 101080701 };

	int i = 0;

	public Renew(MyApplication application) {

		this.application = application;
	}

	@Override
	public void run() {

		try {
			i++;
			if (i >= array.length)
				i = 0;
			StringBuffer html = new StringBuffer();
			URL url = new URL(path + array[i] + ".html"); // 根据String表示形式创建URL
															// 对象。
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 返回一个
																				// URLConnection对象，它表示到URL所引用的远程对象的连接。
			conn.setConnectTimeout(5000);

			InputStreamReader isr = new InputStreamReader(conn.getInputStream());// 返回从此打开的连接读取的输入流。
			BufferedReader br = new BufferedReader(isr);// 创建一个使用默认大小输入缓冲区的缓冲字符输入流。
			String temp;
			while ((temp = br.readLine()) != null) { // 按行读取输出流
				// if (!temp.trim().equals("")) {
				html.append(temp).append("\n"); // 读完每行后换行
				// }
			}
			br.close(); // 关闭
			isr.close(); // 关闭
			application.myData = DataTool.formJson(html.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
