package com.example.mah.sqliteproject;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;

    private EditText userNameEdit;
    private EditText familyEdit;
    private EditText ageEdit;

    private Button saveButton;
    private Button showButton;
    private Button updateButton;
    private Button deleteButton;

    private Button listBtn;

    private OnIdSelectedListener onIdSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        introduction ();

        addData ();

        showButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                viewAllData ();
            }
        } );

        updateButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                UpdateFragment updateFragment = new UpdateFragment ();
                getSupportFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container , updateFragment )
                        .addToBackStack ( null )
                        .commit ();
            }
        } );

        deleteButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {


                DeleteDialog deleteDialog = new DeleteDialog ( MainActivity.this, new OnIdSelectedListener () {
                    @Override
                    public void onIdSelected(String id) {



                    }
                } );

                deleteDialog.show ();


            }
        } );


        listBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                AdapterFragment adapterFragment = new AdapterFragment ();
                getSupportFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,adapterFragment )
                        .addToBackStack ( null )
                        .commit ();

                DataModels dataModels = new DataModels ();
                AdapterFragment adapterFragment2 = new AdapterFragment ();
                Bundle bundle = new Bundle ();
                bundle.putSerializable ( "data_model",dataModels );
                adapterFragment2.setArguments(bundle);

            }
        } );


    }


    private void introduction()
    {
        userNameEdit = (EditText) findViewById ( R.id.id_name );
        familyEdit = (EditText) findViewById ( R.id.id_family );
        ageEdit =  (EditText) findViewById ( R.id.id_age );
        saveButton = (Button) findViewById ( R.id.btn_save );
        showButton = (Button) findViewById ( R.id.btn_show );
        updateButton = (Button) findViewById ( R.id.btn_update );
        deleteButton = (Button) findViewById ( R.id.btn_delete );
        listBtn = (Button) findViewById ( R.id.show_list );
    }

    public void addData()
    {
        saveButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                boolean isInsert = dataBaseHelper.inserData(userNameEdit.getText ().toString (),familyEdit.getText ().toString (),ageEdit.getText ().toString ());
                if(isInsert==true)
                {
                    Toast.makeText ( MainActivity.this, "Data's saved successfully", Toast.LENGTH_LONG ).show ();
                }
                else
                {
                    Toast.makeText ( MainActivity.this, "Unfortunately", Toast.LENGTH_LONG ).show ();
                }
            }
        } );
    }

    private void showMeasage(String title , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable (true);
        builder.setTitle(title);
        builder.setMessage (message);
        builder.show ();
    }

    private void viewAllData()
    {
        Cursor res = dataBaseHelper.getAllData ();
        if(res.getCount ()==0)
        {
            showMeasage("Error","unfortunately nothing exists for showing");
        }
        else
        {
            StringBuffer stringBuffer = new StringBuffer();
            while (res.moveToNext ())
            {
                stringBuffer.append("ID : "+res.getString(0)+"\n");
                stringBuffer.append("Name : "+res.getString(1)+"\n");
                stringBuffer.append("Family : "+res.getString(2)+"\n");
                stringBuffer.append("Age : "+res.getString(3)+"\n");
                stringBuffer.append ( "\n" );
            }
            showMeasage("Data's list",stringBuffer.toString ());
        }
    }
}
