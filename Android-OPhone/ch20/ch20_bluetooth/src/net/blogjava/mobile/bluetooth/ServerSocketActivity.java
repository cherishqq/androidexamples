package net.blogjava.mobile.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

public class ServerSocketActivity extends ListActivity
{
	// ��ʾ������������	
	public static final String PROTOCOL_SCHEME_RFCOMM = "btspp";
	private Handler handler = new Handler();
	// ȡ��Ĭ�ϵ�����������
	private BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
	// ����������
	private BluetoothServerSocket serverSocket;
	// �߳�-�����ͻ��˵�����
	private Thread serverWorker = new Thread()
	{
		public void run()
		{
			listen();
		};
	};

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		setContentView(R.layout.server_socket);
		if (!bluetooth.isEnabled())
		{
			finish();
			return;
		}
		//  ��ʼ�����ͻ��˵�����
		serverWorker.start();
	}

	protected void onDestroy()
	{
		super.onDestroy();
		shutdownServer();
	}

	protected void finalize() throws Throwable
	{
		super.finalize();
		shutdownServer();
	}

	// ֹͣ������ 
	private void shutdownServer()
	{
		new Thread()
		{
			public void run()
			{
				serverWorker.interrupt();
				if (serverSocket != null)
				{
					try
					{
						//  �رշ����� 
						serverSocket.close();
					}
					catch (IOException e)
					{
					}
					serverSocket = null;
				}
			};
		}.start();
	}

	public void onButtonClicked(View view)
	{
		shutdownServer();
	}

	protected void listen()
	{
		try
		{
			// �������������� ��UUID
			serverSocket = bluetooth
					.listenUsingRfcommWithServiceRecord(
							PROTOCOL_SCHEME_RFCOMM,
							UUID
									.fromString("a62e35a0-a21b-11fe-8a39-08112010c888"));
			// �ͻ��������б� 
			final List<String> lines = new ArrayList<String>();
			handler.post(new Runnable()
			{
				public void run()
				{
					lines.add("������������...");
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							ServerSocketActivity.this,
							android.R.layout.simple_list_item_1, lines);
					setListAdapter(adapter);
				}
			});
			//  ���ܿͻ��˵��������� 
			BluetoothSocket socket = serverSocket.accept();
			// ������������ 
			if (socket != null)
			{
				InputStream inputStream = socket.getInputStream();
				int read = -1;
				final byte[] bytes = new byte[2048];
				for (; (read = inputStream.read(bytes)) > -1;)
				{
					final int count = read;
					handler.post(new Runnable()
					{
						public void run()
						{
							StringBuilder b = new StringBuilder();
							for (int i = 0; i < count; ++i)
							{
								if (i > 0)
								{
									b.append(' ');
								}
								String s = Integer.toHexString(bytes[i] & 0xFF);
								if (s.length() < 2)
								{

									b.append('0');
								}
								b.append(s);
							}
							String s = b.toString();
							lines.add(s);
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(
									ServerSocketActivity.this,
									android.R.layout.simple_list_item_1, lines);
							setListAdapter(adapter);
						}
					});
				}
			}
		}
		catch (IOException e)
		{
		} finally
		{

		}
	}
}
