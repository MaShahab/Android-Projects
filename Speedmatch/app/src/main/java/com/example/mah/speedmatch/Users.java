package com.example.mah.speedmatch;

public class Users {

    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
        return "Users{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
