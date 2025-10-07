package com.codelab.basics;


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

    public static final int DATABASE_VERSION = 250;
    public static final String DATABASE_NAME = "DB_Name.db";

    // If you change the database schema, you must increment the database version.
    private static final String TABLE_NAME = "sample_table";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUM_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String _ID = "id";



    private static final String _COL_NAME = "name_col";
    private static final String _COL_TYPE = "type_col";
    private static final String _COL_PKDEX = "dex_col";
    private static final String _COL_DESC = "desc_col";
    private static final String  _COL_ACCESS =  "access_col";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name_col TEXT, " +
                    "type_col TEXT, " +
                    "dex_col INTEGER, " +
                    "desc_col TEXT, " +
                    "access_col INTEGER)";

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

        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Bulbasaur','Grass/Poison',1,'Seed Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Ivysaur','Grass/Poison',2,'Seed Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Venusaur','Grass/Poison',3,'Seed Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Charmander','Fire',4,'Lizard Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Charmeleon','Fire',5,'Flame Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Charizard','Fire/Flying',6,'Flame Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Squirtle','Water',7,'Tiny Turtle Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Wartortle','Water',8,'Turtle Pokemon',0);");
        db.execSQL("INSERT INTO sample_table(name_col,type_col,dex_col,desc_col,access_col) VALUES('Blastoise','Water',9,'Shellfish Pokemon',0);");
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
        Log.v("DBClass", "add=>  " + dataModel.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(_COL_NAME, dataModel.getModelName());
        values.put(_COL_TYPE, dataModel.getType());
        values.put(_COL_PKDEX, dataModel.getDexNum());
        values.put(_COL_DESC, dataModel.getDesc());
        values.put(_COL_ACCESS, dataModel.getAccess());

        long rowId = db.insert(TABLE_NAME, null, values);
        dataModel.setId(rowId);  // set the id in your DataModel

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

    private Random r = new Random();
    // Add Sample rows
//    private void addDefaultRows(){
//        // Call count once
//        int doCount = this.count();
//        if (doCount > 1) {
//            Log.v("DBClass", "already rows in DB");
//
//        }
//        else {
//            Log.v("DBClass", "no rows in DB...add some");
//            DataModel a = new DataModel(1, "Ford", 101);
//            this.save(a);
//            a = new DataModel(2, "GM", 201);
//            this.save(a);
//            a = new DataModel(3, "Tesla", 301);
//            this.save(a);
//            a = new DataModel(4, "Toyota", 401);
//            this.save(a);
//        }
//
//        DataModel a = new DataModel(1, "Rusty Bucket", r.nextInt(500));
//        this.save(a);
//    }

    @Override
    public List<DataModel> findAll() {
        List<DataModel> temp = new ArrayList<DataModel>();

        // if no rows, add
//        addDefaultRows();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build and add it to list
        DataModel item;


        String favQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY access_col DESC LIMIT 1";
        Cursor cursorFav = db.rawQuery(favQuery, null);
        if (cursorFav.moveToFirst()) {
            DataModel mostAccessedPKMN = new DataModel(
                    cursorFav.getLong(0),
                    cursorFav.getString(1),
                    cursorFav.getString(2),
                    cursorFav.getInt(3),
                    cursorFav.getString(4),
                    cursorFav.getInt(5)
            );
            temp.add(mostAccessedPKMN);
        }
        cursorFav.close();


        if (cursor.moveToFirst()) {
            do {
                // This code puts a dataModel object into the PlaceHolder for the fragment
                // if you had more columns in the DB, you'd format  them in the non-details
                // list here
                item = new DataModel(

                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                        );

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

    @Override
    public DataModel getMax() {
        return null;

        // get all

        // find max

        // return max datamodel

    }

    @Override
    public void incAccessCount(long id) {
        Log.v("DBClass", "id = " + id);
        // update sample_table set num_col = num_col + 1 where id=1;

        String cmdString = "update " + TABLE_NAME + " set access_col = access_col + 1 where id="+id;
        Log.v("DBClass", "cmdString = " + cmdString);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(cmdString);
        db.close();

    }

}
