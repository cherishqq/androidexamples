package com.android.rest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import android.util.Log;

import com.android.utils.HttpClientHelp;

public class PhotoListRestRequestTest {
	
	@Test
	public void testGetPhotoList(){
		System.out.println("test");
		
		PhotoListRestRequest request = new PhotoListRestRequest(20,1l);
		boolean b = HttpClientHelp.sendRequest(request);
		System.out.println(b);
		
	}
	
	public void testSimpleHttp(){
		
		 try {
				HttpClient httpclient = new DefaultHttpClient();
//				HttpGet request = new HttpGet("http://192.168.56.1:8080/javaweb/photo/getPictureList?recordCount=0&pictureId=1");
				HttpGet request = new HttpGet("http://10.32.50.101:8080/javaweb/photo/getPictureList?recordCount=0&pictureId=1");
				String response = httpclient.execute(request, new BasicResponseHandler());
				System.out.println(response);
				Log.v("response text", response);
				 } catch (ClientProtocolException e) {
				 e.printStackTrace();
				} catch (IOException e) {
					System.out.println(e.toString());
					e.printStackTrace();
				}
	}

}
