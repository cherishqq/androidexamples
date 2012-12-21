package com.android.log;

import android.util.Log;

/**
 * 
 * @author Derek.pan
 *
 */
public class EngLog {
	
	private String tag;
	
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


	public EngLog(Class  c ) {
		this.tag = c.getName();		
	}
	
	public int d(String msg){
		if(LogSettings.DEBUG){
			return Log.d(tag, msg);
		}
		return 0;
	}
	
	public int d(String msg,Object ... args){
		if(LogSettings.DEBUG){
			msg = String.format(msg, args);
			return Log.d(tag, msg);
		}
		return 0;
	}
	
	public int e(String msg){
		if(LogSettings.DEBUG){
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	public int e(String msg,Object ... args){
		if(LogSettings.DEBUG){
			msg = String.format(msg, args);
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	public int i(String msg){
		if(LogSettings.DEBUG){
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	public int i(String msg,Object ... args){
		if(LogSettings.DEBUG){
			msg = String.format(msg, args);
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	public int w(String msg){
		if(LogSettings.DEBUG){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	public int w(String msg,Object ... args){
		if(LogSettings.DEBUG){
			msg = String.format(msg, args);
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	public int v(String msg){
		if(LogSettings.DEBUG){
			return Log.v(tag, msg);
		}
		return 0;
	}
	
	public int v(String msg,Object ... args){
		if(LogSettings.DEBUG){
			msg = String.format(msg, args);
			return Log.v(tag, msg);
		}
		return 0;
	}
	
	
	
	
	
	

}
