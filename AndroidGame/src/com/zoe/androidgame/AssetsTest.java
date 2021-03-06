package com.zoe.androidgame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AssetsTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView textView = new EditText(this);
		setContentView(textView);
		AssetManager assetManager = getAssets();
		InputStream inputStream = null;
		try
		{
			inputStream = assetManager.open("text/123.txt");
			String text = loadTextFile(inputStream);
			textView.setText(text);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}finally
		{
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					textView.setText("无法关闭文件");
				}
		}
	}

	public String loadTextFile(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[4096];
		int len;
		while ((len = inputStream.read(bytes)) > 0)
			byteStream.write(bytes, 0, len);
		return new String(byteStream.toByteArray(), "UTF-8");
	}
}
