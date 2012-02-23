package com.chess.test.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.chess.test.R;

public class BackgroundChessDrawable extends Drawable {

	private Paint paint;
	private Paint[] shinePaints;
	private Path[] shinePaths;
	private Rect rect;
	private float density = 1;

	private Drawable image;

	private final int DEFAULT_WIDTH = 50;
	private final int DEFAULT_HEIGHT = 50;

	private int cellSize = 50;
	private int cellColor1 = 0xFF494846;
	private int cellColor2 = 0xFF51504d;
	private GradientDrawable gradienView;
	private boolean pathsInitiated;

	private final Context context;
	private float width;
	private float height;

	private final int blackColor = 0x48000000;
	private int screenOrientation;

	public BackgroundChessDrawable(Context context) {
		this.context = context;
		init();
	}

	private void init() {
		density = context.getResources().getDisplayMetrics().density;
		cellSize *= (int) density;
		paint = new Paint(/* Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG */);
		shinePaints = new Paint[4];
		for (int i = 0; i < shinePaints.length; i++) {
			shinePaints[i] = new Paint();
			shinePaints[i].setDither(true);
			shinePaints[i].setAntiAlias(true);
		}

		shinePaths = new Path[4];
		rect = new Rect(0, 0, 5, 5);

		paint.setStrokeWidth(1.0f);
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);
		paint.setDither(true);
		paint.setAntiAlias(true);

		width = context.getResources().getDisplayMetrics().widthPixels;
		height = context.getResources().getDisplayMetrics().heightPixels;

		image = context.getResources().getDrawable(R.drawable.chess_back);
		int opacity = context.getResources().getInteger(R.integer.fade_opacity);
//		blackColor ^= (opacity * 0xFF / 100) << 32;
		image.setBounds(0, 0, (int) width, (int) height);

		image.setDither(true);

		screenOrientation = context.getResources().getConfiguration().orientation;
	}

	public void setCellColor1(int color) {
		cellColor1 = color;
	}

	public void setCellColor2(int color) {
		cellColor2 = color;
	}

	private float[] borders;
	private static final int LEFT = 0;
	private static final int TOP = 1;
	private static final int RIGHT = 2;
	private static final int BOTTOM = 3;

	private void createShinePath() {
		borders = new float[4];
		if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
			borders[LEFT] = height / 4;
			borders[1] = width / 4;
			borders[2] = width * 3 / 4;
			borders[BOTTOM] = height * 3 / 4;
		} else if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
			borders[LEFT] = height / 4;
			borders[1] = width / 4;
			borders[2] = width * 3 / 4;
//            borders[BOTTOM] = height * 3 / 4;
//			borders[BOTTOM] = 0;
			borders[BOTTOM] = -5;
//			borders[BOTTOM] = height *1/ 50;
		} else { // SQUARE
			borders[LEFT] = height / 4;
			borders[1] = height / 4;
			borders[2] = height / 4;
			borders[BOTTOM] = height / 4;
		}
		for (int i = 0; i < shinePaints.length; i++) {
			shinePaths[i] = new Path();
			setCoordinates(shinePaths[i], 0, (int) width, 0, (int) height);
			switch (i) {
				case LEFT:
//				shinePaints[i].setShader(new LinearGradient(0, 0, 0, borders[i], blackColor, 0x0000000,
//						Shader.TileMode.CLAMP));
					break;
				case 1:
//				shinePaints[i].setShader(new LinearGradient(0, 0, borders[i], 0, blackColor, 0x0000000,
//						Shader.TileMode.CLAMP));
					break;
				case 2:
//				shinePaints[i].setShader(new LinearGradient(width, 0, borders[i], 0, blackColor, 0x0000000,
//						Shader.TileMode.CLAMP));
					break;
				case BOTTOM:
					shinePaints[i].setShader(
							new LinearGradient(0, height, 0, borders[i], blackColor, 0x00000000,
							Shader.TileMode.CLAMP));
					break;
				default:
					break;
			}
		}
		pathsInitiated = true;
	}

	private void setCoordinates(Path path, int x0, int x1, int y0, int y1) {
		path.moveTo(x0, y0);
		path.lineTo(x0, y1);
		path.lineTo(x1, y1);
		path.lineTo(x1, y0);
		path.close();
	}

	@Override
	public void draw(Canvas canvas) {
		if (!pathsInitiated) {
			createShinePath();
		}
		canvas.save();

		image.draw(canvas);
		canvas.restore();

		for (int i = 0; i < shinePaints.length; i++) {
//            canvas.drawPath(shinePaths[i], shinePaints[i]);
			canvas.drawPath(shinePaths[BOTTOM], shinePaints[BOTTOM]);
		}
	}

	@Override
	public int getOpacity() {
		return PixelFormat.OPAQUE;
	}

	@Override
	public void setAlpha(int alpha) {
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
	}
}
