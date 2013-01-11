package com.android.app.updateversion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class GetUpdateInfo {
	public static String getUpdataVerJSON(String serverPath) throws Exception{
		StringBuilder newVerJSON = new StringBuilder();
		HttpClient client = new DefaultHttpClient();//鏂板缓http瀹㈡埛绔�?	
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 3000);//璁剧疆杩炴帴瓒呮椂鑼冨洿
		HttpConnectionParams.setSoTimeout(httpParams, 5000);
		//serverPath鏄痸ersion.json鐨勮矾寰�?	
		HttpResponse response = client.execute(new HttpGet(serverPath));
		HttpEntity entity = response.getEntity();
		if(entity != null){
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(entity.getContent(),"UTF-8"),8192);
			String line = null;
			while((line = reader.readLine()) != null){
				newVerJSON.append(line+"\n");//鎸夎璇诲彇�?��叆StringBuilder涓�			
				}
			reader.close();
		}
		return newVerJSON.toString();
	}
}
