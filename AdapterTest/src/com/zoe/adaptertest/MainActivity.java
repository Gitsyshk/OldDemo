package com.zoe.adaptertest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	MyArrayAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ArrayList<String> arrayList = new ArrayList<String>();
		ListView listView = (ListView) findViewById(R.id.list);
		
		arrayList.add("111");
		arrayList.add("111");
		arrayList.add("111");
		arrayList.add("111");
		arrayList.add("111");
		arrayList.add("111");
		arrayList.add("111");

		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					Toast.makeText(MainActivity.this, "" + msg.arg1,
							Toast.LENGTH_SHORT).show();
					arrayList.remove(msg.arg1);			//在data中删除该位置的数据
					adapter.notifyDataSetChanged();
				}
			}
		};
		adapter = new MyArrayAdapter(this, arrayList, handler);
		 listView.setAdapter(adapter);
	}

	public class MyArrayAdapter extends BaseAdapter {
		private ArrayList<String> data;
		private Handler handler;
		private Context context;

		public MyArrayAdapter(Context context, List<String> data,
				Handler handler) {
			this.context = context;
			this.handler = handler;
			this.data = (ArrayList<String>) data;
		}

		class ViewHolder {
			TextView textView;
			Button button;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (null == convertView) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.item, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.text);
				holder.button = (Button) convertView.findViewById(R.id.bn);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.textView.setText(data.get(position));
			holder.button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Message message = new Message();
					message.what = 0x999;
					message.arg1 = position;
					handler.sendMessage(message);
				}
			});
			return convertView;
		}

		@Override
		public int getCount() {
			return null != data ? data.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return null != data ? data.get(position) : null;
		}

		@Override
		public long getItemId(int position) {
			return null != data ? position : 0;
		}
	}
		
}
