package org.mingjiang.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import trainApp.common.HttpHelper;
import trainApp.common.TrainInfo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class RemainTicketsList extends Activity {

	int mLastPosition = -1;
	View mLastView;
	
	ListView list;
	ArrayList<HashMap<String, Object>> listItem;
	HashMap<String, Object> map;
	SimpleAdapter listItemAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.remainticketslist);
		// ��Layout�����ListView
		list = (ListView) findViewById(R.id.remainTicketsList);
		TextView refreshTime_Label = (TextView) this.findViewById(R.id.refreshTime_Label);
		
		
		Bundle mBundle = this.getIntent().getExtras();
		String data = mBundle.getString("data");
		String title = mBundle.getString("start_arriveStation");
		String refreshTime = mBundle.getString("refreshTime");
		
		this.setTitle( title + "�Ĳ�ѯ���");
		refreshTime_Label.setText("������������ÿСʱ����һ�Σ��˴θ���ʱ��:" + refreshTime);

		List<TrainInfo> trainArray = HttpHelper.getTrainInfoList(data);

		listItem = new ArrayList<HashMap<String, Object>>();

		listItemAdapter = new SimpleAdapter(this, listItem, R.layout.remainticketslistitem, 
					new String[] { "ItemTitle", "Start_ArriveStation", "ItemText1", "ItemText2", "ItemText3" }, 
					new int[] { R.id.ItemTitle,R.id.start_arriveStation, R.id.ItemText1, R.id.ItemText2, R.id.ItemText3 });
		// ��Ӳ�����ʾ
		list.setAdapter(listItemAdapter);
		
		
		for (int i = 0; i < trainArray.size(); i++) {
			map = new HashMap<String, Object>();
			
			map.put("ItemTitle", trainArray.get(i).getTrainCode().replace(">", ""));
			
			String Start_ArriveStation =" " + trainArray.get(i).getStartStation() + "-" + trainArray.get(i).getArriveStation();
			map.put("Start_ArriveStation", Start_ArriveStation);
			
			String itemText1 = " ��ʱ:" + trainArray.get(i).getStartTime()
					+ "  ��ʱ:" + trainArray.get(i).getArrtiveTime() + "  ��ʱ:"
					+ trainArray.get(i).getUsedTime();
			map.put("ItemText1", itemText1);
			
			String itemText2 = "����:" + trainArray.get(i).getSoftSeatCount()
					+ "    Ӳ��:" + trainArray.get(i).getHardSeatCount()
					+ "    һ����:" + trainArray.get(i).getFirstClassSeatCount()
					+ "    ������:" + trainArray.get(i).getSecondClassSeatCount();
			map.put("ItemText2", itemText2);
			
			String itemText3 = "վƱ:" + (trainArray.get(i).isHaveSeat() == true ? "��" : "��")
					+ "    ����:" + trainArray.get(i).getSoftCouchetteCount() 
					+ "    Ӳ��:" + trainArray.get(i).getHartCouchetteCount()
					+ "    �߼�����:" + trainArray.get(i).getPremiumCouchetteCount();
			map.put("ItemText3", itemText3);
			listItem.add(map);
			listItemAdapter.notifyDataSetChanged();
		}

		// ��ӵ��
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				if(position != mLastPosition) {  
		             //����������Ŀ����һ�εĲ�ͬ����ô��չ������Ŀ���ر��ϴ�չ������Ŀ  
		            setVisible(view);  
		            setGone(mLastView);  
		            mLastPosition = position;  
		            mLastView = view;  
		        } else {  
		              //����������Ŀ����һ�ε���ͬ����ô�͵����Ի����ṩ���๦��ѡ��  
		        }  
			}
		});

	}

	private void setVisible(View view) {
		if (view == null)
			return;
		view.findViewById(R.id.ItemText2).setVisibility(View.VISIBLE);
		view.findViewById(R.id.ItemText3).setVisibility(View.VISIBLE);
	}
	
	 private void setGone(View view) {  
	       if(view == null)return;  
	       view.findViewById(R.id.ItemText2).setVisibility(View.GONE);  
	       view.findViewById(R.id.ItemText3).setVisibility(View.GONE);  
	    } 

	protected void displayText(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}
}
