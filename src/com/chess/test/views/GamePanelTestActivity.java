package com.chess.test.views;

import actionbarcompat.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.chess.test.R;

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
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_panel);
		whiteCapturedPieces = (LinearLayout) findViewById(R.id.whiteCapturedPieces);
		blackCapturedPieces = (LinearLayout) findViewById(R.id.blackCapturedPieces);
		movesListView = (ListView) findViewById(R.id.movesListView);



		movesListView.setAdapter(new MovesAdapter());
	}

	private class MovesAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 0;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public Object getItem(int i) {
			return null;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public long getItemId(int i) {
			return 0;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			return null;  //To change body of implemented methods use File | Settings | File Templates.
		}
	}
}