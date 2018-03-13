package com.extraaedge.contentsaveandget.utils;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * <b>AppContentProvider</b>
 * <p>This class is used to Crud Operation using Content Provider</p>
 * created by Subodh Kumar
 */
public class AppContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.extraaedge.contentsaveandget.utils.AppContentProvider";

    /**
     * A uri to do operations on person table. A content provider is identified by its uri
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/users");

    /** Constants to identify the requested operation */
    private static final int USERS = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", USERS);
    }

    /**
     * This content provider does the database operations by this object
     */
    private DatabaseContentHelper mContentHelper;
    private SQLiteDatabase mSQLiteDatabase;

    /**
     * A callback method which is invoked when the content provider is starting up
     */
    @Override
    public boolean onCreate() {
        mContentHelper = new DatabaseContentHelper(getContext());
        mSQLiteDatabase = mContentHelper.getWritableDatabase();

        return (mSQLiteDatabase != null);
    }

    /**
     * A callback method which is by the default content uri
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowID = mSQLiteDatabase.insert(DatabaseContentHelper.PERSON_TABLE_NAME, "", values);

        if (rowID > 0) {
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri;
        }
        throw new SQLException("Failed to add anew Record into" + uri);

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {

        if (uriMatcher.match(uri) == USERS) {
            return mContentHelper.getAllUsers();
        } else {
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
}
