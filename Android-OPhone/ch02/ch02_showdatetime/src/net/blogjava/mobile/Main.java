package net.blogjava.mobile;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener
{
	private void showDialog(String title, String msg)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//  ���öԻ����ͼ��
		builder.setIcon(android.R.drawable.ic_dialog_info);
		//  ���öԻ���ı���
		builder.setTitle(title);
		//  ���öԻ�����ʾ����Ϣ
		builder.setMessage(msg);
		//  ���öԻ���İ�ť
		builder.setPositiveButton("ȷ��", null);
		//  ��ʾ�Ի���
		builder.create().show();
		Intent intent;
		
	}
	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
			case R.id.btnShowDate:
			{				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//  ��ʾ��ǰ����
				showDialog("��ǰ����", sdf.format(new Date()));
				break;
			}
			case R.id.btnShowTime: 
			{				
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				//  ��ʾ��ǰʱ��
				showDialog("��ǰʱ��", sdf.format(new Date()));
				break;
			} 
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//  ���������ť�Ķ���ʵ��
		Button btnShowDate = (Button) findViewById(R.id.btnShowDate);
		Button btnShowTime = (Button) findViewById(R.id.btnShowTime);
		//  Ϊ������ť��ӵ����¼�
		btnShowDate.setOnClickListener(this);
		btnShowTime.setOnClickListener(this);
		//setTitle(R.string.hello);
		
	}
}