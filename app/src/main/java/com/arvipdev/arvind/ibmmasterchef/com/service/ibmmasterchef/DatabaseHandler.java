package com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Main";
    private static final String TABLE_Groups  = "Groups";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_GP_NAME = "name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_CONTACTS_TABLE;

        CREATE_CONTACTS_TABLE = "create table " + TABLE_Groups + "("
                + KEY_TIMESTAMP + " integer primary key autoincrement, " + KEY_GP_NAME
                + " text not null);";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Groups);
        onCreate(sqLiteDatabase);
    }
}
