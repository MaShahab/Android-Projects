package com.example.mah.sqliteproject;

import java.util.ArrayList;
import java.util.List;

public class StudentsModel {
    public List<DataModels> dataModels;

    public StudentsModel() {
        dataModels = new ArrayList <> ();
    }

    public List <DataModels> getDataModels() {
        return dataModels;
    }

    public void setDataModels(List <DataModels> dataModels) {
        this.dataModels = dataModels;
    }
}
