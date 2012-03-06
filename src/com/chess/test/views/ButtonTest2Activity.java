package com.chess.test.views;


import actionbarcompat.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.Toast;
import com.chess.test.R;

/**
 * ButtonTestActivity class
 *
 * @author alien_roger
 * @created at: 09.02.12 4:54
 */
public class ButtonTest2Activity extends ActionBarActivity {
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
		setContentView(R.layout.button_test2);
		findViewById(R.id.mainView).setBackgroundDrawable(new BackgroundChessDrawable(this));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
//			Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
				break;

			case R.id.menu_refresh:
				break;

			case R.id.menu_preferences:
				// TODO show popup list
				final CharSequence[] items = {"Red", "Green", "Blue"};

				new AlertDialog.Builder(this)
				.setTitle("Pick a color")
				.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
					}
				}).show();
//				AlertDialog alert = builder.create();
//				alert.show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}



}