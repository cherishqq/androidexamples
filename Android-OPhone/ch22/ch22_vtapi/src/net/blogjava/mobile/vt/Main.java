package net.blogjava.mobile.vt;

import oms.vt.VTController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//  ��ʼ��������Ƶ�绰�İ�ť
		Button button_start = (Button) findViewById(R.id.button_start);
		button_start.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				EditText inputTo = (EditText) Main.this
						.findViewById(R.id.input_to);
				//  ���Ҫ����ĵ绰��
				String number = inputTo.getText().toString();
				if (number == null || number.length() == 0)
				{
					printLog("������һ���绰��.");
					return;
				}

				// ��ʼ������Ƶ�绰
				Intent intent = new Intent(VTController.ACTION_LAUNCH_VTCALL);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
				intent.putExtra(VTController.EXTRA_CALL_OR_ANSWER, true);
				intent.putExtra(VTController.EXTRA_LAUNCH_MODE, 1);
				intent.putExtra(VTController.EXTRA_PHONE_URL, number);
				startActivity(intent);

				printLog("�Ѿ����еĺ��룺 " + number);
			}
		});

		// ��ʼ���Ҷϲ�����Ƶ�绰�İ�ť
		Button button_stop = (Button) findViewById(R.id.button_stop);
		button_stop.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				// �Ҷ���Ƶ�绰
				sendBroadcast(new Intent(VTController.ACTION_STOP_VTCALL));
				printLog("�Ҷ���Ƶ�绰");
			}
		});

	}

	public void printLog(String text)
	{
		if (logView == null)
			logView = (TextView) findViewById(R.id.textview_output);
		if (handlerLog == null)
			handlerLog = new Handler()
			{
				@Override
				public void handleMessage(Message msg)
				{
					super.handleMessage(msg);
					logView.append("\n\n" + msg.getData().getString("text"));
				}
			};
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		msg.setData(bundle);
		handlerLog.sendMessage(msg);
	}

	private Handler handlerLog = null;
	private TextView logView = null;
}
