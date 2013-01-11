
package com.android.store;


import com.android.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;


/**
 * 
 * @author Derek.pan
 * @date 2012-11-9
 * @description 
 *  实现key-value 键值对的读取
 * 
 */
public class SharedPreferencesActivity extends Activity implements OnCheckedChangeListener
{
	private final String PREFERENCE_NAME = "survey";
	private EditText etName;
	private EditText etHabit;
	private CheckBox cbEmployee;
	private RadioGroup rgCompanyType;
	private RadioButton rbCompany1;
	private RadioButton rbCompany2;
	private RadioButton rbCompany3;

	@Override
	protected void onStop()
	{
        // one 
		//  Activity.MODE_PRIVATE   	Activity.MODE_WORLD_READABLEde  有四种模式,主要涉及到权限
		SharedPreferences mySharedPreferences = getSharedPreferences(
				PREFERENCE_NAME, Activity.MODE_PRIVATE);
	
		// two 
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		
		// three 
		editor.putString("name", etName.getText().toString());
		editor.putString("habit", etHabit.getText().toString());
		editor.putBoolean("employee", cbEmployee.isChecked());
		editor.putInt("companyTypeId", rgCompanyType.getCheckedRadioButtonId());

		// four  
		editor.commit();
		super.onStop();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		rbCompany1.setEnabled(isChecked);
		rbCompany2.setEnabled(isChecked);
		rbCompany3.setEnabled(isChecked);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferencesactivity);
		etName = (EditText) findViewById(R.id.etName);
		etHabit = (EditText) findViewById(R.id.etHabit);
		cbEmployee = (CheckBox) findViewById(R.id.cbEmployee);
		rgCompanyType = (RadioGroup) findViewById(R.id.rgCompanyType);
		rbCompany1 = (RadioButton) findViewById(R.id.rbCompany1);
		rbCompany2 = (RadioButton) findViewById(R.id.rbCompany2);
		rbCompany3 = (RadioButton) findViewById(R.id.rbCompany3);
		cbEmployee.setOnCheckedChangeListener(this);
		SharedPreferences sharedPreferences = getSharedPreferences(
				PREFERENCE_NAME, Activity.MODE_PRIVATE);

		etName.setText(sharedPreferences.getString("name", ""));
		etHabit.setText(sharedPreferences.getString("habit", ""));
		cbEmployee.setChecked(sharedPreferences.getBoolean("employee", false));
		rgCompanyType.check(sharedPreferences.getInt("companyTypeId", -1));
		onCheckedChanged(cbEmployee, cbEmployee.isChecked());

	}
}