package com.example.mah.speedmatch;

import java.util.ArrayList;
import java.util.List;

public class Ranklist {

    private List<Users> ranklist;

    public Ranklist() {

        ranklist = new ArrayList<>();
    }

    public void addUsertoList(Users users)
    {
        ranklist.add(users);
    }

    public List<Users> getRanklist() {
        return ranklist;
    }

    public void setRanklist(List<Users> ranklist) {
        this.ranklist = ranklist;
    }
}
