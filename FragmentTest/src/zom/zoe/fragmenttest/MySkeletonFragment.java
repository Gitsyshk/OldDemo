package zom.zoe.fragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MySkeletonFragment extends Fragment{
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		//获取对父Activity的引用
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//初始化Fragment
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//创建、或者填充Fragment的UI，并返回它
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
}
