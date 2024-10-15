package com.example.demo.Model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Timestamp;

public class Comment {
    static int cnt =0;
    @Id
    private int commentId;
    private int parentComment;
    private int postId;
    private int countVote;



    private int stateVote;
    private String username;
    @NotEmpty
    private String content;
    private Timestamp timeUp;

    public Comment(){
        this.parentComment=0;
    }

    public int getStateVote() {
        return stateVote;
    }

    public void setStateVote(int stateVote) {
        this.stateVote = stateVote;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getParentComment() {
        return parentComment;
    }

    public void setParentComment(int parentComment) {
        this.parentComment = parentComment;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCountVote() {
        return countVote;
    }

    public void setCountVote(int countVote) {
        this.countVote = countVote;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeUp() {
        String ans=timeUp.toString();
        ans=ans.substring(0,ans.length()-5);
        int hour= Integer.parseInt(ans.substring(11,13));
        if(hour <= 12) ans=ans+" AM";
        else ans=ans+" PM";
        return ans;
    }

    public void setTimeUp(Timestamp timeUp) {
        this.timeUp = timeUp;
    }
}
