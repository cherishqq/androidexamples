package net.blogjava.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Main extends Activity implements Runnable
{
	// 5��TextView����ɫֵ
	private int[] colors = new int[]
	{ 0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFF00FF, 0xFF00FFFF };
	// ÿһ����ɫ����һ����ɫ�����������һ����ɫ����һ����ɫ�ǵ�һ����ɫ���൱��ѭ������
	private int[] nextColorPointers = new int[]
	{ 1, 2, 3, 4, 0 };
	private View[] views; // ����5��TextView
	private int currentColorPointer = 0; // ��ǰ��ɫ������ָ�룩
	private Handler handler;

	@Override
	public void run()
	{
		int nextColorPointer = currentColorPointer;
		for (int i = views.length - 1; i >= 0; i--)
		{
			// ���õ�ǰTextView�ı�����ɫ
			views[i]
					.setBackgroundColor(colors[nextColorPointers[nextColorPointer]]);
			// �����һ��TextView�ı�����ɫֵ��������ָ�룩
			nextColorPointer = nextColorPointers[nextColorPointer];
		}
		currentColorPointer++;
		if (currentColorPointer == 5)
			currentColorPointer = 0;
		handler.postDelayed(this, 300); // ��300����ѭ��һ��
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ʼ��views����
		views = new View[]
		{ findViewById(R.id.textview5), findViewById(R.id.textview4),
				findViewById(R.id.textview3), findViewById(R.id.textview2),
				findViewById(R.id.textview1) };
		handler = new Handler();
		handler.postDelayed(this, 300); // ��300����ѭ��һ��
	
	}
}
