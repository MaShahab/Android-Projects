package com.example.mah.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private EditText editText;


    public static String DataBaseNAme = "note.db";
    public static String TableName = "note_table";

    public static String Col1 = "id";
    public static String Col2 = "title";
    public static String Col3 = "content";
    public static String Col4 = "date";
    public static String Col5 = "time";

    public MyDatabase(Context context) {
        super (context,DataBaseNAme,null,1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TableName+"(id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , content TEXT , date TEXT , time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL ( "DROP TABLE IF EXISTS "+TableName );
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String title , String content , String date , String time)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2,title);
        contentValues.put(Col3,content);
        contentValues.put(Col4,date);
        contentValues.put(Col5,time);
        long result = sqLiteDatabase.insert(TableName,null,contentValues);
        if(result==-1){return false;}
        else {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TableName,null);
        return res;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        return sqLiteDatabase.delete(TableName,"ID=?",new String[]{id});
    }

    public boolean updateData(String ID , String title , String content , String date , String time)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1,ID);
        contentValues.put(Col2,title);
        contentValues.put(Col3,content);
        contentValues.put(Col4,date);
        contentValues.put(Col5,time);
        sqLiteDatabase.update(TableName,contentValues,"ID=?",new String[]{ID});
        return true;
    }

//    public List<DatasModel> getTitleByName(String name)
//    {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase ();
//        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder ();
//
//        String[] Sql_Select = {"id","NAME","content","date","time"};
//        String Table_Name="Notes";
//
//        sqLiteQueryBuilder.setTables ( Table_Name );
//        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase,Sql_Select,"NAME LIKE?",new String[]{"%"+name+"%"},null,null,null);
//        List<DatasModel> result = new ArrayList <> ();
//        if (cursor.moveToFirst ())
//        {
//            do {
//                DatasModel datasModel = new DatasModel ();
//                datasModel.setID(cursor.getInt(cursor.getColumnIndex("ID")));
//            }
//        }
//
//    }

    public List<String> getTitle()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase ();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder ();

        String[] sql_select={Col2};

        sqLiteQueryBuilder.setTables(TableName);

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase,sql_select,"title LIKE ?",new String[]{"%"+""+"%"},null,null,null);
        List<String> result = new ArrayList <>();

        if(cursor.moveToFirst ())
        {
            do
            {
                result.add(cursor.getString(cursor.getColumnIndex("title")));
            }
            while (cursor.moveToNext ());

        }
        return result;
    }

    public void onSearchFromDatabase()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase ();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM "+ TableName + " WHERE "
                + Col1 + " = " + Col1 + " AND " + Col2 +
                " LIKE  '"+editText.getText()+"%'",null );
    }
}
