package com.android.context;


import com.android.common.Preferences;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;


/**
 * Base class for those who need to maintain global application state
 * @author Derek.pan
 *
 */

public class GirlApp  extends Application{
	
	private static final String tag = "GirlApp";
	private static Context applicationContext = null;
    private SharedPreferences mPrefs;
    private static final String PACKAGE_NAME = "com.android";
	
	private String mVersion = null;
	
	public static Context getGrilApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(Context applicationContext) {
		GirlApp.applicationContext = applicationContext;
	}

	
	
	public String getmVersion() {
		if(mVersion != null){
			return mVersion;
		} else {
			return "";
		}
	}

	public void setmVersion(String mVersion) {
		this.mVersion = mVersion;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.mVersion = getVersionString(this);
		applicationContext = getApplicationContext();
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		   // Setup some defaults in our preferences if not set yet.
        Preferences.setupDefaults(mPrefs, getResources());
        float screenDensity = getApplicationContext().getResources().getDisplayMetrics().density;
		
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
