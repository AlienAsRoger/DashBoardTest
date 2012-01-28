package com.chess.dashboard_test;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class CenteredButton extends FrameLayout {

	private final int DEFAULT_WIDTH = 50;
	private final int DEFAULT_HEIGHT = 50;
	private Button button;

	public CenteredButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CenteredButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CenteredButton(Context context) {
		super(context);
		init();
	}

	private void init() {

	}

	public void setButton(Button button) {
		this.button = button;
		button.setTouchDelegate(this.getTouchDelegate());
//		button.get
//		findViewById(R.id.meter_Btn).setTouchDelegate(findViewById(R.id.linearLayout2).getTouchDelegate());
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (button == null)
			return;

		button.draw(canvas);
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int height = measureParam(heightMeasureSpec, DEFAULT_HEIGHT);
		int width = measureParam(widthMeasureSpec, DEFAULT_WIDTH);
		setMeasuredDimension(width, height);
	}

	private int measureParam(int valueMeasureSpec, int value) {
		switch (View.MeasureSpec.getMode(valueMeasureSpec)) {
		case MeasureSpec.EXACTLY:
			return MeasureSpec.getSize(valueMeasureSpec);
		case MeasureSpec.AT_MOST:
			return Math.min(value, MeasureSpec.getSize(valueMeasureSpec));
		default:
		case MeasureSpec.UNSPECIFIED:
			return value;
		}
	}
}
