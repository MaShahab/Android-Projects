package com.example.mah.whichoneislarger;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    private List<User> items;

    public UserAdapter(List<User> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.name.setText(items.get(i).getName());
        viewHolder.score.setText(String.valueOf(items.get(i).getScore()));
        viewHolder.personNumber.setText(String.valueOf(i+1));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_user_row , viewGroup , false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView score;
        public TextView name;
        public TextView personNumber;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            score = itemView.findViewById(R.id.player_score);
            name = itemView.findViewById(R.id.player_name);
            personNumber = itemView.findViewById(R.id.person_number);
        }
    }
}
