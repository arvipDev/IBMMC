package com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE;

        CREATE_CONTACTS_TABLE = "create table " + TABLE_Groups + "("
                + KEY_TIMESTAMP + " integer primary key, " + KEY_GP_NAME
                + " text not null);";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Groups);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<BaseGroup> add(BaseGroup group) {
        Log.d("Inside", "addDB");
        Log.d("name", group.getName());
        Log.d("ts",""+ group.getTimeStamp());

        ArrayList<BaseGroup> gpList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_GP_NAME, group.getName());
        values.put(KEY_TIMESTAMP, group.getTimeStamp());
        db.insert(TABLE_Groups, null, values);
        db.close();
        print(getAllvalues());
        return gpList;
    }

    public ArrayList<BaseGroup> getAllvalues() {
        ArrayList<BaseGroup> gpList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_Groups;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BaseGroup gp = new BaseGroup();
                gp.setName(cursor.getString(1));
                gp.setTimeStamp(Long.parseLong(cursor.getString(0)));
                gpList.add(gp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return gpList;
    }

    public ArrayList<BaseGroup> deleteFromDB (BaseGroup gp){
        ArrayList<BaseGroup> deleted_gp;
        deleted_gp = getAllvalues();
        for (BaseGroup group: deleted_gp){
            if (gp.getName().matches(group.getName())){
                deleted_gp.remove(group);
                return deleted_gp;
            }
        }
        return null;
    }

    private void print (ArrayList<BaseGroup> gp){
        Log.d("DB size", "" + gp.size());
        for(BaseGroup bp: gp){
            Log.d("name", bp.getName());
            Log.d("ts",""+ bp.getTimeStamp());
        }
    }

    public void deleteAll() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_Groups);
        db.close();
    }

}
