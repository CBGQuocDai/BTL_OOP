package com.example.demo.Model;


import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.sql.Timestamp;


public class PostModel {
    @Id
    private String postId;
    private String userId;
    @NotEmpty(message = "Name is required")
    private String title;
    @NotEmpty(message = "Name is required")
    private String tags;
    @NotEmpty(message = "Name is required")
    private String type;
    @NotEmpty(message = "Name is required")
    private String content;
    private Timestamp timeUp;
    public PostModel(){
        this.type="post";
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTimeUp() {
        return timeUp;
    }

    public void setTimeUp(Timestamp timeUp) {
        this.timeUp = timeUp;
    }
}
