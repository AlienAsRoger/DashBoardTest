package com.chess.dashboard_test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class CenteredButton extends FrameLayout implements View.OnClickListener, View.OnTouchListener {
//public class CenteredButton extends RelativeLayout {

	static final String TAG = "CenteredButton";
	private final int DEFAULT_WIDTH = 100;
	private final int DEFAULT_HEIGHT = 100;
	private Button button;
	private Drawable drawable;

	int mRadius;
	int mAnrType;
	CharSequence buttonText;

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
//		TypedArray dashboardItemText = context.obtainStyledAttributes(attrs, R.style.DashboardItemText);

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
//		LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LayoutParams buttonParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
//		buttonParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		button.setLayoutParams(buttonParams);
		button.setText(buttonText);
		button.setTextAppearance(getContext(), R.style.DashboardItemText);
//		button.setTextColor(Color.WHITE);
//		button.setTextSize(14);
//		button.set

//		button.setTextSize();
		button.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
//		button.setDuplicateParentStateEnabled(true);
//		button.setCompoundDrawablePadding(20);
		button.setBackgroundColor(Color.TRANSPARENT);
//		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		params.gravity = Gravity.CENTER;

		addView(button, params);
//		button.setTouchDelegate(getTouchDelegate());
		this.setTouchDelegate(button.getTouchDelegate());
		button.setClickable(true);
		button.setOnClickListener(this);
		button.setOnTouchListener(this);
		setClickable(true);
	}



	@Override
	public void setPressed(boolean pressed) {
		super.setPressed(pressed);
		button.setPressed(pressed);
	}

	/**
	 * Implement this method to handle touch screen motion events.
	 *
	 * @param event The motion event.
	 * @return True if the event was handled, false otherwise.
	 */
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				button.performClick();
				setPressed(true);
				button.refreshDrawableState();


				break;
			case MotionEvent.ACTION_UP:
				button.setPressed(false);
				button.refreshDrawableState();
				break;
			case MotionEvent.ACTION_MOVE:
				button.setPressed(true);
				button.refreshDrawableState();

				break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		switch (motionEvent.getAction()) {
			case MotionEvent.ACTION_DOWN:
				setPressed(true);
				refreshDrawableState();
				performClick();

				break;
			case MotionEvent.ACTION_UP:
				setPressed(false);
				refreshDrawableState();
				break;
		}
		return false;
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
//	@Override
//	public boolean performClick() {
//		refreshDrawableState();
//		if(!isButtonClicked)
//			button.performClick();
//		return super.performClick();
//	}

	@Override
	public void onClick(View view) {
		isButtonClicked = view.equals(button);
//		performClick();
	}


}
