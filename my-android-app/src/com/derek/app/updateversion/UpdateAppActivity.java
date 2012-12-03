package com.derek.app.updateversion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.derek.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UpdateAppActivity extends Activity {
    /** Called when the activity is first created. */
	private static final String TAG = "Update";
	private Button btnUpdateApp;
	private ProgressDialog pBar;
	private String downPath = "http://10.0.2.2:8080/";
	private String appName = "NewAppSample.apk";
	private String appVersion = "version.json";
	private int newVerCode = 0;
	private String newVerName = "";
	private Handler handler=new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateversion);
        try {
        	if(isNetworkAvailable(this) == false){
        		return;
        	}else{
        		checkToUpdate();
        	}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        btnUpdateApp = (Button)findViewById(R.id.btnUpdateApp);
        btnUpdateApp.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
        });
    }
    //check the Network is available
    private static boolean isNetworkAvailable(Context context) {
		// TODO Auto-generated method stub
    	try{
    	
    		ConnectivityManager cm = (ConnectivityManager)context
    				.getSystemService(Context.CONNECTIVITY_SERVICE);
    		NetworkInfo netWorkInfo = cm.getActiveNetworkInfo();
    		return (netWorkInfo != null && netWorkInfo.isAvailable());//濡拷绁寸純鎴犵捕閺勵垰鎯侀崣顖滄暏
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
	}
	//check new version and update
	private void checkToUpdate() throws NameNotFoundException {
		// TODO Auto-generated method stub
		if(getServerVersion()){
			int currentCode = CurrentVersion.getVerCode(this);
			if(newVerCode > currentCode)
			{//Current Version is old
				//瀵懓鍤弴瀛樻煀閹绘劗銇氱�纭呯樈濡楋拷				showUpdateDialog();
			}
		}
	}
	//show Update Dialog
	private void showUpdateDialog() throws NameNotFoundException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("");
		sb.append(CurrentVersion.getVerName(this));
		sb.append("VerCode:");
		sb.append(CurrentVersion.getVerCode(this));
		sb.append("\n");
		sb.append("閸欐垹骞囬弬鎵閺堫剨绱�");
		sb.append(newVerName);
		sb.append("NewVerCode:");
		sb.append(newVerCode);
		sb.append("\n");
		sb.append("");
		Dialog dialog = new AlertDialog.Builder(UpdateAppActivity.this)
		.setTitle("鏉烆垯娆㈤弴瀛樻煀")
		.setMessage(sb.toString())
		.setPositiveButton("閺囧瓨鏌�", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showProgressBar();//閺囧瓨鏌婅ぐ鎾冲閻楀牊婀�
				}
		})
		.setNegativeButton("閺嗗倷绗夐弴瀛樻煀", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).create();
		dialog.show();
	}
	protected void showProgressBar() {
		// TODO Auto-generated method stub
		pBar = new ProgressDialog(UpdateAppActivity.this);
		pBar.setTitle("濮濓絽婀稉瀣祰");
		pBar.setMessage("鐠囬鈼㈤崥锟�.");
		pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		downAppFile(downPath + appName);
	}
	//Get ServerVersion from GetUpdateInfo.getUpdateVerJSON
	private boolean getServerVersion() {
		// TODO Auto-generated method stub
		try{
			String newVerJSON = GetUpdateInfo.getUpdataVerJSON(downPath + appVersion);
			JSONArray jsonArray = new JSONArray(newVerJSON);
			if(jsonArray.length() > 0){
				JSONObject obj = jsonArray.getJSONObject(0);
				try{
					newVerCode = Integer.parseInt(obj.getString("verCode"));
					newVerName = obj.getString("verName");
				}catch(Exception e){
					Log.e(TAG, e.getMessage());
					newVerCode = -1;
					newVerName = "";
					return false;
				}
			}
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
			return false;
		}
		return true;
	}
	protected void downAppFile(final String url) {
		pBar.show();
		new Thread(){
			public void run(){
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse response;
				try {
					response = client.execute(get);
					HttpEntity entity = response.getEntity();
					long length = entity.getContentLength();
					Log.isLoggable("DownTag", (int) length);
					InputStream is = entity.getContent();
					FileOutputStream fileOutputStream = null;
					if(is == null){
						throw new RuntimeException("isStream is null");
					}
					File file = new File(Environment.getExternalStorageDirectory(),appName);
					fileOutputStream = new FileOutputStream(file);
					byte[] buf = new byte[1024];
					int ch = -1;
					do{
						ch = is.read(buf);
						if(ch <= 0)break;
						fileOutputStream.write(buf, 0, ch);
					}while(true);
					is.close();
					fileOutputStream.close();
					haveDownLoad();
					}catch(ClientProtocolException e){
						e.printStackTrace();
						}catch(IOException e){
						e.printStackTrace();
						}
				}
		}.start();
	}
	//cancel progressBar and start new App
	protected void haveDownLoad() {
		// TODO Auto-generated method stub
		handler.post(new Runnable(){
			public void run(){
				pBar.cancel();
				//瀵懓鍤拃锕�啞濡楋拷閹绘劗銇氶弰顖氭儊鐎瑰顥栭弬鎵畱閻楀牊婀�				
				Dialog installDialog = new AlertDialog.Builder(UpdateAppActivity.this)
				.setTitle("娑撳娴囩�灞惧灇")
				.setMessage("閺勵垰鎯佺�澶庮棖閺傛壆娈戞惔鏃傛暏")
				.setPositiveButton("绾喖鐣�", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						installNewApk();
						finish();
						}
					})
					.setNegativeButton("閸欐牗绉�", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
							}
						})
						.create();
				installDialog.show();
				}
			});
		}
	//鐎瑰顥栭弬鎵畱鎼存梻鏁�	
	protected void installNewApk() 
	{
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(
				new File(Environment.getExternalStorageDirectory(),appName)),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}
	
}