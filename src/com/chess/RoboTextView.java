package com.chess;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.chess.test.R;

public class RoboTextView extends TextView {
	private Context context;
	private String ttfName;
//    private float density;

	public RoboTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RoboTextView(Context context) {
		super(context);
	}

	public RoboTextView(Context context, AttributeSet attrs) {
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
