package com.example.mah.sqliteproject;

import java.io.Serializable;

public class DataModels implements Serializable{

    private String ID;
    private String name;
    private String family;
    private String age;

    public DataModels() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
