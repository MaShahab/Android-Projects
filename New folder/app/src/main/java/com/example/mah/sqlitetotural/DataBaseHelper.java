package com.example.mah.sqlitetotural;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DataBase_Name = "student.db";
    public static final String Table_Name = "student_table";

    public static final String Col1 = "ID";
    public static final String Col2 = "Name";
    public static final String Col3 = "Family";
    public static final String Col4 = "Age";


    public DataBaseHelper(Context context) {
        super (context,DataBase_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+Table_Name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT , Name TEXT , Family TEXT , Age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DataBase_Name);
        onCreate(sqLiteDatabase);
    }
}
