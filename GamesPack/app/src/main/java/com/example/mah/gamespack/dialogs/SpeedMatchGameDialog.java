package com.example.mah.gamespack.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mah.gamespack.OnNameSelctedListener;
import com.example.mah.gamespack.R;

public class SpeedMatchGameDialog extends Dialog {

    private EditText editTextUSer;
    private Button confirmButton;
    private OnNameSelctedListener listener;

    public SpeedMatchGameDialog(@NonNull Context context, OnNameSelctedListener listener) {
        super(context);
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.speed_match_game_dialog);

        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.MATCH_PARENT);

        introduction();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNameSelected(editTextUSer.getText().toString());
                dismiss();

            }
        });
    }

    private void introduction()
    {
        editTextUSer = (EditText) findViewById(R.id.user_name_edit);
        confirmButton = (Button) findViewById(R.id.confirm_button);
    }
}
