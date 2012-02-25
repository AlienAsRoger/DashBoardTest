package com.chess.utils;

import android.content.Context;
import android.view.View;
import com.chess.test.R;
import com.chess.test.views.BackgroundChessDrawable;

/**
 * CommonUtils class
 *
 * @author alien_roger
 * @created at: 25.02.12 9:27
 */
public class CommonUtils {
	
	public static void setBackground(View mainView, Context context){
		mainView.setBackgroundDrawable(new BackgroundChessDrawable(context));

//		int padding = getResources().getDrawable(R.drawable.chess_cell).getIntrinsicWidth() / 2;
		int paddingTop = (int)context.getResources().getDimension(R.dimen.dashboard_padding_top);
		int paddingLeft = (int)context.getResources().getDimension(R.dimen.dashboard_padding_side);
		int paddingRight = (int)context.getResources().getDimension(R.dimen.dashboard_padding_side);
		int paddingBot = (int)context.getResources().getDimension(R.dimen.dashboard_padding_bot);
		mainView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBot);
	}
}
