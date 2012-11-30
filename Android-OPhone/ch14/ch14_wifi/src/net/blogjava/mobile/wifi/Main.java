package net.blogjava.mobile.wifi;

import java.net.Inet4Address;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Main extends Activity implements
		OnCheckedChangeListener
{
	private WifiManager wifiManager;
	private WifiInfo wifiInfo;
	private CheckBox chkOpenCloseWifiBox;

	private List<WifiConfiguration> wifiConfigurations;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifiInfo = wifiManager.getConnectionInfo();
		chkOpenCloseWifiBox = (CheckBox) findViewById(R.id.chkOpenCloseWifi);
		TextView tvWifiConfigurations = (TextView) findViewById(R.id.tvWifiConfigurations);
		TextView tvWifiInfo = (TextView) findViewById(R.id.tvWifiInfo);		
		chkOpenCloseWifiBox.setOnCheckedChangeListener(this);
  

		if (wifiManager.isWifiEnabled())
		{
			chkOpenCloseWifiBox.setText("Wifi�ѿ���");
			chkOpenCloseWifiBox.setChecked(true);
		}
		else
		{
			chkOpenCloseWifiBox.setText("Wifi�ѹر�");
			chkOpenCloseWifiBox.setChecked(false);
		}


		// ���Wifi��Ϣ
		StringBuffer sb = new StringBuffer();
		sb.append("Wifi��Ϣ\n");
		sb.append("MAC��ַ��" + wifiInfo.getMacAddress() + "\n");
		sb.append("������BSSID��" + wifiInfo.getBSSID() + "\n");

		sb.append("IP��ַ��int����" + wifiInfo.getIpAddress() + "\n");
		sb.append("IP��ַ��Hex����" + Integer.toHexString(wifiInfo.getIpAddress())
				+ "\n");
		sb.append("IP��ַ��" + ipIntToString(wifiInfo.getIpAddress()) + "\n");
		sb.append("����ID��" + wifiInfo.getNetworkId() + "\n");
		tvWifiInfo.setText(sb.toString());

		// �õ����úõ�����
		wifiConfigurations = wifiManager.getConfiguredNetworks();
		tvWifiConfigurations.setText("�����ӵ���������\n");
		for (WifiConfiguration wifiConfiguration : wifiConfigurations)
		{
			tvWifiConfigurations.setText(tvWifiConfigurations.getText()
					+ wifiConfiguration.SSID + "\n");
		}
	}

	private String ipIntToString(int ip)
	{
		try
		{
			byte[] bytes = new byte[4];
			bytes[0] = (byte) (0xff & ip);
			bytes[1] = (byte) ((0xff00 & ip) >> 8);
			bytes[2] = (byte) ((0xff0000 & ip) >> 16);
			bytes[3] = (byte) ((0xff000000 & ip) >> 24);
			return Inet4Address.getByAddress(bytes).getHostAddress();
		}
		catch (Exception e)
		{
			return "";
		}

	}



	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		if (isChecked)
		{
			wifiManager.setWifiEnabled(true);
			chkOpenCloseWifiBox.setText("Wifi�ѿ���");
		}
		else
		{
			wifiManager.setWifiEnabled(false);
			chkOpenCloseWifiBox.setText("Wifi�ѹر�");
		}
		
		
		
	}

}