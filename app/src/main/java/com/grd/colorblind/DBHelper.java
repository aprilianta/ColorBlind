package com.grd.colorblind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by A S U S on 11/28/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_JK = "jk";
    public static final String COLUMN_TGL = "tgl";
    /*
    create table users(
        id integer primary key autoincrement,
        email text,
        password text);
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT," + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT," + COLUMN_JK + " TEXT," + COLUMN_TGL + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String username, String email, String password, String jk, String tgl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);
        values.put(COLUMN_JK, jk);
        values.put(COLUMN_TGL, tgl);
        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public boolean getUser(String username, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_USERNAME + " = " + "'"+username+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean cocok(String username){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + USER_TABLE + " where " +COLUMN_USERNAME + " = " + "'"+username+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public String getUsername() {
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(USER_TABLE, new String[] { COLUMN_USERNAME },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return username;
    }

    public boolean updateUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASS, password);
        db.update(USER_TABLE, values, "_id ="+COLUMN_ID,null);
        return true;
    }
}
