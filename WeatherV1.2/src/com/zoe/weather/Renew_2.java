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
				URL url = new URL(path + application.city_id + ".html"); // ����String��ʾ��ʽ����URL
																// ����
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();// ����һ��
											// URLConnection��������ʾ��URL�����õ�Զ�̶�������ӡ�
				conn.setConnectTimeout(5000);
				InputStreamReader isr = new InputStreamReader(
						conn.getInputStream());// ���شӴ˴򿪵����Ӷ�ȡ����������
				BufferedReader br = new BufferedReader(isr);// ����һ��ʹ��Ĭ�ϴ�С���뻺�����Ļ����ַ���������
				String temp;
				while ((temp = br.readLine()) != null) { // ���ж�ȡ�����
					// if (!temp.trim().equals("")) {
					html.append(temp).append("\n"); // ����ÿ�к���
					// }
				}
				br.close(); // �ر�
				isr.close(); // �ر�
				application.myData_2 = DataTool.formJson_2(html.toString());
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
