package com.extraaedge.contentsaveandget.utils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.extraaedge.contentsaveandget.structure.UserListItem;

import java.util.ArrayList;

/**
 * Created by Deepak_extra_edge on 3/10/2018.
 */

public class DatabaseContentHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseContentHelper";
    public static final String DATABASE_NAME = "SQLiteContentProvider.db";
    private static final int DATABASE_VERSION = 1;
    public static final String PERSON_TABLE_NAME = "person";
    public static final String PERSON_COLUMN_ID = "id";
    public static final String PERSON_COLUMN_FULL_NAME = "name";
    public static final String PERSON_COLUMN_CITY = "city";
    public static final String PERSON_COLUMN_EMAIL = "email";

    /** An instance variable for SQLiteDatabase */
    private SQLiteDatabase mDB;

    public DatabaseContentHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mDB= getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + PERSON_TABLE_NAME + "(" +
                PERSON_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                PERSON_COLUMN_FULL_NAME + " TEXT, " +
                PERSON_COLUMN_CITY + " TEXT, " +
                PERSON_COLUMN_EMAIL + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        onCreate(db);
    }

    /** Returns all the users in the table */
    public Cursor getAllUsers(){
        return mDB.query(PERSON_TABLE_NAME, new String[] { PERSON_COLUMN_ID,  PERSON_COLUMN_FULL_NAME , PERSON_COLUMN_CITY, PERSON_COLUMN_EMAIL } ,
                null, null, null, null,
                PERSON_COLUMN_FULL_NAME + " asc ");
    }

    /**
     * This Method is used to get data from Database.
     */
    public ArrayList<UserListItem> getUserData() {
        ArrayList<UserListItem> userListItems = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + PERSON_TABLE_NAME + ";", null);
        StringBuffer buffer = new StringBuffer();
        UserListItem userListItem = null;
        while (cursor.moveToNext()) {
            userListItem = new UserListItem();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            userListItem.setName(name);
            userListItem.setCity(city);
            userListItem.setEmail(email);
            buffer.append(userListItem);

            userListItems.add(userListItem);

        }
        for (UserListItem item : userListItems) {
            Log.d(TAG, "getUserData: " + item.getCity());
        }

        return userListItems;

    }
}
