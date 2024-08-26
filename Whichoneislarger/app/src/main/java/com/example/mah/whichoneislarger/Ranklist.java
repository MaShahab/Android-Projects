package com.example.mah.whichoneislarger;

import java.util.ArrayList;
import java.util.List;

public class Ranklist {

    private List<User> ranklist;

    public Ranklist() {
        ranklist = new ArrayList<>();
    }

    public void addUserToList(User user)
    {
        ranklist.add(user);
    }

    public List<User> getRanklist() {
        return ranklist;
    }

    public void setRanklist(List<User> ranklist) {
        this.ranklist = ranklist;
    }
}
