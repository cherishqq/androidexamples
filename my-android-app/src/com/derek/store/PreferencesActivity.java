package com.derek.store;

import com.derek.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;


/**
 * 
 * @author Derek.pan
 * @date 2012-11-9
 * @description
 *  常用于  setting 界面   可以思考一下他的设计思想。。这个还是蛮常用的。
 */
public class PreferencesActivity extends PreferenceActivity implements
		OnPreferenceChangeListener
{
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getPreferenceManager().setSharedPreferencesName("setting");
		addPreferencesFromResource(R.xml.preference_setting);
		Preference individualNamePreference = findPreference("individual_name");
		SharedPreferences sharedPreferences= individualNamePreference.getSharedPreferences();
		individualNamePreference.setSummary(sharedPreferences.getString("individual_name", ""));
		if (sharedPreferences.getBoolean("yesno_save_individual_info", false))
			 individualNamePreference.setEnabled(true);
		else
			 individualNamePreference.setEnabled(false);
		individualNamePreference.setOnPreferenceChangeListener(this);
		

	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue)
	{
		preference.setSummary(String.valueOf(newValue));		
		return true;
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference)
	{

		if ("yesno_save_individual_info".equals(preference.getKey()))
		{
			findPreference("individual_name").setEnabled(
					!findPreference("individual_name").isEnabled());
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
}