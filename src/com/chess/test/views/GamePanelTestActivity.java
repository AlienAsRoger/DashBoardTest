package com.chess.test.views;

import actionbarcompat.ActionBarActivity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.chess.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * GamePanelTestActivity class
 *
 * @author alien_roger
 * @created at: 06.03.12 7:39
 */
public class GamePanelTestActivity extends ActionBarActivity {

	private LinearLayout whiteCapturedPieces;
	private LinearLayout blackCapturedPieces;
	private ListView movesListView;

	private static final int ITEMS_CNT = 6;
	private static final float WEIGHT_SUM = 16f;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_panel);
		whiteCapturedPieces = (LinearLayout) findViewById(R.id.whiteCapturedPieces);
		blackCapturedPieces = (LinearLayout) findViewById(R.id.blackCapturedPieces);
//		whiteCapturedPieces.setWeightSum(WEIGHT_SUM);
//		blackCapturedPieces.setWeightSum(WEIGHT_SUM);
		movesListView = (ListView) findViewById(R.id.movesListView);


		List<String> itemList = new ArrayList<String>();

		itemList.add("1111");
		itemList.add("1111");
		itemList.add("1111");
		itemList.add("1111");

		movesListView.setAdapter(new MovesAdapter(itemList));


		addItems(whiteCapturedPieces, R.drawable.captured_wq, 1, 0.6f);
		addItems(whiteCapturedPieces, R.drawable.captured_wr, 2, 1.2f);
		addItems(whiteCapturedPieces, R.drawable.captured_wb, 2, 1.2f);
		addItems(whiteCapturedPieces, R.drawable.captured_wn, 2, 1.2f);
		addItems(whiteCapturedPieces, R.drawable.captured_wp, 8, 1.2f);
		addItems(whiteCapturedPieces, R.drawable.captured_wk, 1, 0.6f);

		addItems(blackCapturedPieces, R.drawable.captured_bq, 1, 10.0f);
		addItems(blackCapturedPieces, R.drawable.captured_br, 2, 2.0f);
		addItems(blackCapturedPieces, R.drawable.captured_bb, 2, 2.0f);
		addItems(blackCapturedPieces, R.drawable.captured_bn, 2, 2.0f);
//		addItems(blackCapturedPieces, R.drawable.captured_bp, 8, 8.0f);
//		addItems(blackCapturedPieces, R.drawable.captured_bk, 1, 1.0f);
	}


	private void addItems(LinearLayout viewGroup, int pieceId, int layersCnt, float itemWeight) {

		Drawable[] layers = new Drawable[layersCnt];

		for (int j = 0; j < layersCnt; j++) {
			layers[j] = getResources().getDrawable(pieceId);
		}

		LayerDrawable pieceDrawable = new LayerDrawable(layers);

		for (int i = 0; i < layersCnt; i++) {
			shiftLayer(pieceDrawable, i);
		}

		ImageView imageView = new ImageView(this);
		imageView.setAdjustViewBounds(false);
		imageView.setScaleType(ImageView.ScaleType.CENTER);


		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		params.weight = itemWeight;
		params.gravity = Gravity.LEFT;

		imageView.setImageDrawable(pieceDrawable);
		imageView.setLayoutParams(imageParams);
		viewGroup.setWeightSum(16f);

		// put iamge inside frame to get left gravity
		FrameLayout frame = new FrameLayout(this);
		frame.addView(imageView);
		frame.setLayoutParams(params);

		viewGroup.addView(frame);
		viewGroup.setGravity(Gravity.LEFT);
	}

	private int shiftSize = 20;

	private void shiftLayer(LayerDrawable pieceDrawable, int level) {

		int l = level * shiftSize;
		int r = 0;
		int t = 0;
		int b = 0;
		pieceDrawable.setLayerInset(level, l, t, r, b);
		((BitmapDrawable) pieceDrawable.getDrawable(level)).setGravity(Gravity.LEFT | Gravity.TOP);
	}


	private class MovesAdapter extends BaseAdapter {

		private List<String> itemList;

		public MovesAdapter(List<String> itemList) {
			this.itemList = itemList;
		}

		@Override
		public int getCount() {
			return itemList.size();  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public Object getItem(int i) {
			return itemList.get(i);  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public long getItemId(int i) {
			return i;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			TextView textView = new TextView(getApplicationContext());
			textView.setText(itemList.get(i));
			return textView;  //To change body of implemented methods use File | Settings | File Templates.
		}
	}
}