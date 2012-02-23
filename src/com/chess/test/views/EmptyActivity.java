package com.chess.test.views;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.WindowManager;
import com.chess.test.R;

/**
 * EmptyActivity class
 *
 * @author alien_roger
 * @created at: 22.02.12 21:41
 */
public class EmptyActivity extends Activity {
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		// Eliminates color banding
		getWindow().setFormat(PixelFormat.RGBA_8888);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.mainView).setBackgroundDrawable(new BackgroundChessDrawable(this));

	}
}