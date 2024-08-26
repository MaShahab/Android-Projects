package com.example.mah.searchbartry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;

    private ArrayAdapter adapter;

    private ArrayList<String> names;

    private OnNameSelectedListener onNameSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        editText = (EditText) findViewById ( R.id.edit_text );
        listView = (ListView) findViewById ( R.id.list_view );

        names = new ArrayList <>();
        names.add("sdf");


        NameDialog nameDialog = new NameDialog ( MainActivity.this, new OnNameSelectedListener () {
            @Override
            public void onNameSelected(String name) {

//                names = new ArrayList <>();
//                names.add(name);

            }
        } );

        nameDialog.show ();



        adapter=new ArrayAdapter ( this,android.R.layout.simple_list_item_1,names);
        listView.setAdapter ( adapter );

        editText.addTextChangedListener ( new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (MainActivity.this).adapter.getFilter ().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );

    }
}
