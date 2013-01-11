package com.android.util;

import android.content.Context;
import android.telephony.TelephonyManager;

public class NetworkUtils {
	
	public enum NetworkState {
		
	}
	
	public static boolean isGPRSNetwork(Context context){
		return  ((TelephonyManager)(context.getSystemService(Context.TELEPHONY_SERVICE))).getNetworkType() == TelephonyManager.NETWORK_TYPE_GPRS;
	}
	
	
	

}
