package com.example.mah.onlineshop.otherClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.GetSpeciphications;

import java.util.List;

public class SpeciphicationsAdapter extends RecyclerView.Adapter<SpeciphicationsAdapter.ViewHolder> {

    private List<GetSpeciphications> items;

    public SpeciphicationsAdapter(List <GetSpeciphications> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_speciphications , viewGroup ,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.userNameSpec.setText ( items.get ( i ).getUsername () );
        viewHolder.phonenumberSpec.setText ( items.get ( i ).getPhoneNumber () );
        viewHolder.addressSpec.setText ( items.get ( i ).getDetail () );
        viewHolder.idSpec.setText ( items.get ( i ).getId () );
        viewHolder.idSpec.setTextIsSelectable(true);
        viewHolder.publishSpec.setText ( items.get ( i ).getCreatedAt () );

    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userNameSpec;
        private TextView phonenumberSpec;
        private TextView addressSpec;
        private TextView idSpec;
        private TextView publishSpec;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            userNameSpec=(TextView) itemView.findViewById ( R.id.username_spec);
            phonenumberSpec=(TextView) itemView.findViewById ( R.id.id_phone_number_spec );
            addressSpec=(TextView) itemView.findViewById ( R.id.address_spec );
            idSpec=(TextView) itemView.findViewById ( R.id.id_spec );
            publishSpec=(TextView) itemView.findViewById ( R.id.publish_date_spec );
        }
    }
}
