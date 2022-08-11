package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** This class creates a connection to the database and add a table to the database */
public class DBHelper  extends SQLiteOpenHelper {
    /**Declaration of the database name*/
    protected final static String DATABASE_NAME = "NewsItemsDB";
    /**Declaration of the database version number */
    protected final static int VERSION_NUMBER = 1;
    /**Declaration of the table name */
    public final static String TABLE_NAME= "ITEMS";
    /**Declaration of the title column*/
    public final static String COL1_TITLE = "COL1_TITLE";
    /**Declaration of the url column*/
    public final static String COL2_URL = "COL2_URL";
    /**Declaration of the id column*/
    public final static String COL_ID = "_id";



    public DBHelper(Context ctx) { super(ctx, DATABASE_NAME, null, VERSION_NUMBER);  }

    /** this method creates the table and column in the database */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1_TITLE + " text,"
                + COL2_URL + " text);");  // add or remove columns
    }

    /**this method gets called if the database version on your device is lower than VERSION_NUM */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {   //Drop the old table:
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        //Create the new table:
        onCreate(db);
    }

    /**this method gets called if the database version on your device is higher than VERSION_NUM */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {   //Drop the old table:
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        //Create the new table:
        onCreate(db);
    }
}
