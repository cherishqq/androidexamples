package com.android;

import java.util.prefs.Preferences;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;

public class App extends Application {
	
	
	private static final String tag = "GirlApp";
	private static Context applicationContext = null;
    private SharedPreferences mPrefs;
    private static final String PACKAGE_NAME = "com.android";
	
	private String mVersion = null;
	
	private int mWidth;
	
	
	public static int getmWidth() {
		return applicationContext.getResources().getDisplayMetrics().widthPixels;
	}


	public static int getmHeight() {
		return applicationContext.getResources().getDisplayMetrics().heightPixels;
	}


	private int mHeight;
    
    @Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.mVersion = getVersionString(this);
		applicationContext = getApplicationContext();
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		   // Setup some defaults in our preferences if not set yet.
        float screenDensity = getApplicationContext().getResources().getDisplayMetrics().density;
	}
	
	public static Context getApp() {
		return applicationContext;
	}

	
    private static String getVersionString(Context context) {
        // Get a version string for the app.
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(PACKAGE_NAME, 0);
            return PACKAGE_NAME + ":" + String.valueOf(pi.versionCode);
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    

}
