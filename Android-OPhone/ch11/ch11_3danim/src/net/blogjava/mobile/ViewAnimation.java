package net.blogjava.mobile;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class ViewAnimation extends Animation
{
	private int centerX, centerY;
	public ViewAnimation(int centerX, int centerY)
	{
		this.centerX = centerX;
		this.centerY = centerY;
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight)
	{
		super.initialize(width, height, parentWidth, parentHeight);
		setDuration(2500);
		setFillAfter(true);
		setInterpolator(new LinearInterpolator());
	}
	Camera camera = new Camera();
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t)
	{
		final Matrix matrix = t.getMatrix();
		camera.save();
		camera.translate(0.0f, 0.0f, (1300 - 1300.0f * interpolatedTime));
		camera.rotateY(360 * interpolatedTime);
		camera.rotateZ((360 * interpolatedTime));
		
		camera.getMatrix(matrix);
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		camera.restore();
	}
}