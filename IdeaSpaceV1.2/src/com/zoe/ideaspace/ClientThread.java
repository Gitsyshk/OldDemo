package com.zoe.ideaspace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Handler;
import android.os.Message;

public class ClientThread implements Runnable {
	private Socket socket = null;
	private BufferedReader br = null;
	private MyApplication application;
	private DataModel dataModel;
	public ClientThread(String IP, int port,MyApplication application) throws UnknownHostException, IOException{
		socket = new Socket(IP, port);
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.application = application;
	}
	@Override
	public void run() {
		while (true) {
			try {
				 String content = null;
				 while ((content = br.readLine()) != null) {
					 dataModel = DataTool.formJson(content);
					 application.dataModel.setValue1(dataModel.value1);
					 application.dataModel.setValue2(dataModel.value2);
					 application.dataModel.setValue3(dataModel.value3);				 
				 } 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
