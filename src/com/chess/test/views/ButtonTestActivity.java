package com.chess.test.views;


import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Spinner;
import com.chess.test.R;
import com.chess.test.views.adapters.ChessSpinnerAdapter;

/**
 * ButtonTestActivity class
 *
 * @author alien_roger
 * @created at: 09.02.12 4:54
 */
public class ButtonTestActivity extends Activity {
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		// Eliminates color banding
		getWindow().setFormat(PixelFormat.RGBA_8888);
	}
	
	private Spinner spinner;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_test);
		findViewById(R.id.mainView).setBackgroundDrawable(new BackgroundChessDrawable(this));
		spinner = (Spinner) findViewById(R.id.spinner);

		
		spinner.setAdapter(new ChessSpinnerAdapter(this,R.array.AIM));
	}



}