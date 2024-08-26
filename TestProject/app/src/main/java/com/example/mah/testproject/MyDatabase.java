package com.example.mah.testproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyDatabase extends SQLiteOpenHelper {

    public static String DataBaseNAme = "note.db";
    public static String TableName = "note_table";

    public static String Col1 = "id";

    public MyDatabase(Context context) {
        super (context,DataBaseNAme,null,1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TableName+"(id INTEGER PRIMARY KEY AUTOINCREMENT , newImage blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL ( "DROP TABLE IF EXISTS "+TableName );
        onCreate(sqLiteDatabase);
    }

    public boolean insertImage(byte[] img)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put("newImage",img);
        long result = sqLiteDatabase.insert(TableName,null,contentValues);
        if(result== -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getImageData(byte[] image)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TableName,null);
        return res;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        return sqLiteDatabase.delete(TableName,"ID=?",new String[]{id});
    }

//    public boolean updateData(String ID , String title , String content , String date , String time)
//    {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Col1,ID);
//        contentValues.put(Col2,title);
//        contentValues.put(Col3,content);
//        contentValues.put(Col4,date);
//        contentValues.put(Col5,time);
//        sqLiteDatabase.update(TableName,contentValues,"ID=?",new String[]{ID});
//        return true;
//    }

    public Cursor getImage()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TableName,null);
        return cursor;
    }

    public boolean updateImage(byte[] img)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues();
        contentValues.put("update_image" , img);
        long updateResult = sqLiteDatabase.update(TableName,contentValues,"ID=?", new String[]{String.valueOf(img)});
        if(updateResult==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
