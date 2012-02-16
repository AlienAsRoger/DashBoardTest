package com.chess.views;


import android.app.Activity;
import android.os.Bundle;
import com.chess.R;

/**
 * ButtonTestActivity class
 *
 * @author alien_roger
 * @created at: 09.02.12 4:54
 */
public class ButtonTestActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_test);
		findViewById(R.id.mainView).setBackgroundDrawable(new BackgroundChessDrawable(this));

	}
}