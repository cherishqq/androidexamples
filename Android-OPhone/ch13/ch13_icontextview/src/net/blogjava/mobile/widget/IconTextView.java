package net.blogjava.mobile.widget;

import net.blogjava.mobile.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class IconTextView extends TextView
{
	// ͼ����ԴID
	private int resourceId = 0;
	// iconλ�� 0��left 1:right
	private int iconPosition = 0;
	private Bitmap bitmap;

	public IconTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.IconTextView);
		
		resourceId = typedArray.getResourceId(R.styleable.IconTextView_iconSrc,
				0);
		if (resourceId > 0)
			bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
		iconPosition = typedArray.getInt(R.styleable.IconTextView_iconPosition,
				0);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if (bitmap != null)
		{

			// ��ԭͼ�Ͻ�ȡͼ��������ڱ�����Ϊ����ͼ��
			Rect src = new Rect();
			// ����ȡ��ͼ���Ƶ�bitmap�ϵ�Ŀ�������ڱ������븴��������ͬ
			Rect target = new Rect();
			src.left = 0;
			src.top = 0;
			src.right = bitmap.getWidth();
			src.bottom = bitmap.getHeight();

			int textHeight = (int) getTextSize();
			int left = 0;
			if (iconPosition == 1)
			{
				left = (int) getPaint().measureText(getText().toString()) + 2;
			}
			target.left = left;
			// ����ͼ���Ƶ�Ŀ¼����������ꡣ����TextView���ı����ݲ����Ǵ���˿�ʼ���Ƶģ���ˣ���Ҫ���¼������ͼ���������
			target.top = (int) ((getMeasuredHeight() - getTextSize()) / 2) + 1;
			target.bottom = target.top + textHeight;
			// Ϊ�˱�֤ͼ�񲻱��Σ���Ҫ����ͼ��߶����¼���ͼ��Ŀ��
			target.right = left
					+ (int) (textHeight * (bitmap.getWidth() / (float) bitmap
							.getHeight()));
			// ��ʼ����ͼ��
			canvas.drawBitmap(bitmap, src, target, getPaint());
			// ��TextView�е��ı������ƶ�һ���ľ��루�ڱ������ƶ���ͼ���ȼ�2�����ص��λ�ã�
			if (iconPosition == 0)
				canvas.translate(target.right + 2, 0);
		}
		super.onDraw(canvas);

	}

}
