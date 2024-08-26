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
import com.example.mah.onlineshop.fragments.TestFrag;
import com.example.mah.onlineshop.models.CommentModels;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHoler> {

    private List<CommentModels> items1;

    public CommentsAdapter(List <CommentModels> items) {
        this.items1 = items;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_comments , viewGroup ,false );
        return new ViewHoler ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.name.setText(items1.get ( i ).getName ());
        viewHoler.title.setText ( items1.get ( i ).getTitle () );
        viewHoler.text.setText ( items1.get ( i ).getText () );
        viewHoler.date.setText ( items1.get ( i ).getDate () );
        viewHoler.commentId.setText ( items1.get ( i ).getId () );
        viewHoler.publishId.setText ( items1.get ( i ).getCreatedAt () );
        viewHoler.productId.setText ( items1.get ( i ).getProductId () );
        viewHoler.updateId.setText ( items1.get ( i ).getUpdatedAt () );

        if(viewHoler.title.getText ()==items1.get ( i ).getTitle ())
        {
            viewHoler.errorTEXT.setText("برای این محصول نظری ثبت نشده است");
            viewHoler.errorTEXT.setVisibility ( View.INVISIBLE );
        }
//        if(items1.get ( i ).getTitle ()== null)
//        {
//            viewHoler.errorTEXT.setText("برای این محصول نظری ثبت نشده است");
//        }

        CommentsFrag commentsFrag = new CommentsFrag ();
        Bundle bundle = new Bundle();
        bundle.putSerializable("items_comments" , items1.get(i));
        commentsFrag.setArguments(bundle);

//        TestFrag testFrag = new TestFrag ();
//        Bundle bundle = new Bundle ();
//        bundle.putSerializable("salam" , items1.get ( i ));
//        testFrag.setArguments ( bundle );

//        TestFrag testFrag = new TestFrag ();
//        Bundle bundle = new Bundle();
//        bundle.putString("DDDDDDDDDDDD" , items1.get ( i ).getId ());
//        testFrag.setArguments ( bundle );




    }

    @Override
    public int getItemCount() {
        return items1.size ();
    }

    class ViewHoler extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView title;
        private TextView text;
        private TextView date;
        private TextView commentId;
        private TextView publishId;
        private TextView productId;
        private TextView updateId;
        private TextView errorTEXT;

        public ViewHoler(@NonNull View itemView) {
            super ( itemView );

            name = (TextView) itemView.findViewById ( R.id.name_comment_id );
            title = (TextView) itemView.findViewById ( R.id.title_comment_id );
            text = (TextView) itemView.findViewById ( R.id.text_comment_id );
            date = (TextView) itemView.findViewById ( R.id.date_comment_id );
            commentId = (TextView) itemView.findViewById ( R.id.comment_id );
            publishId = (TextView) itemView.findViewById ( R.id.publish_comment_id );
            productId = (TextView) itemView.findViewById ( R.id.product_comment_id );
            updateId = (TextView) itemView.findViewById ( R.id.update_comment_id );
            errorTEXT=(TextView) itemView.findViewById ( R.id.error_txt );
        }
    }
}
