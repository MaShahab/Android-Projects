package com.example.mah.notepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class DatasAdapter extends RecyclerView.Adapter<DatasAdapter.ViewHOlder>  {

    private List<DatasModel> items;
    private Context context;
    private MyDatabase database;
    private MaterialSearchBar materialSearchBar;

    public DatasAdapter(List <DatasModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_list_notes,viewGroup,false );
        return new ViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHOlder viewHOlder, final int i) {

        viewHOlder.mytitle.setText ("موضوع : "+ items.get(i).getTitle());
        viewHOlder.mycontent.setText (items.get ( i ).getContent () );
        viewHOlder.mycontent.setTextIsSelectable(true);
        viewHOlder.mydate.setText ("تاریخ : "+ items.get ( i ).getDate () );
        viewHOlder.mytime.setText ("زمان : "+ items.get ( i ).getTime () );

        viewHOlder.content.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                MainContentFragment mainContentFragment = new MainContentFragment ();
                AppCompatActivity MainContentFragment = (AppCompatActivity) view.getContext ();
                Bundle bundle = new Bundle();
                bundle.putString("content_text",items.get(i).getContent ());
                mainContentFragment.setArguments(bundle);
                MainContentFragment.getSupportFragmentManager ().beginTransaction ()
                        .add(R.id.frame_container,mainContentFragment)
                        .addToBackStack(null)
                        .commit ();


            }
        } );

//        Toast.makeText ( context, ""+items.get ( i ).getID (), Toast.LENGTH_SHORT ).show ();

        final IdModel idModel = new IdModel ();
        idModel.setNoteId(items.get(i).getID ());

        viewHOlder.deleteImg.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(final View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setTitle( R.string.alert);
                builder.setMessage("آیا مطمئن هستید که این رکورد پاک شود ؟"+"\n"+"\n");
                builder.setPositiveButton ( "بله", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        database = new MyDatabase ( context );

                        Integer deleteRow = database.deleteData(idModel.getNoteId ());
                        if(deleteRow>0)
                        {
//                            DataListFragment dataListFragment = new DataListFragment ();
//                            AppCompatActivity DataListFragment = (AppCompatActivity) view.getContext ();
//                            DataListFragment.getSupportFragmentManager ().beginTransaction ()
//                                    .add(R.id.frame_container,dataListFragment)
//                                    .addToBackStack(null)
//                                    .commit ();
                            Intent myactivity = new Intent(context.getApplicationContext(), MainActivity.class);
                            myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                            context.getApplicationContext().startActivity(myactivity);
                            Toast.makeText ( context , "با موققیت این رکورد حذف گردید", Toast.LENGTH_LONG ).show ();
                        }
                        else {
                            Toast.makeText ( context , "حذف این رکورد با مشکل مواجه شده است", Toast.LENGTH_SHORT ).show ();
                        }

                    }
                } );


                builder.setNegativeButton ( "خیر", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss ();

                    }
                } );

                builder.show ();

            }
        } );


        viewHOlder.editImg.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                UpdateDataFrag updateDataFrag1 = new UpdateDataFrag ();
                AppCompatActivity UpdateDataFrag = (AppCompatActivity) view.getContext ();
                Bundle bundle = new Bundle();
                bundle.putSerializable("serialize_dataModel",items.get(i));
                updateDataFrag1.setArguments(bundle);
                UpdateDataFrag.getSupportFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,updateDataFrag1 )
                        .addToBackStack ( null )
                        .commit ();


            }
        } );


    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHOlder extends RecyclerView.ViewHolder
    {

        public TextView mytitle;
        public TextView mycontent;
        public TextView mydate;
        public TextView mytime;
        public ImageView deleteImg;
        public ImageView editImg;
        public TextView content;

        public ViewHOlder(@NonNull View itemView) {
            super ( itemView );

            mytitle = (TextView) itemView.findViewById ( R.id.title_template );
            mycontent = (TextView) itemView.findViewById ( R.id.content_template );
            mydate = (TextView) itemView.findViewById ( R.id.date_template );
            mytime = (TextView) itemView.findViewById ( R.id.time_template );
            deleteImg = (ImageView) itemView.findViewById ( R.id.delete_schema );
            editImg = (ImageView) itemView.findViewById ( R.id.edit_schema );
            content = (TextView) itemView.findViewById ( R.id.content_2 );

        }
    }
}
