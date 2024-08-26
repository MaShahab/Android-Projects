package com.example.mah.sqliteproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditDialog extends Dialog {

    private TextView name;
    private TextView family;
    private TextView age;

    private Button editButton;

    private OneditInfoListener oneditInfoListener;

    public EditDialog(@NonNull Context context , OneditInfoListener oneditInfoListener) {
        super ( context );
        this.oneditInfoListener = oneditInfoListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView(R.layout.dialog_edit);

        getWindow ().setLayout(1000,1370);

        introduction ();


        editButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                oneditInfoListener.onInfo(name.getText ().toString (),family.getText ().toString (),age.getText ().toString ());
                dismiss ();
            }
        } );



    }

    private void introduction()
    {
        name = (EditText) findViewById ( R.id.edit_name );
        family = (EditText) findViewById ( R.id.edit_family );
        age = (EditText) findViewById ( R.id.edit_age );
        editButton = (Button) findViewById ( R.id.btn_edit );
    }
}
