package com.example.mah.testproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE_GALLERY = 999;
    final int REQUEST_CODE_CAMERA = 888;

    private ImageView imageView;
    private ImageView imageView2;
    private Button getImageBtn;
    private Button inserttodbBtn;
    private Button delteImageButton;
    private Button showImage;
    private Button updateButton;

    private MyDatabase myDatabase;
    private byte[] byteArray;

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );



        introduction ();

        getImageBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA},REQUEST_CODE_GALLERY);

            }
        } );

        inserttodbBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if(imageView==null)
                {
                    Toast.makeText ( MainActivity.this, "nothing for insert to data base", Toast.LENGTH_SHORT ).show ();
                }

                else
                {
                    Toast.makeText ( MainActivity.this, "Ok", Toast.LENGTH_SHORT ).show ();
                    imageViewToByte(imageView);
                    insertImage(byteArray);
                }

            }
        } );


        showImage.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

//                byte[] bb = cursor.getBlob(cursor.getColumnIndex(MyBaseColumn.MyTable.ImageField));
//                imageView.setImageBitmap(BitmapFactory.decodeByteArray(bb, 0, bb.length));


//                byte[] blob=cursor.getBlob(0);
//                Bitmap bmp=BitmapFactory.decodeByteArray(blob,0,blob.length);
//                ImageView image=new ImageView(MainActivity.this);
//                image.setImageBitmap(bmp);

//                viewAlldata ();


                myDatabase = new MyDatabase(MainActivity.this);

                cursor = myDatabase.getImage();

                if(cursor.move (3))
                {
                    byte[] blob = cursor.getBlob(1);
                    ByteArrayInputStream inputStream = new ByteArrayInputStream ( blob );
                    Bitmap bitmap = BitmapFactory.decodeStream ( inputStream );
                    imageView2.setImageBitmap ( bitmap );
                }



            }
        } );

        delteImageButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                deleteData ();

            }
        } );

        updateButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                updateImage(byteArray);

            }
        } );
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_CODE_GALLERY:
            {
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select"), REQUEST_CODE_GALLERY);
                }
                else
                {
                    Toast.makeText ( this, "Unfortunately you denied the permission", Toast.LENGTH_SHORT ).show ();
                }
            }
            break;
            case REQUEST_CODE_CAMERA:
            {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText ( this, "you wont permit to camera", Toast.LENGTH_SHORT ).show ();
                }
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Uri uri = data.getData ();
        try
        {
            InputStream inputStream = getContentResolver ().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace ();
        }

    }


    private void introduction()
    {
        imageView = (ImageView) findViewById ( R.id.imageView );
        getImageBtn = (Button) findViewById ( R.id.button );
        inserttodbBtn = (Button) findViewById ( R.id.button2 );
        showImage = (Button) findViewById ( R.id.show_image );
        imageView2 = (ImageView) findViewById ( R.id.imageView_output );
        delteImageButton = (Button) findViewById ( R.id.delete_btn );
        updateButton = (Button) findViewById ( R.id.btn_update_image );
    }

    private byte[] imageViewToByte(ImageView imageView)
    {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable ()).getBitmap ();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byteArray = stream.toByteArray ();
        return byteArray;
    }

    private void insertImage(byte[] newImage)
    {
        myDatabase = new MyDatabase(this);
        boolean insertimage = myDatabase.insertImage(newImage);
        if (insertimage==true)
        {
            Toast.makeText ( this, "inserted successfully", Toast.LENGTH_SHORT ).show ();
        }
        else
        {
            Toast.makeText ( this, "not successfully", Toast.LENGTH_SHORT ).show ();
        }
    }

//    public Bitmap getImage(int i){
//
//        String qu = "select img  from table where feedid=" + i ;
//        SQLiteDatabase sqLiteDatabase =null;
//        Cursor cur = sqLiteDatabase.rawQuery(qu, null);
//
//        if (cur.moveToFirst()){
//            byte[] imgByte = cur.getBlob(0);
//            cur.close();
//            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
//        }
//        if (cur != null && !cur.isClosed()) {
//            cur.close();
//        }
//
//        return null ;
//    }

    public void getImage(View view)
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tb",null);
        if(cursor.moveToNext ())
        {
            byte[] image = cursor.getBlob (0);
            Bitmap bitmap = BitmapFactory.decodeByteArray ( image,0,image.length );
        }
    }
    private void viewAlldata()
    {
        Cursor cursor = myDatabase.getImageData(byteArray);
        if(cursor.getCount ()==0)
        {
            Toast.makeText ( this, "fsdfsdfsdf", Toast.LENGTH_SHORT ).show ();
        }
        else
        {
            byteArray = cursor.getBlob( Integer.parseInt ("newImage") );
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
            imageView2.setImageBitmap(bitmap);
        }
    }

    private void deleteData()
    {
        myDatabase = new MyDatabase(MainActivity.this);
        Integer deleterow = myDatabase.deleteData("2");
        if(deleterow>0)
        {
            Toast.makeText ( this, "deleted successfully", Toast.LENGTH_SHORT ).show ();
        }
        else {
            Toast.makeText ( this, "unfortunately not deleted", Toast.LENGTH_SHORT ).show ();
        }
    }

    private void updateImage(byte[] updateImage)
    {
        myDatabase = new MyDatabase(MainActivity.this);
        boolean isUpdate = myDatabase.updateImage(updateImage);
        if(isUpdate==true)
        {
            Toast.makeText ( this, "updated successfully", Toast.LENGTH_SHORT ).show ();
        }
        else
        {
            Toast.makeText ( this, "not updated", Toast.LENGTH_SHORT ).show ();
        }
    }
}