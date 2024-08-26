package com.example.mah.sqliteproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteDialog extends Dialog {

    private EditText idEdit;
    private Button delButton;

    private OnIdSelectedListener onIdSelectedListener;

    public DeleteDialog(@NonNull Context context , OnIdSelectedListener onIdSelectedListener) {
        super ( context );
        this.onIdSelectedListener = onIdSelectedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.dialog_delete );

        introductoin ();

        getWindow ().setLayout(800,1390);

        delButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                onIdSelectedListener.onIdSelected(idEdit.getText ().toString ());
                deleteData ();
            }

        } );

    }

    private void introductoin()
    {
        idEdit = (EditText) findViewById ( R.id.dialog_id );
        delButton = (Button) findViewById ( R.id.dialog_btn_delete );
    }

    private void deleteData()
    {
        DataBaseHelper dataBaseHelpe = new DataBaseHelper(getContext ());
        Integer deleteRow = dataBaseHelpe.deleteData(idEdit.getText ().toString ());
        if(deleteRow>0)
        {
            Toast.makeText ( getContext (), "successfully deleted ", Toast.LENGTH_LONG ).show ();
        }
        else {
            Toast.makeText ( getContext (), "unfortunately deleting operation counter with fault", Toast.LENGTH_SHORT ).show ();
        }
    }
}
