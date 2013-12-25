package com.tted.palette;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Palette extends Activity {
	PaletteView paletteView = null;

	ListView picList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		paletteView = (PaletteView) findViewById(R.id.palette);
		picList = (ListView) findViewById(R.id.picList);
		final Bitmap b = ((BitmapDrawable) (getResources()
				.getDrawable(R.drawable.pic1))).getBitmap();
		ViewTreeObserver vto2 = paletteView.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			public void onGlobalLayout() {
				paletteView.getViewTreeObserver().removeGlobalOnLayoutListener(
						this);
				paletteView.setBgBitmap(b, paletteView.getWidth(),
						paletteView.getHeight());
			}
		});
		paletteView.setBgBitmap(b);
		ArrayList<HashMap<String, Object>> users = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 4; i++) {
			HashMap<String, Object> user = new HashMap<String, Object>();
			int picId = getResources().getIdentifier("pic" + (i + 1),
					"drawable", "com.tted.palette");
			user.put("img", picId);
			user.put("imgname", "picture_" + (i + 1));
			users.add(user);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, users,
				R.layout.mylistview, new String[] { "img", "imgname" },
				new int[] { R.id.img, R.id.imgname });
		picList.setAdapter(saImageItems);

		picList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				paletteView.clearAction();
				int picId = getResources().getIdentifier(
						"pic" + (position + 1), "drawable", "com.tted.palette");
				Bitmap bgBitmap = ((BitmapDrawable) (getResources()
						.getDrawable(picId))).getBitmap();
				Bitmap bgBitmap2 = null;
				Log.i("picId", picId + "picId");
				if (position == 3) {
					bgBitmap2 = ((BitmapDrawable) (getResources()
							.getDrawable(picId - 3))).getBitmap();
				} else {
					bgBitmap2 = ((BitmapDrawable) (getResources()
							.getDrawable(picId + 1))).getBitmap();
				}
				paletteView.setBgBitmap(bgBitmap, paletteView.getWidth(),
						paletteView.getHeight());
				paletteView.setBgBitmap(bgBitmap2);
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return paletteView.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "閫�嚭");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}