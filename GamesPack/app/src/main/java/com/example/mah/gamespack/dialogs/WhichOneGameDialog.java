package com.example.mah.gamespack.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mah.gamespack.OnNameSelctedListener;
import com.example.mah.gamespack.R;

public class WhichOneGameDialog extends Dialog
{
    private EditText editText;
    private Button Confirm;
    private OnNameSelctedListener listener;

    public WhichOneGameDialog(@NonNull Context context , OnNameSelctedListener listener) {
        super(context);
        this.listener=listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.which_one_dialog);

        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.MATCH_PARENT);

        introduction();

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onNameSelected(editText.getText().toString());
                dismiss();

            }
        });


    }

    private void introduction()
    {
        editText=(EditText) findViewById(R.id.user_edit);
        Confirm=(Button) findViewById(R.id.user_button);
    }
}
