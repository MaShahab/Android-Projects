package com.example.mah.onlineshop.otherClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mah.onlineshop.MainActivity;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.fragments.CommentsFrag;
import com.example.mah.onlineshop.fragments.ProductsDetailsFrag;
import com.example.mah.onlineshop.models.DetailsResponse;
import com.example.mah.onlineshop.models.ProductsModels;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHoler> {

    private List<ProductsModels> items;

    public ProductsAdapter(List <ProductsModels> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_products,viewGroup,false );
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, final int i) {
        viewHoler.name.setText(items.get ( i ).getName ());
        viewHoler.name.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                ProductsDetailsFrag productsDetailsFrag = new ProductsDetailsFrag ();
                AppCompatActivity ProductsDetailsFrag = (AppCompatActivity) view.getContext();
                ProductsDetailsFrag.getSupportFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,productsDetailsFrag )
                        .addToBackStack ( null )
                        .commit ();
                Log.d("TAG","id" + items.get (i).getId ());
//                Log.d("TAG","id" + items.get (i).getName ());
                Bundle bundle = new Bundle();
//                bundle.putString("id",items.get (i).getId ());
                bundle.putSerializable ( "product" , items.get ( i ) );
                productsDetailsFrag.setArguments ( bundle );

//                CommentsFrag commentsFrag = new CommentsFrag ();
//                Bundle bundle1 = new Bundle ();
//                bundle1.putString("productID" ,items.get ( i ).getId ());
//                commentsFrag.setArguments(bundle1);

                ProductsAdapter productsAdapter;

//                Bundle bundleName = new Bundle();
//                bundle.putString("name",items.get (i).getName ());
//                productsDetailsFrag.setArguments ( bundleName );
//
//                Bundle bundleDescription = new Bundle();
//                bundle.putString("description",items.get (i).getDescription ());
//                productsDetailsFrag.setArguments ( bundleDescription );
//
//                Bundle bundlePublish = new Bundle();
//                bundle.putString("publish",items.get (i).getPublishDate ());
//                productsDetailsFrag.setArguments ( bundlePublish );
//
//                Bundle bundleUpdate = new Bundle();
//                bundle.putString("update",items.get (i).getUpdateDate ());
//                productsDetailsFrag.setArguments ( bundleUpdate );
//
//                Bundle bundlePrice = new Bundle();
//                bundle.putString("price",items.get (i).getPrice ());
//                productsDetailsFrag.setArguments ( bundlePrice );
//
//                Bundle bundlePhotoUrl = new Bundle();
//                bundle.putString("photo",items.get (i).getPhotoUrl ());
//                productsDetailsFrag.setArguments ( bundlePhotoUrl );
//
//                Bundle bundleRating = new Bundle();
//                bundle.putString("rating",items.get (i).getRating ());
//                productsDetailsFrag.setArguments ( bundleRating );

//                ProductsModels productsModels = new ProductsModels ();
//                productsModels.setName(items.get ( i ).getName ());
//                Toast.makeText ( ProductsDetailsFrag, ""+productsModels.getName (), Toast.LENGTH_SHORT ).show ();
//                productsModels.setDescription (items.get ( i ).getDescription ());
//                productsModels.setPhotoUrl(items.get ( i ).getPhotoUrl ());
//                productsModels.setPrice (items.get ( i ).getPrice ());
//                productsModels.setPublishDate ( items.get ( i ).getPublishDate () );
//                productsModels.setRating ( items.get ( i ).getRating () );
//                productsModels.setUpdateDate ( items.get ( i ).getUpdateDate () );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHoler extends RecyclerView.ViewHolder
    {
        public Button name;

        public ViewHoler(@NonNull View itemView) {
            super ( itemView );
            name = (Button) itemView.findViewById ( R.id.card_products );
        }
    }
}
