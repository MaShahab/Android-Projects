package com.example.mah.whichoneislarger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class BestScoreFragment extends Fragment
{
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.best_score_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);

//        User bestuser = MyPreferenceManager.getInstance(getActivity()).getBestUser();
//        bestscore.setText("The best score belongs to " +bestuser.getName() + " with " + bestuser.getScore() + " points!");
//        if(bestuser.getName()==null){return;}


        configureRanklist();

    }

    private void configureRanklist()
    {
        Ranklist ranklistObject = MyPreferenceManager.getInstance(getActivity()).getRanklist();
        Comparator<User> userComparator = new Comparator<User>() {
            @Override
            public int compare(User x, User y) {
             if(x.getScore()>y.getScore()){
                 return -1;
             }
             else if(x.getScore()<y.getScore()){
                 return +1;
             }
             else return 0;
            }
        };
        Collections.sort(ranklistObject.getRanklist(),  userComparator);
        userAdapter = new UserAdapter(ranklistObject.getRanklist());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(userAdapter);
    }

    private void introduction(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.ranklist);
    }

}
