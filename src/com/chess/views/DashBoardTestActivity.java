package com.chess.views;

import actionbarcompat.ActionBarActivityMy;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.chess.R;

public class DashBoardTestActivity extends ActionBarActivityMy implements OnClickListener {
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		// Eliminates color banding
		getWindow().setFormat(PixelFormat.RGBA_8888);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main3);

		View mainView = findViewById(R.id.mainView);
		mainView.setBackgroundDrawable(new BackgroundChessDrawable(this));
		int padding = getResources().getDrawable(R.drawable.chess_cell).getIntrinsicWidth() / 2;
		mainView.setPadding(padding, padding, padding, padding);

//		findViewById(R.id.playLive).setTouchDelegate(findViewById(R.id.playLiveFrame).getTouchDelegate());
		findViewById(R.id.playLiveFrame).setOnClickListener(this);
		findViewById(R.id.playOnlineFrame).setOnClickListener(this);

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
			startActivity(new Intent(this,ButtonTestActivity.class));
		} else if (v.getId() == R.id.playOnlineFrame) {
			Toast.makeText(this, "playOnlineFrame", Toast.LENGTH_SHORT).show();
		}
		// TODO Auto-generated method stub

	}
/*
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		// Eliminates color banding
		getWindow().setFormat(PixelFormat.RGBA_8888);
	}*/
}