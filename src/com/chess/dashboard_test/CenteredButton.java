package com.chess.dashboard_test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class CenteredButton extends FrameLayout implements View.OnClickListener {
//public class CenteredButton extends RelativeLayout {

	static final String TAG = "CenteredButton";
	private final int DEFAULT_WIDTH = 100;
	private final int DEFAULT_HEIGHT = 100;
	private Button button;
	private Drawable drawable;

	int mRadius;
	int mAnrType;
	CharSequence buttonText;

	static final int ANR_NONE = 0;
	static final int ANR_SHADOW = 1;
	static final int ANR_DROP = 2;

	private int mMaxChildWidth = 0;
	private int mMaxChildHeight = 0;

	public CenteredButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initFromAttr(context, attrs);
	}

	public CenteredButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initFromAttr(context, attrs);
	}

	public CenteredButton(Context context) {
		super(context);
	}

	private void initFromAttr(Context context, AttributeSet attrs) {
		// look up any layout-defined attributes
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CenteredButton);

		final int N = a.getIndexCount();
		for (int i = 0; i < N; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CenteredButton_buttonDrawable: {
				drawable = a.getDrawable(i);
			}
				break;

			case R.styleable.CenteredButton_buttonText: {
				buttonText = a.getText(attr);
			}
				break;

			}
		}

		Log.i(TAG, "DraggableDot @ " + this + " : radius=" + mRadius + " legend='" + buttonText + "' anr=" + mAnrType);

		button = new Button(getContext());
		LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		buttonParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		button.setLayoutParams(buttonParams);
		button.setText(buttonText);
		button.setTextColor(Color.WHITE);
		button.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
//		button.setDuplicateParentStateEnabled(true);
//		button.setCompoundDrawablePadding(20);
		button.setBackgroundColor(Color.TRANSPARENT);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;

		addView(button, params);
		button.setTouchDelegate(getTouchDelegate());
		this.setTouchDelegate(button.getTouchDelegate());
		button.setClickable(true);
		button.setOnClickListener(this);
		setClickable(true);
	}

	@Override
	public void setPressed(boolean pressed) {
		super.setPressed(pressed);
		button.setPressed(pressed);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (button == null)
			return;
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

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

			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

			mMaxChildWidth = Math.max(mMaxChildWidth, child.getMeasuredWidth());
			mMaxChildHeight = Math.max(mMaxChildHeight, child.getMeasuredHeight());
		}

		// Measure again for each child to be exactly the same size.

		childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxChildWidth, MeasureSpec.EXACTLY);
		childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxChildHeight, MeasureSpec.EXACTLY);

		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}

			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
		}

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

	private boolean isButtonClicked;
	@Override
	public boolean performClick() {
		if(!isButtonClicked)
			button.performClick();
		return super.performClick();
	}

	@Override
	public void onClick(View view) {
//		callOnClick();
		if(view.equals(button)){
			isButtonClicked = true;
		}else
			isButtonClicked = false;
		performClick();

		//To change body of implemented methods use File | Settings | File Templates.
	}
}
