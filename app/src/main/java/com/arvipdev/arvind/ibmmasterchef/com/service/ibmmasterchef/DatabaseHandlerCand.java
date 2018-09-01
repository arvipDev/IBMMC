package com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseCandLst;
import java.util.ArrayList;

public class DatabaseHandlerCand extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Cand";
    private static final String TABLE_Cands  = "Candidates";
    private static final String KEY_CANDS = "cands_json";
    private static final String KEY_TIMESTAMP = "c_ts";

    public DatabaseHandlerCand(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE;
        CREATE_CONTACTS_TABLE = "create table " + TABLE_Cands + "("
                + KEY_TIMESTAMP + " integer primary key, " + KEY_CANDS
                + " text not null);";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<BaseCandLst> getAllvalues() {
        ArrayList<BaseCandLst> bcList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_Cands;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BaseCandLst gp = new BaseCandLst();
                gp.setCandidatesJSON(cursor.getString(1));
                gp.setTimeStamp(Long.parseLong(cursor.getString(0)));
                bcList.add(gp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bcList;
    }
}
