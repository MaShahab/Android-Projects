package com.example.mah.chatroom;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mah.chatroom.models.Messages;
import com.example.mah.chatroom.models.Sending;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {


    private List<Messages> items;
    private Sending sending;
    private List<Sending> sendItem;
    private RoomsMessagesFragment roomsMessagesFragment;

    public MessagesAdapter(List<Messages> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_messages , viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.message.setText(items.get(i).getText());
        viewHolder.userName.setText(items.get(i).getUsername());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView userName;
        public TextView message;
        public TextView myName;
        public TextView myMessage;
        public EditText myEdit;
        public Button sendButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.user);
            message = (TextView) itemView.findViewById(R.id.message);
            sendButton =  itemView.findViewById(R.id.button_send);
            myEdit = (EditText) itemView.findViewById (R.id.messsage_edit);

        }
    }
}
