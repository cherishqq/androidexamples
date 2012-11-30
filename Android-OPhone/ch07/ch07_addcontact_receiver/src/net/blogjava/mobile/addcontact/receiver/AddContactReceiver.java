package net.blogjava.mobile.addcontact.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AddContactReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		if ("net.blogjava.mobile.ADDCONTACT".equals(intent.getAction()))
		{
			String message = "";
			Bundle bundle = intent.getExtras();
			if (bundle != null)
			{				
				message = "����:" + bundle.getString("name") + "\n";
				message += "�绰��" + bundle.getString("telephone") + "\n";
				message += "�����ʼ���" + bundle.getString("email") + "\n";
				message += "ͷ���ļ�·����" + bundle.getString("photoFilename") + "\n";
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			}
		}

	}
}
