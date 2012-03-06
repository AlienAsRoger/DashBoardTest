package com.chess.test.views;

import actionbarcompat.ActionBarActivity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
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
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_panel);
		whiteCapturedPieces = (LinearLayout) findViewById(R.id.whiteCapturedPieces);
		blackCapturedPieces = (LinearLayout) findViewById(R.id.blackCapturedPieces);
		movesListView = (ListView) findViewById(R.id.movesListView);


        List<String> itemList = new ArrayList<String>();

        itemList.add("1111");
        itemList.add("1111");
        itemList.add("1111");
        itemList.add("1111");

		movesListView.setAdapter(new MovesAdapter(itemList));

        addItems(whiteCapturedPieces,R.drawable.wp_six);
        addItems(whiteCapturedPieces,R.drawable.wp_six);
        addItems(whiteCapturedPieces,R.drawable.wp_six);
        addItems(whiteCapturedPieces,R.drawable.wp_six);
        addItems(whiteCapturedPieces,R.drawable.wp_six);

        addItems(blackCapturedPieces,R.drawable.wp_six);
        addItems(blackCapturedPieces,R.drawable.wp_six);
        addItems(blackCapturedPieces,R.drawable.wp_six);
        addItems(blackCapturedPieces,R.drawable.wp_six);
        addItems(blackCapturedPieces,R.drawable.wp_six);
	}

    private void addItems(LinearLayout viewGroup,int pieceId){
        Drawable[] layers = new Drawable[] {
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId)/*,
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId),
                getResources().getDrawable(pieceId)*/
        };


        LayerDrawable pieceDrawable = new LayerDrawable(layers);

        shiftLayer(pieceDrawable,1);
        shiftLayer(pieceDrawable,2);
        shiftLayer(pieceDrawable,3);
//        shiftLayer(pieceDrawable,4);



        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                            ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
//        imageView.setLayoutParams(params);
        imageView.setImageDrawable(pieceDrawable);
        FrameLayout frame = new FrameLayout(this);
        frame.addView(imageView);
        frame.setLayoutParams(params);
        viewGroup.addView(frame);
    }

    private int shiftSize = 30;
    private void shiftLayer(LayerDrawable pieceDrawable,int level){

        int l=level * shiftSize;
        int r=0;
        int t=0;
        int b=0;
        pieceDrawable.setLayerInset(level, l, t, r,  b);

    }



	private class MovesAdapter extends BaseAdapter{

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