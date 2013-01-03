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
    		return (netWorkInfo != null && netWorkInfo.isAvailable());//婵☆偓鎷风粊瀵哥磾閹寸姷鎹曢柡鍕靛灠閹線宕ｉ婊勬殢
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
				//鐎殿喚鎳撻崵顓㈠即鐎涙ɑ鐓�柟缁樺姉閵囨氨锟界涵鍛▓婵℃鎷�			showUpdateDialog();
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
		sb.append("闁告瑦鍨归獮鍥棘閹殿喖顣奸柡鍫墾缁憋拷");
		sb.append(newVerName);
		sb.append("NewVerCode:");
		sb.append(newVerCode);
		sb.append("\n");
		sb.append("");
		Dialog dialog = new AlertDialog.Builder(UpdateAppActivity.this)
		.setTitle("閺夌儐鍨▎銏ゅ即鐎涙ɑ鐓�")
		.setMessage(sb.toString())
		.setPositiveButton("闁哄洤鐡ㄩ弻锟�", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showProgressBar();//闁哄洤鐡ㄩ弻濠呫亹閹惧啿顤呴柣妤�墛濠�拷
				}
		})
		.setNegativeButton("闁哄棗鍊风粭澶愬即鐎涙ɑ鐓�", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).create();
		dialog.show();
	}
	protected void showProgressBar() {
		// TODO Auto-generated method stub
		pBar = new ProgressDialog(UpdateAppActivity.this);
		pBar.setTitle("婵繐绲藉﹢顏呯▔鐎ｎ厽绁�");
		pBar.setMessage("閻犲洭顥熼埣銏ゅ触閿燂拷.");
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
				//鐎殿喚鎳撻崵顓犳媰閿曪拷鍟炴俊妤嬫嫹闁圭粯鍔楅妵姘跺及椤栨碍鍎婇悗鐟邦樋椤ユ牠寮幍顔界暠闁绘鐗婂﹢锟�			
				Dialog installDialog = new AlertDialog.Builder(UpdateAppActivity.this)
				.setTitle("濞戞挸顑堝ù鍥╋拷鐏炴儳鐏�")
				.setMessage("闁哄嫷鍨伴幆浣猴拷婢跺寒妫栭柡鍌涘濞堟垶鎯旈弮鍌涙殢")
				.setPositiveButton("取消", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						installNewApk();
						finish();
						}
					})
					.setNegativeButton("闁告瑦鐗楃粔锟�", new DialogInterface.OnClickListener() {
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
	//閻庣懓顦块ˉ鏍棘閹殿喗鐣遍幖瀛樻⒒閺侊拷	
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