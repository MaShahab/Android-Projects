package com.example.mah.jsonapplication;

public class User
{
    private Integer score;
    private String name;



    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
