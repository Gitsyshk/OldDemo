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
		//��ȡ�Ը�Activity������
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//��ʼ��Fragment
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//�������������Fragment��UI����������
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
}
