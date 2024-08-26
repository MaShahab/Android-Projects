package com.example.mah.sqliteproject;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHOlder> {

    private DataBaseHelper dataBaseHelper;
    private List<DataModels> items;
    private Context context;

    public StudentAdapter(List <DataModels> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_datas , viewGroup ,false );
        return new ViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder viewHOlder, final int i) {

        DataModels dataModels = items.get ( i );

        viewHOlder.id.setText ( "ID : "+items.get ( i ).getID ());
        viewHOlder.name.setText ( "name : "+items.get ( i ).getName () );
        viewHOlder.family.setText ( "family : "+items.get ( i ).getFamily () );
        viewHOlder.age.setText ( "age : "+items.get ( i ).getAge ());

        viewHOlder.delete.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelpe = new DataBaseHelper(context);
                Integer deleteRow = dataBaseHelpe.deleteData(items.get ( i ).getID ());
                if(deleteRow>0)
                {
                    Toast.makeText ( context , "successfully deleted ", Toast.LENGTH_LONG ).show ();
                }
                else {
                    Toast.makeText ( context , "unfortunately deleting operation counter with fault", Toast.LENGTH_SHORT ).show ();
                }

            }
        } );

        viewHOlder.edit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                EditDialog editDialog = new EditDialog ( context, new OneditInfoListener () {
                    @Override
                    public void onInfo(String username, String family, String age) {

                        DataBaseHelper dataBaseHelper = new DataBaseHelper ( context );
                        boolean isUpdate = dataBaseHelper.updateData(items.get ( i ).getID (),username,family,age);
                        if(isUpdate==true)
                        {
                            Toast.makeText ( context, "The student with "+items.get ( i ).getID () + " id updated successfully", Toast.LENGTH_SHORT ).show ();
                        }
                        else {
                            Toast.makeText ( context, "Unfortunately occurred a disorder to updating data ! ", Toast.LENGTH_SHORT ).show ();
                        }

                    }
                } );
                editDialog.show ();
            }
        } );

    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHOlder extends RecyclerView.ViewHolder
    {

        private TextView id;
        private TextView name;
        private TextView family;
        private TextView age;

        private ImageView edit;
        private ImageView delete;

        public ViewHOlder(@NonNull View itemView) {
            super ( itemView );

            id = (TextView) itemView.findViewById ( R.id.id_temp );
            name = (TextView) itemView.findViewById ( R.id.id_name_temp );
            family = (TextView) itemView.findViewById ( R.id.id_family_temp );
            age = (TextView) itemView.findViewById ( R.id.id_age_temp );
            edit = (ImageView) itemView.findViewById ( R.id.img_edit );
            delete = (ImageView) itemView.findViewById ( R.id.img_del );

        }
    }
}
