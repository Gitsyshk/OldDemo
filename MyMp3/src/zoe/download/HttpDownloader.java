package zoe.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpDownloader {
	private URL url = null;

	public String download(String urlStr) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			url = new URL(urlStr);
			HttpsURLConnection urlConn = (HttpsURLConnection) url
					.openConnection();
			buffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while((line = buffer.readLine())!=null)
			{
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			try{
				buffer.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
