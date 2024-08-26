package com.example.mah.onlineshop.otherClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.InquiryInCartModel;

import java.util.List;

public class ProductInquiryAdapter extends RecyclerView.Adapter<ProductInquiryAdapter.ViewHolder> {

    private List<InquiryInCartModel> items;
    private String userName;

    public ProductInquiryAdapter(List <InquiryInCartModel> items, String userName) {
        this.items = items;
        this.userName = userName;
    }

    //    public ProductInquiryAdapter(List <InquiryInCartModel> items) {
//        this.items = items;
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_inquiry , viewGroup ,false );
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.orderUsername.setText(items.get ( i ).getUsername ());
//        viewHolder.orderCount.setText (String.valueOf ( items.get ( i ).getCount ()));
        viewHolder.orderProductId.setText ( items.get ( i ).getProductId () );
        viewHolder.orderPublish.setText ( items.get ( i ).getCreatedAt () );
        viewHolder.orderInquiry.setText ( items.get ( i ).getId () );

    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView orderUsername;
//        public TextView orderCount;
        public TextView orderProductId;
        public TextView orderPublish;
        public TextView orderInquiry;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            orderUsername=(TextView) itemView.findViewById ( R.id.username_order );
//            orderCount=(TextView) itemView.findViewById ( R.id.id_count );
            orderProductId=(TextView) itemView.findViewById ( R.id.product_id_order );
            orderPublish=(TextView) itemView.findViewById ( R.id.date_publish );
            orderInquiry =(TextView) itemView.findViewById ( R.id.id_inquiry );
        }
    }

}
