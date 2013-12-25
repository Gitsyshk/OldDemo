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
			URL url = new URL(path + array[i] + ".html"); // ����String��ʾ��ʽ����URL
															// ����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();// ����һ��
																				// URLConnection��������ʾ��URL�����õ�Զ�̶�������ӡ�
			conn.setConnectTimeout(5000);

			InputStreamReader isr = new InputStreamReader(conn.getInputStream());// ���شӴ˴򿪵����Ӷ�ȡ����������
			BufferedReader br = new BufferedReader(isr);// ����һ��ʹ��Ĭ�ϴ�С���뻺�����Ļ����ַ���������
			String temp;
			while ((temp = br.readLine()) != null) { // ���ж�ȡ�����
				// if (!temp.trim().equals("")) {
				html.append(temp).append("\n"); // ����ÿ�к���
				// }
			}
			br.close(); // �ر�
			isr.close(); // �ر�
			application.myData = DataTool.formJson(html.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
