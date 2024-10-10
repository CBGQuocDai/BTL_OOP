package com.example.demo.Model;


import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;


import java.sql.Timestamp;


public class Post {
    private static int cnt=0;
    @Id
    private int postId;
    private int userId;
    @NotEmpty(message = "Name is required")
    private String title;
    @NotEmpty(message = "Name is required")
    private String tags;
    @NotEmpty(message = "Name is required")
    private String type;
    @NotEmpty(message = "Name is required")
    private String content;
    private Timestamp timeUp;
    public Post(){
        this.type="post";
        this.postId=++cnt;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "Name is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Name is required") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Name is required") String getTags() {
        return tags;
    }

    public void setTags(@NotEmpty(message = "Name is required") String tags) {
        this.tags = tags;
    }

    public @NotEmpty(message = "Name is required") String getType() {
        return type;
    }

    public void setType(@NotEmpty(message = "Name is required") String type) {
        this.type = type;
    }

    public @NotEmpty(message = "Name is required") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Name is required") String content) {
        this.content = content;
    }

    public String getTimeUp() {
        String ans=timeUp.toString();
        ans=ans.substring(0,ans.length()-5);
        int hour= Integer.parseInt(ans.substring(11,13));
        if(hour <= 12) ans=ans+"AM";
        else ans=ans+"PM";
        return ans;
    }

    public void setTimeUp(Timestamp timeUp) {
        this.timeUp = timeUp;
    }
}
