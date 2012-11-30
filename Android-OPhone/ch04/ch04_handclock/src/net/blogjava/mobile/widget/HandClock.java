package net.blogjava.mobile.widget;

import java.util.Calendar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class HandClock extends View implements Runnable
{
	private int clockImageResourceId;
	private Bitmap bitmap;
	private float scale;
	private float handCenterWidthScale;
	private float handCenterHeightScale;
	private int minuteHandSize;
	private int hourHandSize;
	private Handler handler = new Handler();

	@Override
	public void run()
	{
		// ���»���View
		invalidate();
		// �������ö�ʱ������60������run����
		handler.postDelayed(this, 60 * 1000);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ����ͼ���ʵ�ʴ�С�ȱ�������View�Ĵ�С
		setMeasuredDimension((int) (bitmap.getWidth() * scale), (int) (bitmap
				.getHeight() * scale));
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint paint = new Paint();
		Rect src = new Rect();
		Rect target = new Rect();
		src.left = 0;
		src.top = 0;
		src.right = bitmap.getWidth();
		src.bottom = bitmap.getHeight();

		target.left = 0;
		target.top = 0;
		target.bottom = (int) (src.bottom * scale);
		target.right = (int) (src.right * scale);
		// ������ͼ��
		canvas.drawBitmap(bitmap, src, target, paint);
		// ����������ĵ�ĺ�������
		float centerX = bitmap.getWidth() * scale * handCenterWidthScale;
		float centerY = bitmap.getHeight() * scale * handCenterHeightScale;
		// ��������ĵ㻭һ���뾶Ϊ5��ʵ��ԲȦ
		canvas.drawCircle(centerX, centerY, 5, paint);
		// ���÷���Ϊ3�����ش�
		paint.setStrokeWidth(3);
		Calendar calendar = Calendar.getInstance();
		int currentMinute = calendar.get(Calendar.MINUTE);
		int currentHour = calendar.get(Calendar.HOUR);
		// ��������ʱ��ĽǶ�
		double minuteRadian = Math
				.toRadians((360 - ((currentMinute * 6) - 90)) % 360);
		double hourRadian = Math.toRadians((360 - ((currentHour * 30) - 90))
				% 360 - (30 * currentMinute / 60));
		// �ڱ����ϻ�����
		canvas.drawLine(centerX, centerY, (int) (centerX + minuteHandSize
				* Math.cos(minuteRadian)), (int) (centerY - minuteHandSize
				* Math.sin(minuteRadian)), paint);
		// ����ʵ��Ϊ4�����ش�
		paint.setStrokeWidth(4);
		// �ڱ����ϻ�����
		canvas.drawLine(centerX, centerY, (int) (centerX + hourHandSize
				* Math.cos(hourRadian)), (int) (centerY - hourHandSize
				* Math.sin(hourRadian)), paint);
	}

	public HandClock(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// ��ȡ��Ӧ������ֵ
		clockImageResourceId = attrs.getAttributeResourceValue(null,
				"clockImageSrc", 0);
		if (clockImageResourceId > 0)
			bitmap = BitmapFactory.decodeResource(getResources(),
					clockImageResourceId);
		scale = attrs.getAttributeFloatValue(null, "scale", 1);
		handCenterWidthScale = attrs.getAttributeFloatValue(null,
				"handCenterWidthScale", bitmap.getWidth() / 2);
		handCenterHeightScale = attrs.getAttributeFloatValue(null,
				"handCenterHeightScale", bitmap.getHeight() / 2);
		//  �ڶ�ȡ�����ʱ�볤�Ⱥ󣬽���ֵ��ͼ������ű�����������
		minuteHandSize = (int) (attrs.getAttributeIntValue(null,
				"minuteHandSize", 0) * scale);
		hourHandSize = (int) (attrs.getAttributeIntValue(null, "hourHandSize",
				0) * scale);
		int currentSecond = Calendar.getInstance().get(Calendar.SECOND);
		//  ����ʱ������0��ʱִ��run����
		handler.postDelayed(this, (60 - currentSecond) * 1000);
	}

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		//  ɾ���ص���
		handler.removeCallbacks(this);
	}

}
