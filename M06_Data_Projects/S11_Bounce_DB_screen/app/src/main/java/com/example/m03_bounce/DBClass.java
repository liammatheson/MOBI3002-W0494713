package com.example.m03_bounce;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by w0091766 on 4/29/2016.
 */
public class DBClass extends SQLiteOpenHelper implements DB_Interface {

    public static final int DATABASE_VERSION = 18;
    public static final String DATABASE_NAME = "Bounce_DB_Name.db";

    // If you change the database schema, you must increment the database version.
    private static final String TABLE_NAME = "sample_table";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUM_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String _ID = "_ID";
    private static final String _COL_1 = "X";  // Names of the columns
    private static final String _COL_2 = "Y";
    private static final String _COL_3 = "DX";
    private static final String _COL_4 = "DY";
    private static final String _COL_5 = "COLOR";
    private static final String _COL_6 = "NAME";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "X FLOAT, Y FLOAT, DX FLOAT, DY FLOAT, COLOR INTEGER, NAME TEXT)";
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    /**
     * This JavaDoc goes with this method type / * * and hit enter
     */
    public void onCreate(SQLiteDatabase db) {

        Log.d("DBClass", "DB onCreate() " + SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE);
        Log.d("DBClass", "DB onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        Log.d("DBClass", "DB onUpgrade() to version " + DATABASE_VERSION);
    }

    /////////// Implement Interface ///////////////////////////
    @Override
    public int count() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        Log.v("DBClass", "getCount=" + cnt);
        return cnt;
    }

    @Override
    public int save(DataModel dataModel) {
        Log.v("DBClass", "save()=>  " + dataModel.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(_COL_1, dataModel.getModelX());
        values.put(_COL_2, dataModel.getModelY());
        values.put(_COL_3, dataModel.getModelDX());
        values.put(_COL_4, dataModel.getModelDY());
        values.put(_COL_5, dataModel.getColor());
        values.put(_COL_6, dataModel.getName());

        // 3. insert
        db.insert(TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

        return 0;
    }

    @Override
    public int update(DataModel dataModel) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<DataModel> findAll() {
        List<DataModel> temp = new ArrayList<DataModel>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build and add it to list
        DataModel item;
        if (cursor.moveToFirst()) {
            do {
                item = new DataModel(cursor.getFloat(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getInt(5), cursor.getString(6) );
                temp.add(item);
            } while (cursor.moveToNext());
        }

        Log.v("DBClass", "findAll=> " + temp.toString());

        // return all
        return temp;
    }

    @Override
    public String getNameById(Long id) {
        return null;
    }

}
