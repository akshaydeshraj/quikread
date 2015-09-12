package com.katana.quikread.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BooksDbHelper extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Quikr";

    // Table name
    private static final String TABLE_NAME = "QuikrBooks";

    // Table Columns names
    private static final String KEY_ID = "primary_key";
    private static final String ADD_TYPE = "add_type";
    private static final String CITY_NAME = "city";
    private static final String AD_LOCALITY = "ad_locality";
    private static final String CONTENT = "content";
    private static final String IMAGE_URL = "image_url";
    private static final String BOOK_ID = "id";
    private static final String BOOK_TITLE = "title";
    private static final String GENRE = "genre";
    private static final String ADD_URL = "add_url";
    private static final String GEO_PIN = "geo_pin";


    public BooksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "create table "+TABLE_NAME+
                " ("+ KEY_ID +" integer primary key AUTOINCREMENT, " +
                ADD_TYPE+" text," + CITY_NAME+" text, "+AD_LOCALITY +
                " text,"+ CONTENT+" text, " + IMAGE_URL+" text,"+ BOOK_ID+" text, "
                +BOOK_TITLE+" text," + GENRE +" text,"+ ADD_URL+" text, "+GEO_PIN+" text)";

        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public String[] getTableColumns(){
        String[] array = {KEY_ID,ADD_TYPE,CITY_NAME,AD_LOCALITY,CONTENT,IMAGE_URL,BOOK_ID
                ,BOOK_TITLE, GENRE,ADD_URL,GEO_PIN};
        return array ;
    }

    public String getTableName(){
        return TABLE_NAME;
    }

}
