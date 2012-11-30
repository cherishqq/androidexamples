package net.blogjava.mobile.gtalk;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.packet.Message;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChatRoom extends Activity implements OnMessageListener,
		OnClickListener
{
	private MessageReceiver mUpdateMessage;
	private String mContactAccount;
	private Thread mThread;
	private EditText metMessageList;
	private EditText metMessage;
	private Chat mChat;
	private LocationManager locationManager;

	@Override
	protected void onDestroy()
	{
		GTalk.mChattingContactMap.put(mContactAccount, false);
		mUpdateMessage.flag = false;
		mUpdateMessage.mCollector.cancel();
		super.onDestroy();
	}

	@Override
	public void onClick(View view)
	{
		String msg = metMessage.getText().toString();
		if (!"".equals(msg))
		{
			try
			{
				mChat.sendMessage(msg);
				metMessage.setText("");
				metMessageList.append("�ң�\n");
				metMessageList.append(msg + "\n\n");
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}

	}

	private void sendMsg(String from, String msg)
	{
		if ("#position".equals(msg.toLowerCase()) || "#λ��".equals(msg))
		{
			try
			{

				metMessageList.append("�Զ��ظ���"
						+ GTalk.mUtil.getLeftString(from, "@") + "\n");

				Criteria criteria = new Criteria();
				// �����õĶ�λЧ��
				criteria.setAccuracy(Criteria.ACCURACY_FINE);
				criteria.setAltitudeRequired(false);
				criteria.setBearingRequired(false);
				criteria.setCostAllowed(false);
				// ʹ��ʡ��ģʽ
				criteria.setPowerRequirement(Criteria.POWER_LOW);
				// ��õ�ǰ��λ���ṩ��
				String provider = locationManager.getBestProvider(criteria,
						true);
				// ��õ�ǰ��λ��
				Location location = locationManager
						.getLastKnownLocation(provider);
				// ��õ�ǰλ�õ�γ��
				Double latitude = location.getLatitude();
				// ��õ�ǰλ�õľ���
				Double longitude = location.getLongitude();
				mChat.sendMessage("GTalk�����ˣ�\n���ȣ�" + longitude + "\nγ�ȣ�"
						+ latitude + "\n\n");

			}
			catch (Exception e)
			{

			}
		}
		else
		{
			metMessageList.append(GTalk.mUtil.getLeftString(from, "/") + ":\n");
			metMessageList.append(msg + "\n\n");
		}

	}

	@Override
	public void processMessage(Message message)
	{
		sendMsg(message.getFrom(),message.getBody());
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatroom);
		metMessageList = (EditText) findViewById(R.id.etMessageList);
		metMessage = (EditText) findViewById(R.id.etMessage);
		Button btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(this);

		mContactAccount = getIntent().getStringExtra("contactAccount");
		String msg = getIntent().getStringExtra("msg");
		if (msg != null)
		{
			sendMsg(mContactAccount, msg);
		}

		setTitle(mContactAccount);
		mUpdateMessage = new MessageReceiver(mContactAccount);
		mUpdateMessage.setOnMessageListener(this);
		mThread = new Thread(mUpdateMessage);
		mThread.start();
		ChatManager chatmanager = GTalk.mConnection.getChatManager();
		mChat = chatmanager.createChat(mContactAccount, null);
		locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
	}

}