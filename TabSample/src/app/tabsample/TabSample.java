package app.tabsample;

import com.readystatesoftware.viewbadger.BadgeView;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * @author Adil Soomro
 * 
 */
@SuppressWarnings("deprecation")
public class TabSample extends TabActivity {
	/** Called when the activity is first created. */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qqsetting);
		setTabs();
	}

	private void setTabs() {
		View view = addTab("Home", R.drawable.tab_message_selector,
				ArrowsActivity.class);

		TextView uncheckTextView = (TextView) view
				.findViewById(R.id.unchecked_msg_num);
		uncheckTextView.setVisibility(View.VISIBLE);
		BadgeView badgeView = new BadgeView(this, uncheckTextView);
		badgeView.setText("1");
		badgeView.show();

		addTab("Search", R.drawable.tab_contacts_selector,
				OptionsActivity.class);

		addTab("Home", R.drawable.tab_trend_selector, ArrowsActivity.class);
		addTab("Search", R.drawable.tab_setting_selector, OptionsActivity.class);
	}

	private View addTab(String labelId, int drawableId, Class<?> c) {
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

		View tabIndicator = LayoutInflater.from(this).inflate(
				R.layout.mainactivity_tab_item, getTabWidget(), false);

		ImageView icon = (ImageView) tabIndicator
				.findViewById(R.id.tab_item_image);
		icon.setImageResource(drawableId);

		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
		return tabIndicator;
	}
}