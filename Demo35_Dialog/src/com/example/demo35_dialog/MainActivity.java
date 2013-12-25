package com.example.demo35_dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button button1, button2,button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.bn1);
		button2 = (Button) findViewById(R.id.bn2);
		button3 = (Button) findViewById(R.id.bn3);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("标题");
				builder.setMessage("Message");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setPositiveButton("queding",
						new OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				builder.setNegativeButton("quxiao",
						new OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("标题");
				CharSequence items[] = { "beijing", "shanghai" };
				builder.setItems(items,
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								

							}

						});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		});
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("标题");
				final CharSequence items[] = { "beijing", "shanghai" };
				builder.setSingleChoiceItems(items, -1, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						CharSequence select_item = items[which];
						Log.d("msg", "->>>>"+select_item);
						dialog.dismiss();
					}
				});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
