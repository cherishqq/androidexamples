package com.android.activity.listview;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;

import com.android.model.User;

public class UsersListActivity  extends ListActivity {
	
	
	
	
	
	
	
	
	
	
	
	private class FriendsTask extends AsyncTask<String,Void,List<User>>{

		@Override
		protected List<User> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
