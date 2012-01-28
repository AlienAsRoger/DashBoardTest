package com.chess.dashboard_test;

import actionbarcompat.ActionBarActivity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DashBoardTestActivity extends ActionBarActivity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		View mainView = findViewById(R.id.mainView);
		mainView.setBackgroundDrawable(new BackgroundChessDrawable(this));
		int padding = getResources().getDrawable(R.drawable.chess_cells).getIntrinsicWidth() / 2;
		mainView.setPadding(padding, padding, padding, padding);

		findViewById(R.id.playLive).setTouchDelegate(findViewById(R.id.playLiveFrame).getTouchDelegate());
		findViewById(R.id.playLiveFrame).setOnClickListener(this);
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
			Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
			break;

		case R.id.menu_settings:
			Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.playLiveFrame) {
			Toast.makeText(this, "playLiveFrame", Toast.LENGTH_SHORT).show();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();

		// Eliminates color banding
		getWindow().setFormat(PixelFormat.RGBA_8888);
	}
}