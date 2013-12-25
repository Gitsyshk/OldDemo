package cy.rotateview;

import cy.rotateview.view.CYRotateView;
import cy.rotateview.view.CYRotateView.CYRotateViewListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements CYRotateViewListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		CYRotateView myView = (CYRotateView) findViewById(R.id.myView);
		myView.setRotateViewListener(this);
		View view1 = LayoutInflater.from(this).inflate(R.layout.view1, null);
		View view2 = LayoutInflater.from(this).inflate(R.layout.view2, null);
		View view3 = LayoutInflater.from(this).inflate(R.layout.view3, null);
		View view4 = LayoutInflater.from(this).inflate(R.layout.view4, null);

		myView.addView(view1);
		myView.addView(view2);
		myView.addView(view3);
		// 添加视图
		myView.addView(view4);
		// 事件旋转，默认手势旋转
		myView.rorateToNext();
		myView.rorateToPre();
		// 设置旋转角度，默认为90度
		myView.setRoateAngle(80);
		// 设置旋转方向，默认为横向
		// myView.setRorateDirecation(CYRorateView.RORATE_VERTICAL);
		// 设置旋转是否循环，默认循环
		myView.setIsNeedCirculate(false);
	}

	@Override
	public void getRorateCurrentView(int item) {
		Toast.makeText(MainActivity.this, "CurrentView"+item , Toast.LENGTH_SHORT).show();
	}


}
