package net.blogjava.mobile.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class ClientSocketActivity extends Activity
{
	private BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		setContentView(R.layout.client_socket);
		if (!bluetooth.isEnabled())
		{
			finish();
			return;
		}
		// ��ʾѡ��һ��Ҫ���ӵķ�����
		Toast.makeText(this, "��ѡ��һ���豸��������", Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(this, DiscoveryActivity.class);
		// ��ת�������������豸�б���������ѡ��
		startActivityForResult(intent, 0x1);
	}

	// ѡ���˷�����֮���������
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode != 0x1)
		{
			return;
		}
		if (resultCode != RESULT_OK)
		{
			return;
		}
		final BluetoothDevice device = data
				.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		new Thread()
		{
			public void run()
			{
				// ����ѡ�е��豸
				connect(device);
			};
		}.start();
	}

	protected void connect(BluetoothDevice device)
	{
		BluetoothSocket socket = null;
		try
		{
			// ����һ��Socket���ӣ�ֻ��Ҫ��������ע��ʱ��UUID��
			socket = device.createRfcommSocketToServiceRecord(UUID
					.fromString("a62e35a0-a21b-11fe-8a39-08112010c888"));
			// �����豸
			socket.connect();
		}
		catch (IOException e)
		{

		} finally
		{
			if (socket != null)
			{
				try
				{
					socket.close();
				}
				catch (IOException e)
				{

				}
			}
		}
	}
}
