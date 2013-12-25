package com.zoe.demo33_appwidget02;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
			for (int i = 0; i < appWidgetIds.length; i++) {
				System.out.println(appWidgetIds[i]);
				Intent intent = new Intent(context,MainActivity.class);
				//����һ��pendingIntent
				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
				RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.example_appwidget);
				//Ϊ��ť���¼�������
				remoteViews.setOnClickPendingIntent(R.id.vidgetButtonId, pendingIntent);
				appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
			}
			super.onUpdate(context, appWidgetManager, appWidgetIds);	
	}
}
