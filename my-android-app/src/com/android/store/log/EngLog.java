package com.android.store.log;

import android.util.Log;

public class EngLog {
	
	public static int	 d(String tag, String msg){
		if(LogSettings.DEBUG){
			return Log.d(tag, msg);
		}
		return 0;	
	}
	
	
	public static int	 d(String tag, String msg, Throwable tr){
		if(LogSettings.DEBUG){
			return Log.d(tag, msg,tr);
		}
		return 0;	
	}
	
	
	public static int	 e(String tag, String msg){
		if(LogSettings.DEBUG){
			return Log.e(tag, msg);
		}
		return 0;	
	}
	
	public static int e(String tag, String msg, Throwable tr){
		if(LogSettings.DEBUG){
			return Log.e(tag, msg,tr);
		}
		return 0;	
	}
	
	
	
	
	

}
