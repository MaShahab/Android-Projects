package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentsResponse {
    @SerializedName ( "results" )
    private List<CommentModels> comments;

    public List <CommentModels> getComments() {
        return comments;
    }

    public void setComments(List <CommentModels> comments) {
        this.comments = comments;
    }
}
