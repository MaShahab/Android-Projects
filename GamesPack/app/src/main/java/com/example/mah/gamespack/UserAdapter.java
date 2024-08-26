package com.example.mah.gamespack;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mah.gamespack.models.Users;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<Users> items;

    public UserAdapter(List<Users> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.speed_match_template , viewGroup ,false);
        return new ViewHolder(view);
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

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView score;
        public TextView name;
        public TextView personNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            score = (TextView) itemView.findViewById(R.id.player_score);
            name = (TextView) itemView.findViewById(R.id.player_name);
            personNumber = (TextView) itemView.findViewById(R.id.person_number);
        }
    }

}
