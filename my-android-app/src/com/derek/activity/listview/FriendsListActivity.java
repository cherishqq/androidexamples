package com.derek.activity.listview;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;

import com.derek.model.User;

public class FriendsListActivity  extends ListActivity {
	
	
	
	
	private class FriendsTask extends AsyncTask<String,Void,List<User>>{

		@Override
		protected List<User> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
