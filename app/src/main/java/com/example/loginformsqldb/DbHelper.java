package com.example.loginformsqldb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database_three";
    public static final int DATABASE_VERSION = 1;
    //public static final String CREATE_TABLE = "CREATE TABLE user_login(dfname text,dmname text,dlname text)";
   public static final String CREATE_TABLE = "CREATE TABLE user_login(dfname text,dmname text,dlname text,dbirthdate text,demail text,dphno text,dstdid text,dyear text,dgrade text,dgender text)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
