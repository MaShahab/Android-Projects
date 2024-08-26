package com.example.mah.searchbartry;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameDialog extends Dialog {

    private EditText dialogEdit;
    private Button dialogButton;

    private OnNameSelectedListener onNameSelectedListener;

    public NameDialog(@NonNull Context context,OnNameSelectedListener onNameSelectedListener) {
        super ( context );
        this.onNameSelectedListener = onNameSelectedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        dialogEdit = (EditText) findViewById ( R.id.edit_dialog );
        dialogButton = (Button) findViewById ( R.id.my_button );

        dialogButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                onNameSelectedListener.onNameSelected(dialogEdit.getText ().toString ());

            }
        } );

    }
}
