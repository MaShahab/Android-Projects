package com.example.mah.speedmatch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class BestScoreFragment extends Fragment {

    private UserAdapter userAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.best_score_fragment , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);
        configureRanklist();
    }


    private void introduction(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.LIST);
    }

    private void configureRanklist()
    {
        Ranklist ranklistObject = MyPreferenceManager.getInstance(getActivity()).getRanklist();
        Comparator<Users> usersComparator = new Comparator<Users>() {
            @Override
            public int compare(Users x, Users y) {
                if(x.getScore()>y.getScore())
                {
                    return -1;
                }
                else if(x.getScore()<y.getScore())
                {
                    return +1;
                }
                else return 0;
            }
        };
        Collections.sort(ranklistObject.getRanklist(),usersComparator);
        userAdapter = new UserAdapter(ranklistObject.getRanklist());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(userAdapter);
    }
}
