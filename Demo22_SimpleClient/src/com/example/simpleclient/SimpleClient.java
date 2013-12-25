package com.example.simpleclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


public class SimpleClient extends Activity {
	EditText show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		show = (EditText) findViewById(R.id.show);
		//�ر���������socket
		try
		{
			Socket socket = new Socket("218.64.220.107" , 30000);
			//��Socket��Ӧ����������װ��BufferedReader
			BufferedReader br = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			//������ͨIO����
			String line = br.readLine();
			show.setText("���Է����������ݣ�" + line);			
			br.close();
			socket.close();	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}