package com.example.mah.onlineshop.otherClasses;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.fragments.CommentsFrag;
import com.example.mah.onlineshop.models.PeoductsModels2;
import com.example.mah.onlineshop.models.ProductsModels;

import java.util.ArrayList;
import java.util.List;

public class ProductsDetailsAdapter extends RecyclerView.Adapter<ProductsDetailsAdapter.ViewHoler> {
    private List<PeoductsModels2> items= new ArrayList <> ();

    public ProductsDetailsAdapter(List <PeoductsModels2> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_details , viewGroup ,false );
        return new ViewHoler ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.id.setText ( items.get ( i ).getId () );
        viewHoler.name.setText(items.get(i).getName ());
        viewHoler.publish.setText(items.get(i).getPublishDate ());
        viewHoler.update.setText ( items.get ( i ).getUpdateDate () );
        viewHoler.price.setText ( items.get ( i ).getPrice () );
        viewHoler.rating.setText ( items.get ( i ).getRating () );
        viewHoler.photourl.setText ( items.get ( i ).getPhotoUrl () );
        viewHoler.description.setText ( items.get ( i ).getDescription());

        CommentsFrag commentsFrag = new CommentsFrag ();
        Bundle bundle = new Bundle ();
        bundle.putString("productID" ,items.get ( i ).getId ());
        commentsFrag.setArguments(bundle);
    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    //    @NonNull
//    @Override
//    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_details , viewGroup ,false );
//        return new ViewHoler ( view );
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
//        viewHoler.id.setText ( items.get ( i ).getId () );
//        viewHoler.name.setText(items.get(i).getName ());
//        viewHoler.publish.setText(items.get(i).getPublishDate ());
//        viewHoler.update.setText ( items.get ( i ).getUpdateDate () );
//        viewHoler.price.setText ( items.get ( i ).getPrice () );
//        viewHoler.rating.setText ( items.get ( i ).getRating () );
//        viewHoler.photourl.setText ( items.get ( i ).getPhotoUrl () );
//        viewHoler.description.setText ( items.get ( i ).getDescription () );
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size ();
//    }
//
    class ViewHoler extends RecyclerView.ViewHolder
    {
        private TextView id;
        private TextView name;
        private TextView publish;
        private TextView update;
        private TextView price;
        private TextView rating;
        private TextView photourl;
        private TextView description;

        public ViewHoler(@NonNull View itemView) {
            super ( itemView );
            id=(TextView) itemView.findViewById ( R.id.id_product );
            name=(TextView) itemView.findViewById ( R.id.id_name );
            publish=(TextView) itemView.findViewById ( R.id.id_publish );
            update=(TextView) itemView.findViewById ( R.id.id_update );
            price=(TextView) itemView.findViewById ( R.id.id_price );
            rating=(TextView) itemView.findViewById ( R.id.id_rating );
            photourl=(TextView) itemView.findViewById ( R.id.id_photoURL );
            description=(TextView) itemView.findViewById ( R.id.id_description );
        }
    }
}
