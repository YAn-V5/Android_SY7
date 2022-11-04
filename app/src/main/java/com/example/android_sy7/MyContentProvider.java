package com.example.android_sy7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    public static final int CONTACTS_ALL = 0;
    public static final int CONTACTS_ITEM = 1;
    private static UriMatcher uriMatcher;
    private static MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.android_sy7.provider","contacts",CONTACTS_ALL);
        uriMatcher.addURI("com.example.android_sy7.provider","contacts/#",CONTACTS_ITEM);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new MyDatabaseHelper(getContext(),"Phone.db",null,6);
        return true;
    }

    public MyContentProvider() {
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case CONTACTS_ALL://查询contacts表中所有数据
                return"vnd.android.cursor.dir/vnd.com.example.android_sy7.provider.contacts";
            case CONTACTS_ITEM://查询contacts表中单条数据
                return"vnd.android.cursor.item/vnd.com.example.android_sy7.provider.contacts";
            default:
                break;
        }
        return null;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch(uriMatcher.match(uri)){
            case CONTACTS_ALL://查询contacts表中所有数据
                cursor = db.query("Contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CONTACTS_ITEM://查询contacts表中单条数据
                String contactsId = uri.getPathSegments().get(1);
                cursor = db.query("Contacts",projection,"id=?",new String[]{contactsId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}