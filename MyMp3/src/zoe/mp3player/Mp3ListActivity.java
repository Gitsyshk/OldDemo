package zoe.mp3player;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import zoe.download.HttpDownloader;
import zoe.model.Mp3Info;
import zoe.xml.Mp3ListContentHandler;
import android.R.xml;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class Mp3ListActivity extends ListActivity {

	private static final int UPDATE = 1;
	private static final int ABOUT = 2;
	String urlStr = "http://192.168.15.129:8080/mp3/resurces.xml";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, UPDATE, 1, R.string.mp3list_updata);
		menu.add(0, ABOUT, 2, R.string.mp3list_about);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case UPDATE:
			String xml = downloadXML(urlStr);
			List<Mp3Info> mp3Infos = parse(xml);
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			for (Iterator iterator = mp3Infos.iterator(); iterator.hasNext();) {
				Mp3Info mp3Info = (Mp3Info) iterator.next();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("mp3.name", mp3Info.getMp3Name());
				map.put("mp3.size", mp3Info.getMp3Size());
				list.add(map);
			}
			SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
					R.layout.mp3info_item, new String[] { "mp3_name",
							"mp3_size" }, new int[] { R.id.mp3_name,
							R.id.mp3_size });
			setListAdapter(simpleAdapter);

			break;
		case ABOUT:

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private String downloadXML(String urlStr) {
		HttpDownloader httpDownloader = new HttpDownloader();
		String result = httpDownloader.download(urlStr);
		return result;
	}

	private List<Mp3Info> parse(String xmlStr) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		List<Mp3Info> infos = new ArrayList<Mp3Info>();
		try {
			XMLReader xmlReader = saxParserFactory.newSAXParser()
					.getXMLReader();

			Mp3ListContentHandler mp3ListContentHandler = new Mp3ListContentHandler(
					infos);
			xmlReader.setContentHandler(mp3ListContentHandler);
			xmlReader.parse(new InputSource(new StringReader(xmlStr)));
			for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
				Mp3Info mp3Info = (Mp3Info) iterator.next();
				System.out.println(mp3Info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infos;
	}
}