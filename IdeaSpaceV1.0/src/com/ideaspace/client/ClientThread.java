package com.ideaspace.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ClientThread implements Runnable {
	private Socket socket = null;
	private Handler handler = null;
	private BufferedReader br = null;

	public ClientThread(String IP, int port, Handler handler) throws Exception {
		socket = new Socket(IP, port);
		Log.d("sada", "sadas");
		this.handler = handler;
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		while (true) {
			try {
//				InputStream in = socket.getInputStream();
//				String str = null;
//				int count = 37;
//				int readCount = 0;
//				byte[] buffer = new byte[37];
//				while (readCount < count) {
//					readCount += in.read(buffer, readCount, count - readCount);
//				}
//				str = new String(buffer);
//				Message msg = new Message();
//				msg.what = 0x1233;
//				msg.obj = str;
//				handler.sendMessage(msg);
				 String content = null;
				 while ((content = br.readLine()) != null) {
					Message msg = new Message();
					msg.what = 0x1233;
					msg.obj = content;
					handler.sendMessage(msg);
				 } 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
