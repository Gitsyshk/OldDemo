package exmaple.movment;

import android.app.Activity;
import android.os.Bundle;

public class Movement extends Activity{
	public void onDreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new MovementView(this));
	}

}
