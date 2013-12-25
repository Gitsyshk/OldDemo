package com.zoe.androidgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class ExternalStorageTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);
		String state = Environment.getExternalStorageState();
		if(!state.equals(Environment.MEDIA_MOUNTED))
		{
			textView.setText("SD未挂载");
		}
		else{
			File externalDir = Environment.getExternalStorageDirectory();
			File textFile = new File(externalDir.getPath() + File.separator + "text.txt");
			try {
				writeTextFile(textFile,"这是文件。");
				String text = readTextFile(textFile);
				textView.setText(text);
				if(!textFile.delete())
				{
					textView.setText("无法删除文件");
				}
			} catch (IOException e) {
				 textView.setText("Wrong:"+e.getMessage());
			}
		}
	}



	private void writeTextFile(File file, String text) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(text);
		writer.close();
	}

	private String readTextFile(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder text = new StringBuilder();
		String line;
		while((line = reader.readLine())!=null)
		{
			text.append(line+"\n");
		}
		reader.close();
		return text.toString();
	}
}
