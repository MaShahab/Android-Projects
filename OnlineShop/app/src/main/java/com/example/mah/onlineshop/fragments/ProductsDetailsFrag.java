package com.example.mah.onlineshop.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mah.onlineshop.Data.DetailsController;
import com.example.mah.onlineshop.Data.OrderProductController;
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.dialogsPackage.NumberDialog;
import com.example.mah.onlineshop.interfacesPackage.OnNumberSelectedListener;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.DetailsResponse;
import com.example.mah.onlineshop.models.PeoductsModels2;
import com.example.mah.onlineshop.models.ProductOrdering;
import com.example.mah.onlineshop.models.ProductsId;
import com.example.mah.onlineshop.models.ProductsModels;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;
import com.example.mah.onlineshop.otherClasses.ProductsDetailsAdapter;

import java.io.InputStream;
import java.util.List;

public class ProductsDetailsFrag extends Fragment {

//    private RecyclerView recyclerView;w
//    private List<PeoductsModels2> productsModels = new ArrayList <> ();
    private ProductsDetailsAdapter productsDetailsAdapter;

    private DetailsResponse detailsResponse =new DetailsResponse ();
    private TextView productID;
    private TextView publishdateID;
    private TextView updatedateID;
    private TextView nameID;
    private TextView priceID;
    private TextView descriptionID;
    private TextView photoURLID;
    private TextView ratingID;
    private ImageView imageView;
    private ProgressBar progressBar;

    private Button commentBtn;
    private Button orderBtn;
//    private String productId2 = getArguments ().getString("id");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_details_products , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );


        introduction(view);

        DetailsController detailsController = new DetailsController(productsDetailsCallback);

      //  String productId = getArguments ().getString("id");
        final ProductsModels productsModels = (ProductsModels) getArguments ().getSerializable ( "product");
        Log.d ( "TAG" , "productModel is:"  + productsModels.getName ());
        Log.d ( "TAG" , "productModel is:"  + productsModels.getDescription ());
        Log.d ( "TAG" , "productModel is:"  + productsModels.getId ());

//        Toast.makeText ( getActivity (), "getname" + productsModels.getName (), Toast.LENGTH_SHORT ).show ();
//        Toast.makeText ( getActivity (), "getDescription" + productsModels.getDescription (), Toast.LENGTH_SHORT ).show ();

//        CommentsFrag commentsFrag = new CommentsFrag ();
//        Bundle bundle = new Bundle ();
//        bundle.putString("productID" ,productsModels.getId ());
//        commentsFrag.setArguments(bundle);

        MypreferenceManager.getInstance ( getActivity () ).putID(productsModels.getId ());

        productID.setText("نام محصول : "+ productsModels.getName ());
        publishdateID.setText("تاریخ انتشار : "+ productsModels.getUpdateDate ());
        updatedateID.setText("تاریخ بروز رسانی : "+ productsModels.getUpdateDate ());
        nameID.setText("آی دی محصول : "+ productsModels.getId ());
        nameID.setTextIsSelectable(true);
        priceID.setText("قیمت : "+ productsModels.getPrice () + " تومان ");
        descriptionID.setText("در باره محصول : "+ productsModels.getDescription ());
//        photoURLID.setText(productsModels.getPhotoUrl ());
        ratingID.setText ("امتیاز محصول : "+ productsModels.getRating () +" / 5" );
        new DownloadImageTask(imageView).execute(productsModels.getPhotoUrl ());


        orderBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                NumberDialog numberDialog = new NumberDialog ( getActivity (), new OnNumberSelectedListener () {

                    @Override
                    public void onNumberSelected(String numbers , String username) {
//                        Toast.makeText ( getActivity (), "numbers : "+numbers, Toast.LENGTH_SHORT ).show ();
                        OrderProductController productController = new OrderProductController(orderProductsCallback);
                        ProductOrdering productOrdering = new ProductOrdering ();
                        productOrdering.setCount(numbers);
                        productOrdering.setProductId(productsModels.getId ());
                        productOrdering.setUsername(username);
                        productController.start("Bearer" + MypreferenceManager.getInstance ( getActivity () ).getAccessToken (),productOrdering);

                        DetailsOrderFragment detailsOrderFragment = new DetailsOrderFragment ();
                        getFragmentManager ().beginTransaction ()
                                .add (R.id.frame_container,detailsOrderFragment)
                                .addToBackStack ( null )
                                .commit ();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("productOrderClass" , productOrdering);
                        detailsOrderFragment.setArguments(bundle);

                    }

//                    @Override
//                    public void onNumberSelected(String numbers) {
//
//                    }
//
//                    @Override
//                    public void onUsernameSelected(String username) {
//                        Toast.makeText ( getActivity (), "username : "+username, Toast.LENGTH_SHORT ).show ();
////                        MypreferenceManager.getInstance ( getActivity () ).putUsernameOrder ( username );
////                        productController.start("Bearer" + MypreferenceManager.getInstance ( getActivity () ).getAccessToken (),productOrdering);
//                    }


                } );
                numberDialog.show ();
            }
        } );

        commentBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                CommentsFrag commentsFrag = new CommentsFrag ();
                getFragmentManager ().beginTransaction ()
                        .add(R.id.frame_container,commentsFrag)
                        .addToBackStack ( null )
                        .commit ();
            }
        } );



        ProductsId productsId = new ProductsId ();
//        productsId.setProductsId(productId);

        detailsController.start( "Bearer" +MypreferenceManager.getInstance ( getActivity () ).getAccessToken (),productsId);
//        Toast.makeText ( getActivity (), ""+productId, Toast.LENGTH_SHORT ).show ();
//        Log.d ( "TAG" , "productsId : "+productId);

        Intent intent = getActivity ().getIntent ();
       // Bundle extras = intent.getExtras();
        //String username_string = extras.getString("EXTRA_USERNAME");
        //String password_string = extras.getString("EXTRA_PASSWORD");



        //ProductsModels productsModels = new ProductsModels ();
//        productID.setText(productId);



    }

    public ShopsAPIS.OrderProductsCallback orderProductsCallback = new ShopsAPIS.OrderProductsCallback () {
        @Override
        public void onResponse(ProductOrdering productOrdering) {

        }

        @Override
        public void onfailure(String cause) {

        }
    };

    public ShopsAPIS.ProductsDetailsCallback productsDetailsCallback = new ShopsAPIS.ProductsDetailsCallback () {
        @Override
        public void onResponse(List <PeoductsModels2> peoductsModels2s) {
//            configureList (peoductsModels2s);
        }

        @Override
        public void onFailure(String cause) {

        }
    };


    private void introduction(View view)
    {
//        recyclerView=(RecyclerView) view.findViewById ( R.id.recycler );
        productID=(TextView)view.findViewById (R.id.id_product);
        publishdateID=(TextView) view.findViewById ( R.id.publishDate_id );
        updatedateID=(TextView) view.findViewById ( R.id.updateDate_id );
        nameID=(TextView) view.findViewById(R.id.name_id);
        priceID=(TextView) view.findViewById ( R.id.price_id );
        descriptionID=(TextView) view.findViewById ( R.id.description_id );
        photoURLID=(TextView) view.findViewById ( R.id.photoUrl_id );
        ratingID=(TextView) view.findViewById ( R.id.rating_id );
        imageView=(ImageView) view.findViewById ( R.id.photoUrl_image );
        commentBtn=(Button) view.findViewById ( R.id.button_comment );
        orderBtn=(Button)view.findViewById ( R.id.button_order );
        progressBar=(ProgressBar) view.findViewById ( R.id.progressbar_image );
    }

//    private void configureList(List<PeoductsModels2> peoductsModels2s )
//    {
//        productsDetailsAdapter=new ProductsDetailsAdapter(peoductsModels2s);
//        recyclerView.setAdapter (productsDetailsAdapter);
//        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
////        Collections.sort ( peoductsModels2s, new Comparator <PeoductsModels2> () {
////            @Override
////            public int compare(PeoductsModels2 x, PeoductsModels2 y) {
////                return y.getId ().compareTo ( x.getId () );
////            }
////        } );
//    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            progressBar.setVisibility (View.INVISIBLE);
        }
    }


}
