package com.example.mah.notepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataListFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button addNoteButton;

    private DatasAdapter datasAdapter;

    private MyDatabase myDatabase;

    private List<DatasModel> datasModelList = new ArrayList <>();

    private MaterialSearchBar materialSearchBar;

    private List<String> suggestList = new ArrayList <> ();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_list_notes , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        LocalBroadcastManager.getInstance ( getActivity () ).sendBroadcast(new Intent("open_notes_page"));

        introduction(view);
        myDatabase = new MyDatabase ( getActivity () );


        materialSearchBar.setHint("search");
        materialSearchBar.setCardViewElevation(10);

        loadSuggestList();

        materialSearchBar.addTextChangeListener ( new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<String> suggest = new ArrayList <> ();
                for(String search:suggestList)
                {
                    suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );

        materialSearchBar.setOnSearchActionListener ( new MaterialSearchBar.OnSearchActionListener () {
            @Override
            public void onSearchStateChanged(boolean enabled) {

                if(!enabled)
                {
                    recyclerView.setAdapter(datasAdapter);
                }

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                startSearch(text.toString ());

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        } );

        datasAdapter = new DatasAdapter ( datasModelList , getActivity () );

        recyclerView.setAdapter ( datasAdapter );

        Cursor res = myDatabase.getAllData ();
        if(res.getCount ()==0)
        {
//            Toast.makeText ( getActivity (), "هیج نوتی تا کنون ثبت نشده است ! برای ثبت نوت جدید روی دکمه به اضافه کلیک کنید", Toast.LENGTH_LONG ).show ();
        }

        while (res.moveToNext ())
        {
            TitleModel titleModel = new TitleModel ();
            DatasModel datasModel = new DatasModel ();
            datasModel.setID ( String.valueOf ( res.getString(0)));
            datasModel.setTitle(String.valueOf(res.getString(1)));
//            MyPreferenceManager.getInstance(getActivity ()).putTitle(String.valueOf(res.getString(1)));
            titleModel.setMyTitle(String.valueOf ( res.getString(1)));
            datasModel.setContent ( String.valueOf ( res.getString ( 2 ) ) );
            datasModel.setDate ( String.valueOf ( res.getString ( 3 ) ) );
            datasModel.setTime ( String.valueOf ( res.getString ( 4 ) ) );

            datasModelList.add(datasModel);
        }

        configureList(datasModelList);



        addNoteButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                AddDataFragment addDataFragment = new AddDataFragment ();
                getFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,addDataFragment )
                        .addToBackStack ( null )
                        .commit ();
            }
        } );


//        ArrayAdapter adapter = new ArrayAdapter(getActivity (),android.R.layout.simple_list_item_1,source);
//        listView.setAdapter(adapter);

//        loadSuggestList ();

//        materialSearchBar.addTextChangeListener ( new TextWatcher () {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                List<String> suggest = new ArrayList <> ();
//                for(String search:suggestList)
//                {
//                    suggest.add(search);
//                }
//                materialSearchBar.setLastSuggestions(suggest);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        } );
//        materialSearchBar.setOnSearchActionListener ( new MaterialSearchBar.OnSearchActionListener () {
//            @Override
//            public void onSearchStateChanged(boolean enabled) {
//                if(!enabled)
//                {
//                    recyclerView.setAdapter(datasAdapter);
//                }
//            }
//
//            @Override
//            public void onSearchConfirmed(CharSequence text) {
//                startSearch(text.toString ());
//            }
//
//            @Override
//            public void onButtonClicked(int buttonCode) {
//
//            }
//        } );


//        suggestList.add(MyPreferenceManager.getInstance ( getActivity () ).getmyTitle ());
//        ArrayAdapter adapter = new ArrayAdapter(getActivity (),android.R.layout.simple_list_item_1,suggestList);
//        listView.setAdapter(adapter);
//        editText.addTextChangedListener ( new TextWatcher () {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        } );




        datasAdapter = new DatasAdapter(datasModelList,getActivity ());
        recyclerView.setAdapter(datasAdapter);
    }

    private void loadSuggestList()
    {
        suggestList = myDatabase.getTitle();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    private void startSearch(String s)
    {
        datasAdapter = new DatasAdapter(datasModelList,getActivity ());
        recyclerView.setAdapter ( datasAdapter );
    }

    private void introduction(View view)
    {
        recyclerView = (RecyclerView) view.findViewById ( R.id.recycler );
        addNoteButton = (Button) view.findViewById ( R.id.btn_add_new_note );
        materialSearchBar = (MaterialSearchBar) view.findViewById ( R.id.material_search );
//        editText = (EditText) view.findViewById ( R.id.edit_search );
//        listView = (ListView) view.findViewById ( R.id.list_view );
    }

    private void configureList(List<DatasModel> datasModels)
    {
        datasAdapter=new DatasAdapter(datasModels,getActivity ());
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
        recyclerView.setAdapter(datasAdapter);
    }

//    private void loadSuggestList()
//    {
//        List<String> mylist = new ArrayList <> ();
//        mylist.add("ali");
//        mylist.add("ahmad");
//        suggestList = mylist;
//    }
}
