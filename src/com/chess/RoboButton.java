package com.chess;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import com.chess.test.R;

public class RoboButton extends Button {
	private Context context;
	private String ttfName = "Regular";
//    private float density;

//	public RoboButton(Context context, AttributeSet attrs, int defStyle) {
//		super(context, attrs, defStyle);
//	}
//
//	public RoboButton(Context context) {
//		super(context);
//	}

	public RoboButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;


		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BlendTextView);

		final int N = a.getIndexCount();
		for (int i = 0; i < N; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
				case R.styleable.BlendTextView_ttf: {
					ttfName = a.getString(i);
				}
				break;
			}
		}
		init();

	}

	private void init() {
		Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-" + ttfName + ".ttf");
		setTypeface(font);
	}

	@Override
	public void setTypeface(Typeface tf) {
		super.setTypeface(tf);
	}

}
