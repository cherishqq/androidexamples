package net.blogjava.mobile;

import java.util.Random;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener
{
	private static final int MAX_PROGRESS = 100;
	private ProgressDialog progressDialog;
	private Handler progressHandler;
	private int progress;

	// ��ʾ���ȶԻ���style��ʾ���ȶԻ���ķ��
	private void showProgressDialog(int style)
	{
		progressDialog = new ProgressDialog(this);
		progressDialog.setIcon(R.drawable.wait);
		progressDialog.setTitle("���ڴ�������...");
		progressDialog.setMessage("���Ժ�...");		
		// ���ý��ȶԻ���ķ��
		progressDialog.setProgressStyle(style);
		// ���ý��ȶԻ���Ľ������ֵ
		progressDialog.setMax(MAX_PROGRESS);
		// ���ý��ȶԻ���ġ���ͣ����ť
		progressDialog.setButton("��ͣ", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				// ͨ��ɾ����Ϣ����ķ�ʽֹͣ��ʱ��
				progressHandler.removeMessages(1);
			}
		});
		// ���ý��ȶԻ���ġ�ȡ������ť
		progressDialog.setButton2("ȡ��", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				// ͨ��ɾ����Ϣ����ķ�ʽֹͣ��ʱ����ִ��
				progressHandler.removeMessages(1);
				// �ָ����ȳ�ʼֵ
				progress = 0;
				progressDialog.setProgress(0);
			}
		});
		progressDialog.show();
		progressHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				super.handleMessage(msg);
				// if (progressDialog.getProgress() >= MAX_PROGRESS)
				if (progress >= MAX_PROGRESS)
				{
					// ���ȴﵽ���ֵ���رնԻ���
					progress = 0;
					progressDialog.dismiss();
					
				}
				else
				{
					progress++;
					// �����ȵ���1
					progressDialog.incrementProgressBy(1);
					// ���������һ�ε������ȣ�����handleMessage��������ʱ��
					progressHandler.sendEmptyMessageDelayed(1,
							50 + new Random().nextInt(500));

				}
			}
		};

		// ���ý��ȳ�ʼֵ
		progress = (progress > 0) ? progress : 0;
		progressDialog.setProgress(progress);
		progressHandler.sendEmptyMessage(1);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.button1:
				// ��ʾ���������Ľ��ȶԻ���
				showProgressDialog(ProgressDialog.STYLE_HORIZONTAL);
				break;

			case R.id.button2:
				// ��ʾ��תָ����Ľ��ȶԻ���
				showProgressDialog(ProgressDialog.STYLE_SPINNER);
				break;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);

	}
}