package com.example.telephonestatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Telephoney extends Activity {

	ListView showView;
	String[] statusNames;
	ArrayList<String> statusValues = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		statusNames = getResources().getStringArray(R.array.statusNames);
		String[] simState = getResources().getStringArray(R.array.simState);
		String[] phoneType = getResources().getStringArray(R.array.phoneType);
		statusValues.add(tManager.getDeviceId());
		statusValues.add(tManager.getDeviceSoftwareVersion() != null 
				? tManager.getDeviceSoftwareVersion() : "δ֪");
			// ��ȡ������Ӫ�̴���
			statusValues.add(tManager.getNetworkOperator());
			// ��ȡ������Ӫ������
			statusValues.add(tManager.getNetworkOperatorName());
			// ��ȡ�ֻ���������
			statusValues.add(phoneType[tManager.getPhoneType()]);
			// ��ȡ�豸����λ��
			statusValues.add(tManager.getCellLocation() != null 
				? tManager.getCellLocation().toString() : "δ֪λ��");
			// ��ȡSIM���Ĺ���
			statusValues.add(tManager.getSimCountryIso());
			// ��ȡSIM�����к�
			statusValues.add(tManager.getSimSerialNumber());
			// ��ȡSIM��״̬
			statusValues.add(simState[tManager.getSimState()]);	
			 // ���ListView����
			showView = (ListView) findViewById(R.id.show);
			ArrayList<Map<String , String>> status = 
				new ArrayList<Map<String , String>>();
			//����statusValues���ϣ���statusNames��statusValues
			//�����ݷ�װ��List<Map<String , String>>������
			for(int i = 0 ; i < statusValues.size() ; i++)
			{
				HashMap<String, String> map = 
					new HashMap<String , String>();
				map.put("name" , statusNames[i]);
				map.put("value" , statusValues.get(i));
				status.add(map);
			}
			// ʹ��SimpleAdapter��װList����
			SimpleAdapter adapter = new SimpleAdapter(
				this  
				, status
				, R.layout.line
				, new String[]{"name" , "value"}
				, new int[]{R.id.name , R.id.value});
			// ΪListView����Adapter
			showView.setAdapter(adapter);
	}
}
