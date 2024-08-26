package com.example.mah.imagesaving;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        introduction ();

        db.execSQL("create table if not exists tb (a blob)");


    }

    private void introduction()
    {
        imageView = (ImageView) findViewById ( R.id.show_image );
        db = this.openOrCreateDatabase("test.db", Context.MODE_PRIVATE,null);
    }

    public void saveImage(View view)
    {
        try
        {
            FileInputStream fis = new FileInputStream("/storage/sdcard/myself.jpg");
            byte[] image = new byte[fis.available ()];
            fis.read(image);

            ContentValues contentValues = new ContentValues();
            contentValues.put("a",image);
            db.insert("tb",null,contentValues);
            fis.close ();

            Toast.makeText ( this, "insert successes", Toast.LENGTH_SHORT ).show ();

        }

        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }
    public void getImage(View view)
    {
        Cursor cursor = db.rawQuery ("select * from tb",null);
        if(cursor.moveToNext ())
        {
            byte[] image = cursor.getBlob(0);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imageView.setImageBitmap(bitmap);
            Toast.makeText ( this, "select successes", Toast.LENGTH_SHORT ).show ();
        }
    }
}
