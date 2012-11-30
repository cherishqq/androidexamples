package net.blogjava.mobile.livewallpapers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class LiveWallpaperPainting extends Thread
{

	private SurfaceHolder surfaceHolder;
	private Context context;

	private boolean wait;
	private boolean run;

	/* �ߴ�Ͱ뾶  */
	private int width;
	private int height;
	private int radius;

	/** ������ */
	private List<TouchPoint> points;

	/* ʱ��켣 */
	private long previousTime;

	public LiveWallpaperPainting(SurfaceHolder surfaceHolder, Context context,
			int radius)
	{

		this.surfaceHolder = surfaceHolder;
		this.context = context;
		// ֱ��surface����������ʾʱ�ſ�ʼ����
		this.wait = true;
		// ��ʼ��������
		this.points = new ArrayList<TouchPoint>();
		// ��ʼ���뾶
		this.radius = radius;
	}

	//  ͨ������ҳ����Ըı�Բ�İ뾶
	public void setRadius(int radius)
	{
		this.radius = radius;
	}

    // ��ͣʵʱ��ֽ�Ķ���
	public void pausePainting()
	{
		this.wait = true;
		synchronized (this)
		{
			this.notify();
		}
	}

	//  �ָ���ʵʱ��ֽ�ϻ��Ʋ�ɫʵ��Բ	 
	public void resumePainting()
	{
		this.wait = false;
		synchronized (this)
		{
			this.notify();
		}
	}

    //  ֹͣ��ʵʱ��ֽ�ϻ��Ʋ�ɫʵ��Բ
	public void stopPainting()
	{
		this.run = false;
		synchronized (this)
		{
			this.notify();
		}
	}

	@Override
	public void run()
	{
		this.run = true;
		Canvas canvas = null;
		while (run)
		{
			try
			{
				canvas = this.surfaceHolder.lockCanvas(null);
				synchronized (this.surfaceHolder)
				{
					doDraw(canvas);
				}
			} finally
			{
				if (canvas != null)
				{
					this.surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			// �������Ҫ��������ͣ����
			synchronized (this)
			{
				if (wait)
				{
					try
					{
						wait();
					}
					catch (Exception e)
					{
					}
				}
			}
		}
	}

	public void setSurfaceSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		synchronized (this)
		{
			this.notify();
		}
	}

	public void doTouchEvent(MotionEvent event)
	{
		synchronized (this.points)
		{
			
			int color = new Random().nextInt(Integer.MAX_VALUE);
			points.add(new TouchPoint((int) event.getX(), (int) event.getY(),
					color, Math.min(width, height) / this.radius));
		}
		this.wait = false;
		synchronized (this)
		{
			notify();
		}
	}

	private void doDraw(Canvas canvas)
	{
		long currentTime = System.currentTimeMillis();
		long elapsed = currentTime - previousTime;
		if (elapsed > 20)
		{
			BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.background);
			//  ����ʵʱ��ֽ�ı���ͼ
			canvas.drawBitmap(bitmapDrawable.getBitmap(), 0, 0, new Paint());
			
			// ���ƴ�����
			Paint paint = new Paint();
			List<TouchPoint> pointsToRemove = new ArrayList<TouchPoint>();
			synchronized (this.points)
			{
				for (TouchPoint point : points)
				{
					paint.setColor(point.color);
					point.radius -= elapsed / 20;
					if (point.radius <= 0)
					{
						pointsToRemove.add(point);
					}
					else
					{
						canvas
								.drawCircle(point.x, point.y, point.radius,
										paint);
					}
				}
				points.removeAll(pointsToRemove);
			}
			previousTime = currentTime;
			if (points.size() == 0)
			{
				wait = true;
			}
		}
	}

	class TouchPoint
	{

		int x;
		int y;
		int color;
		int radius;

		public TouchPoint(int x, int y, int color, int radius)
		{
			this.x = x;
			this.y = y;
			this.radius = radius;
			
			this.color = color;
		}

	}

}
