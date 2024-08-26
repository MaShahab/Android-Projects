package com.example.mah.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DataBase_Name ="student.db";
    public static final String Table_name ="student_table";

    public static final String Col1="ID";
    public static final String Col2="NAME";
    public static final String Col3="FAMILY";
    public static final String Col4="AGE";

    public DataBaseHelper(Context context) {
        super(context,DataBase_Name,null,1);
        SQLiteDatabase db = this.getWritableDatabase ();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+Table_name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT , Family TEXT , Age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(sqLiteDatabase);
    }
    public boolean inserData(String name,String family,String age)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2,name);
        contentValues.put(Col3,family);
        contentValues.put(Col4,age);
        long result = sqLiteDatabase.insert(Table_name,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+Table_name,null);
        return res;
    }

    public boolean updateData(String id , String name , String family , String age)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1 , id);
        contentValues.put(Col2 , name);
        contentValues.put(Col3 , family);
        contentValues.put(Col4 , age);
        sqLiteDatabase.update(Table_name,contentValues,"ID=?" , new String[]{id});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        return db.delete(Table_name,"ID=?", new String[]{id});
    }
}
