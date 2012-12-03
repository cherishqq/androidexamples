package com.derek.store.db.provider;

import java.util.LinkedHashMap;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class DataStore {

	private DataStore() {
	};

	public static final int DB_VERSION = 2;
	static final String DB_FILE = "crm.db";

	public interface RCMColumns {
		public static final String USER_ID = "userId"; // INTEGER (long)
		public static final String DEFAULT_SORT_ORDER = "_ID ASC";
	}

	/**
	 * a simple instance
	 * 
	 * @author Derek.pan
	 * @date 2012-11-26
	 * @description
	 */

	public static final class UserTable extends DbTable implements RCMColumns,
			BaseColumns {

		private static final String TABLE_NAME = "User";

		public static final String USER_NAME = "userName";
		public static final String USER_PASSWORD = "password";
		public static final String USER_STATUS = "status";

		public static final int USER_STATUS_ALIVE = 0;
		public static final int USER_STATUS_DELETE = 1;

		private static final UserTable sInstance = new UserTable();

		static UserTable getInstance() {
			return sInstance;
		}

		private static final String CREATE_TABLE_STMT = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_NAME
				+ " ("
				+ _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ USER_NAME
				+ " TEXT,"
				+ USER_ID
				+ " INTEGER ,"
				+ USER_PASSWORD
				+ " TEXT,"
				+ USER_STATUS
				+ " INTEGER DEFAULT  0"

				+ ");";

		@Override
		String getName() {
			return TABLE_NAME;
		}

		@Override
		void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_STMT);
		}

	}

	static LinkedHashMap<String, DbTable> sDbTables = new LinkedHashMap<String, DbTable>();

	static {
		sDbTables.put(UserTable.getInstance().getName(),
				UserTable.getInstance());

	};
}
