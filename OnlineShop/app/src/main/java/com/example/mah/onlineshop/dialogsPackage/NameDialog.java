package com.example.mah.onlineshop.dialogsPackage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.interfacesPackage.OnNameSelectedListener;

public class NameDialog extends Dialog {

    private EditText editText;
    private Button button;
    private OnNameSelectedListener onNameSelectedListener;

    public NameDialog(@NonNull Context context , OnNameSelectedListener onNameSelectedListener) {
        super ( context );
        this.onNameSelectedListener = onNameSelectedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.dialog_inquiry );

            getWindow ().setLayout(1000,1700);

        introduction ();

        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                onNameSelectedListener.onNameSelected(editText.getText().toString());
                dismiss ();

            }
        } );

    }

    private void introduction()
    {
        editText=(EditText) findViewById ( R.id.edit_username2 );
        button=(Button) findViewById ( R.id.btn_reg );
    }
}
