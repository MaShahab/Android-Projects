package com.example.mah.gamespack;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mah.gamespack.models.Ranklist;
import com.example.mah.gamespack.models.Users;

import java.util.List;

public class SpeedMatchUserAdapter extends RecyclerView.Adapter<SpeedMatchUserAdapter.ViewHoler> {

    private List<Users> items;

    public SpeedMatchUserAdapter(List <Users> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_speed_match , viewGroup ,false );
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {

        viewHoler.personNumber2.setText ( String.valueOf(i+1));
        viewHoler.palyerName2.setText ( items.get ( i ).getName () );
        viewHoler.playerScore2.setText (String.valueOf ( items.get ( i ).getScore ()  ) );

    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHoler extends RecyclerView.ViewHolder
    {

        private TextView personNumber2;
        private TextView palyerName2;
        private TextView playerScore2;
        private ImageView imageView2;

        public ViewHoler(@NonNull View itemView) {
            super ( itemView );

            personNumber2=(TextView) itemView.findViewById ( R.id.person_number2 );
            palyerName2=(TextView) itemView.findViewById ( R.id.player_name2 );
            playerScore2=(TextView) itemView.findViewById ( R.id.player_score2 );
            imageView2=(ImageView) itemView.findViewById ( R.id.person_image2 );
        }
    }
}
