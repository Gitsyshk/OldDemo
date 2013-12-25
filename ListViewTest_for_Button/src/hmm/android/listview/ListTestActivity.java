package hmm.android.listview;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ListTestActivity extends Activity {
	 private List<Map<String, Object>> mData; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	mData = getData();  
       MyAdapter adapter = new MyAdapter(this); 
       ListView lv = (ListView)findViewById(android.R.id.list);
		lv.setAdapter(adapter);
		// ListView 中某项被选中
		/*lv.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> a, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				  Log.v("Select","ListItemSelected:"+ a.toString()+"查看："
		            + v.toString()+"位置:"+"ID："+ id);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			
			}
			
			
		});*/
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				  Log.v("Click","ListItemSelected:"+ a.toString()+"查看："
				            + v.toString()+"位置:"+"ID："+ id);
					Uri uri =Uri.parse("smsto://123456789");
		 			Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
		 			intent.putExtra("sms_body","实时温度");
		 			startActivity(intent);
			}
        	
        });
    }
    private List<Map<String, Object>> getData() { 
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
    	String[] title={"软件大楼","图书馆","文化街"};
    	String[] rating={"2","3","4"};
    	for(int i=0;i<3;i++)
    	{
    		Map<String, Object> map = new HashMap<String, Object>();    
        	map.put("title", title[i]);        
        	map.put("img", R.drawable.ic_launcher); 
        	map.put("rating",rating[i]);
        	list.add(map); 
    	}          
    	return list;     
    } 
	
	public final class ViewHolder{        
		public ImageView img;        
		public TextView title; 
		public RatingBar rating;
		public Button view_btn;
	  }                 
	public class MyAdapter extends BaseAdapter{    
		private LayoutInflater mInflater;      
		public MyAdapter(Context context){    
			this.mInflater = LayoutInflater.from(context);    
			}       
		public int getCount() {   
			// TODO Auto-generated method stub        
			return mData.size();      
			}          
		public Object getItem(int arg0) {       
			// TODO Auto-generated method stub        
			return null;        
			}        
		public long getItemId(int arg0) { 
			// TODO Auto-generated method stub     
			return 0;       
			}         
		public View getView(int position, View convertView, ViewGroup parent) {  
			ViewHolder holder = null;     
			if (convertView == null) {        
				holder=new ViewHolder();   
				convertView = mInflater.inflate(R.layout.list_item, null);      
				holder.img = (ImageView)convertView.findViewById(R.id.img);   
				holder.title = (TextView)convertView.findViewById(R.id.title);  
				holder.rating = (RatingBar)convertView.findViewById(R.id.rating);
				holder.view_btn = (Button) convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);    
				}else {                  
					holder = (ViewHolder)convertView.getTag();  
					}                                       
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));      
			holder.title.setText((String)mData.get(position).get("title")); 
			Log.v("R","R:"+(String) mData.get(position).get("rating"));
			//holder.rating.setRating((Float)mData.get(position).get("rating"));
			holder.view_btn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					showInfo();
				}});
			return convertView;      
			}             
	}
	 public void showInfo(){        
		 new AlertDialog.Builder(this)     
		 .setTitle("我的listview")    
		 .setMessage("介绍...")      
		 .setPositiveButton("确定", new DialogInterface.OnClickListener() {     
			 @Override           
			 public void onClick(DialogInterface dialog, int which) {    
				 
			 }         })         .show();               } 
}