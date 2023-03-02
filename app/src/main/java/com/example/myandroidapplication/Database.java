package com.example.myandroidapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.myandroidapplication.entity.Profile;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    private static final String TABLE_NAME = "users";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_USERNAME = "user_username";
    private static final String USER_PASSWORD = "user_password";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+" ("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USER_NAME+" TEXT,"+USER_EMAIL+" TEXT,"+USER_USERNAME+" TEXT,"+USER_PASSWORD+" TEXT)";
        sqLiteDatabase.execSQL(query);

        String query2 = "CREATE TABLE profile (username TEXT, phone TEXT, division TEXT, district TEXT, area TEXT, address TEXT)";
        sqLiteDatabase.execSQL(query2);
    }

    public void addNewUser(String name, String email, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_NAME, name);
        values.put(USER_EMAIL, email);
        values.put(USER_USERNAME, username);
        values.put(USER_PASSWORD, password);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", profile.getUsername());
        values.put("phone", profile.getPhone());
        values.put("division", profile.getDivision());
        values.put("district", profile.getDistrict());
        values.put("area", profile.getArea());
        values.put("address", profile.getAddress());

        db.insert("profile", null, values);
        db.close();
    }

    public int login(String username, String password){
        String[] arr = new String[2];
        arr[0] = username;
        arr[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE user_username=? AND user_password=?", arr);
        if (cursor.moveToFirst()) {
            return 1;
        }
        return 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
