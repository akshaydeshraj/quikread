package com.katana.quikread.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.katana.quikread.models.QuikrItem;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class DatabaseManager {

    private SQLiteDatabase mDatabase;
    private BooksDbHelper mBooksDbHelper;
    private String[] allColumns = null;
    private String tableName;

    @Inject
    public DatabaseManager(BooksDbHelper dataBase){
        mBooksDbHelper = dataBase;
        tableName = mBooksDbHelper.getTableName();
        allColumns = mBooksDbHelper.getTableColumns();
    }

    public void open() {

        mDatabase = mBooksDbHelper.getWritableDatabase();
    }

    public void addBookItem(QuikrItem model){

        ContentValues values = new ContentValues();
        values.put(allColumns[1],model.getAttributeAdType());
        values.put(allColumns[2], model.getCityName());
        values.put(allColumns[3], model.getMetaCategoryName());
     //   values.put(allColumns[4], model.getAdLocality());
        values.put(allColumns[5], model.getContent());
//        values.put(allColumns[6], model.getImages());
        values.put(allColumns[7], model.getId());
        values.put(allColumns[8], model.getTitle());
//        values.put(allColumns[9], model.getAttributeGenre());
        values.put(allColumns[10], model.getUrl());
        values.put(allColumns[11], model.getGeoPin());


        if(mDatabase!=null) {
            mDatabase.insert(tableName, null, values);
            mDatabase.close();
        }

    }

    public List<QuikrItem> getAllBookItems() {

        List<QuikrItem> notificationsModels = new LinkedList<>();
        String query = "SELECT  * FROM " + tableName;
        Cursor cursor = mDatabase.rawQuery(query, null);
        QuikrItem model;
        if (cursor.moveToFirst()) {
            do {
                model = new QuikrItem();
                model.setPrimaryKey(Integer.parseInt(cursor.getString(0)));
                model.setAttributeAdType(cursor.getString(1));
                model.setCityName(cursor.getString(2));
                model.setMetaCategoryName(cursor.getString(3));
//                model.setAdLocality(cursor.getString(4));
                model.setContent(cursor.getString(5));
//                model.setImages(cursor.getString(6));
                model.setId(cursor.getString(7));
                model.setTitle(cursor.getString(8));
//                model.setAttributeGenre(cursor.getString(9));
                model.setUrl(cursor.getString(10));
                model.setGeoPin(cursor.getString(11));
                notificationsModels.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();

        return notificationsModels;

    }
/*

    public void updateAlarm(QuikrItem model) {
        open();
        ContentValues values = new ContentValues();
        values.put(allColumns[1],model.getAppName());
        values.put(allColumns[2], model.getTickerText());
        values.put(allColumns[3], model.getDate());

        database.update(tableName, //table name
                values, // column/value
                allColumns[0]+" = ?", // selections
                new String[] { String.valueOf(model.getNotificationId()) }); //selection args
        database.close();
    }

    public void deleteAlarm(QuikrItem model){
        database.delete(tableName, //table name
                allColumns[0] + " = ?",  // selections
                new String[]{String.valueOf(model.getNotificationId())}); //selections args

    }
    */

    public void close() {
        mBooksDbHelper.close();
    }


}
