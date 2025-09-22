package com.codelab.basics;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;


/**
 * Created by w0091766 on 4/29/2016.
 */
public class DBClass extends SQLiteOpenHelper implements DB_Interface {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "DB_Name.db";

    // If you change the database schema, you must increment the database version.
    private static final String TABLE_NAME = "sample_table";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUM_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String _ID = "_ID";
    private static final String _COL_1 = "str_col";
    private static final String _COL_2 = "num_col";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, str_col VARCHAR(256), num_col INTEGER)";
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

        // Old code with embedded SQL
//        db.execSQL("CREATE TABLE sample_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, str_col VARCHAR(256), num_col INTEGER)");
//
//        db.execSQL(
//                "INSERT INTO sample_table(str_col,num_col) VALUES('Ford', 100)");
//        db.execSQL(
//                "INSERT INTO sample_table(str_col,num_col) VALUES('Toyota', 200)");
//        db.execSQL(
//                "INSERT INTO sample_table(str_col,num_col) VALUES('Honda', 300)");
//        db.execSQL(
//                "INSERT INTO sample_table(str_col,num_col) VALUES('GM', 400)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        Log.d("DBClass", "DB onUpgrade() to version " + DATABASE_VERSION);
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
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
        //String command = "INSERT INTO CarModels(str_col,num_col) VALUES('" + carModel.getModelName() + "', " + carModel.getModelNumber() + ")";

        Log.v("DBClass", "add=>  " + dataModel.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(_COL_1, dataModel.getModelName());
        values.put(_COL_2, dataModel.getModelNumber());

        // 3. insert
        db.insert(TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

        // debug output to see what we're doing
        dump();

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

    private Random r = new Random();
    // Add Sample rows
    private void addDefaultRows(){
        // Call count once
        int doCount = this.count();
        if (doCount > 1) {
            Log.v("DBClass", "already rows in DB");

        } else {
            Log.v("DBClass", "no rows in DB...add some");
            DataModel a = new DataModel(1, "Ford", 101);
            this.save(a);
            a = new DataModel(2, "GM", 201);
            this.save(a);
            a = new DataModel(3, "Tesla", 301);
            this.save(a);
            a = new DataModel(4, "Toyota", 401);
            this.save(a);
        }

        DataModel a = new DataModel(1, "Rusty Bucket", r.nextInt(500));
        this.save(a);
    }

    @Override
    public List<DataModel> findAll() {
        List<DataModel> temp = new ArrayList<DataModel>();

        // if no rows, add
        addDefaultRows();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build and add it to list
        DataModel item;
        if (cursor.moveToFirst()) {
            do {
                // This code puts a dataModel object into the PlaceHolder for the fragment
                // if you had more columns in the DB, you'd format  them in the non-details
                // list here
                item = new DataModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
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

    // Dump the DB as a test
    private void dump() {
    }  // oops, never got around to this...but findall is dump-ish

}
