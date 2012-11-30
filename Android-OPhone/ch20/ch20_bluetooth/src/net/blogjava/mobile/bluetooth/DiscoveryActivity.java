package net.blogjava.mobile.bluetooth;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DiscoveryActivity extends ListActivity
{
	private Handler _handler = new Handler();
	// �����������������
	private BluetoothAdapter bluetoothAdapter = BluetoothAdapter
			.getDefaultAdapter();
	// �����洢�������������豸
	private List<BluetoothDevice> bluetoothDevices = new ArrayList<BluetoothDevice>();
	// �Ƿ��������
	private volatile boolean discoveryFinished;
	private Runnable discoveryWorkder = new Runnable()
	{
		public void run()
		{
			// ��ʼ����
			bluetoothAdapter.startDiscovery();
			while(true)
			{
				if (discoveryFinished)
				{
					break;
				}
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
				}
			}
		}
	};
	//  ���������豸���ʱ����
	private BroadcastReceiver foundReceiver = new BroadcastReceiver()
	{
		public void onReceive(Context context, Intent intent)
		{
			//  ��������������
			BluetoothDevice device = intent
					.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		    //  �������ӵ��豸�б���
			bluetoothDevices.add(device);
			//  ��ʾ�б�
			showDevices();
		}
	};
	//  �������ʱ����
	private BroadcastReceiver discoveryReceiver = new BroadcastReceiver()
	{

		@Override
		public void onReceive(Context context, Intent intent)
		{
			//  ж��ע��Ľ����� 
			unregisterReceiver(foundReceiver);
			unregisterReceiver(this);
			discoveryFinished = true;
		}
	};

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		setContentView(R.layout.discovery);
		//  �������������û�д򿪣���ر�Activity
		if (!bluetoothAdapter.isEnabled())
		{
			finish();
			return;
		}
		//  ע������� 
		IntentFilter discoveryFilter = new IntentFilter(
				BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(discoveryReceiver, discoveryFilter);
		IntentFilter foundFilter = new IntentFilter(
				BluetoothDevice.ACTION_FOUND);
		registerReceiver(foundReceiver, foundFilter);
		//  ��ʾһ���Ի���,�������������豸 
		SamplesUtils.indeterminate(DiscoveryActivity.this, _handler,
				"����ɨ��...", discoveryWorkder, new OnDismissListener()
				{
					public void onDismiss(DialogInterface dialog)
					{

						for (; bluetoothAdapter.isDiscovering();)
						{

							bluetoothAdapter.cancelDiscovery();
						}

						discoveryFinished = true;
					}
				}, true);
	}

	//  ��ʾ�����豸�б�
	protected void showDevices()
	{
		List<String> list = new ArrayList<String>();
		for (int i = 0, size = bluetoothDevices.size(); i < size; ++i)
		{
			StringBuilder b = new StringBuilder();
			BluetoothDevice d = bluetoothDevices.get(i);
			b.append(d.getAddress());
			b.append('\n');
			b.append(d.getName());
			String s = b.toString();
			list.add(s);
		}

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		_handler.post(new Runnable()
		{
			public void run()
			{

				setListAdapter(adapter);
			}
		});
	}

	protected void onListItemClick(ListView l, View v, int position, long id)
	{

		Intent result = new Intent();
		result.putExtra(BluetoothDevice.EXTRA_DEVICE, bluetoothDevices
				.get(position));
		setResult(RESULT_OK, result);
		finish();
	}
}
