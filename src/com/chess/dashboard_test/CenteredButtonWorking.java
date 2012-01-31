package com.chess.dashboard_test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

//public class CenteredButton extends FrameLayout {
public class CenteredButtonWorking extends RelativeLayout {

	static final String TAG = "CenteredButton";
	private final int DEFAULT_WIDTH = 100;
	private final int DEFAULT_HEIGHT = 100;
	private Button button;
	private Drawable drawable;

	int mRadius;
	int mAnrType;
	CharSequence mLegend;

	static final int ANR_NONE = 0;
	static final int ANR_SHADOW = 1;
	static final int ANR_DROP = 2;

	private int mMaxChildWidth = 0;
	private int mMaxChildHeight = 0;


	public CenteredButtonWorking(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
//		attrs.
		init(context, attrs);
	}

	public CenteredButtonWorking(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public CenteredButtonWorking(Context context) {
		super(context);
//		init(context);
	}

	private void init(Context context, AttributeSet attrs) {
		// look up any layout-defined attributes
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CenteredButton);

		final int N = a.getIndexCount();
		for (int i = 0; i < N; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CenteredButton_buttonDrawable: {
				drawable = a.getDrawable(i);
//					mRadius = a.getDimensionPixelSize(attr, 0);
			}
				break;

			case R.styleable.CenteredButton_buttonText: {
				mLegend = a.getText(attr);
			}
				break;

			}
		}

		Log.i(TAG, "DraggableDot @ " + this + " : radius=" + mRadius + " legend='" + mLegend + "' anr=" + mAnrType);

		button = new Button(getContext());
		LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
//		buttonParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		button.setLayoutParams(buttonParams);
		button.setText(mLegend);
		button.setTextColor(Color.RED);
//		int left = 0;
//		int right = drawable.getIntrinsicWidth();
//		int top = 0;
//		int bottom = drawable.getIntrinsicHeight();
//		Rect bounds = new Rect(left, top, right, bottom);
//		drawable.setBounds(bounds);
		button.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
//		button.setCompoundDrawablePadding(20);
//		button.setBackgroundColor(Color.TRANSPARENT);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
//		params.gravity = Gravity.CENTER;
		addView(button, params);
//		setBackgroundColor(R.color.dashboard_button);
		setBackgroundResource(R.drawable.dashboard_button);
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

//		int left = 0;
//		int right = 50;
//		int top = 0;
//		int bottom = 50;
//		Rect bounds = new Rect(left, top, right, bottom);
//		drawable.setBounds(bounds);
//		button.draw(canvas);
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		final int count = getChildCount();

		// Calculate the number of visible children.

		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}
//			child.layout(left, top, right, bottom);
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int height = measureParam(heightMeasureSpec, DEFAULT_HEIGHT);
		int width = measureParam(widthMeasureSpec, DEFAULT_WIDTH);

		int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.AT_MOST);
		int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec),
				MeasureSpec.AT_MOST);

		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}

//			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

			mMaxChildWidth = Math.max(mMaxChildWidth, child.getMeasuredWidth());
			mMaxChildHeight = Math.max(mMaxChildHeight, child.getMeasuredHeight());
		}

		// Measure again for each child to be exactly the same size.

//		childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxChildWidth, MeasureSpec.EXACTLY);
//		childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxChildHeight, MeasureSpec.EXACTLY);
		childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxChildWidth, MeasureSpec.AT_MOST);
		childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxChildHeight, MeasureSpec.AT_MOST);

		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}

//			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
		}

		int w2 = resolveSize(mMaxChildWidth, widthMeasureSpec);
		int h2 = resolveSize(mMaxChildHeight, heightMeasureSpec);
//		setMeasuredDimension(w2, h2);

		setMeasuredDimension(width, height);
	}

	private int measureParam(int valueMeasureSpec, int value) {
		switch (MeasureSpec.getMode(valueMeasureSpec)) {
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
