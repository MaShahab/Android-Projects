package com.example.mah.sqliteproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterFragment extends Fragment {

    private StudentAdapter studentAdapter;
    private RecyclerView recyclerView;

    private DataBaseHelper dataBaseHelper;

    private List<DataModels> datamodelList = new ArrayList <> ();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_adapter,container,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction(view);

        dataBaseHelper = new DataBaseHelper(getActivity ());

        Cursor res = dataBaseHelper.getAllData ();
        if(res.getCount ()==0)
        {
            Toast.makeText ( getActivity (), "unfortunately nothing exists for showing", Toast.LENGTH_SHORT ).show ();
        }

            while (res.moveToNext ())
            {

                DataModels dataModels = new DataModels ();
                dataModels.setID(String.valueOf(res.getString(0)));
                dataModels.setName ( String.valueOf ( res.getString ( 1 ) ) );
                dataModels.setFamily ( String.valueOf ( res.getString ( 2 ) ) );
                dataModels.setAge ( String.valueOf ( res.getString ( 3 ) ) );

                datamodelList.add(dataModels);
            }

            configureList(datamodelList);

    }

    private void introduction(View view)
    {
        recyclerView = (RecyclerView) view.findViewById ( R.id.recycler );
    }

    private void configureList(List<DataModels> dataModels)
    {
        studentAdapter = new StudentAdapter(dataModels,getActivity ());
        recyclerView.setAdapter ( studentAdapter );
        recyclerView.setLayoutManager (new LinearLayoutManager ( getActivity () ));
    }
}
