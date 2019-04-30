package com.example.lulu.assignment;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

public class Providers extends ContentProvider {
    static final String PROVIDER_NAME="com.example.lulu.mygroups.Providers";
    static final String URL = "content://"+ PROVIDER_NAME +"/student";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String _ID = "id";
    static final String NAME = "name";
    static final String ASSIGNMENTS = "Agrade";
    static final String MIDSEM= "Mgrade";
    static final String EXAM= "Egrade";

    private static HashMap<String, String> values;
    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;
    static final UriMatcher urimatcher;
    static {
        urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urimatcher.addURI(PROVIDER_NAME, "student", STUDENTS);
        urimatcher.addURI(PROVIDER_NAME, "student/#", STUDENTS);
    }
// database specific constant declarations
    private SQLiteDatabase db;
    static final String DATABASE_NAME= "must";
    static final String STUDENT_TABLE_NAME= "students";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = " CREATE TABLE "+ STUDENT_TABLE_NAME +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " +
            " Agrade TEXT NOT NULL," +
            " Mgrade TEXT NOT NULL," +
            " Egrade TEXT NOT NULL);";

//the helper class creats and manages the providers underlying repository
    private static class DatabaseHelper extends SQLiteOpenHelper {
    DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(" DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
    onCreate(db);
    }
}

    @Override

    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbahelper = new DatabaseHelper(context);
        //we create a writable database whose creation is triggered  when its not yet existant
        db = dbahelper.getWritableDatabase();
        return (db== null)? false:true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STUDENT_TABLE_NAME);
        switch (urimatcher.match(uri)) {
            case STUDENTS:
                qb.setProjectionMap(values);
                break;

            case STUDENT_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
        }
        if( sortOrder == null || sortOrder == "" ){
            sortOrder= NAME;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,null, sortOrder);
        //registering to watch for changes in the content URL
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (urimatcher.match(uri)){
            //getting all student records
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";
                //getting a specific student given the id
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.students";
                default:
                    throw new IllegalArgumentException("Unknown URI" + uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //sdding a new student or student information
        long rowID = db.insert( STUDENT_TABLE_NAME, "", values);

        //after the colonm has been added sucessfully
        try {
            if (rowID > 0) {
                Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }

        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (urimatcher.match(uri)){
            case STUDENTS:
                count = db.delete( STUDENT_TABLE_NAME, selection, selectionArgs);
                break;

            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( STUDENT_TABLE_NAME, _ID + "=" + id +
                        (!TextUtils.isEmpty(selection)? " AND (" + selection + ')' : ""), selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException( " Unknown URI " +uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (urimatcher.match(uri)){
            case STUDENTS:
                count = db.update( STUDENT_TABLE_NAME, values, selection, selectionArgs);
                break;

            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update( STUDENT_TABLE_NAME, values, _ID + "=" + id +
                        (!TextUtils.isEmpty(selection)? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException( " Unknown URI " + uri );
        }
    getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}