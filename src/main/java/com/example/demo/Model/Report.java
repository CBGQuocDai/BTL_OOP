package com.example.demo.Model;

import jakarta.validation.constraints.NotEmpty;

import java.sql.Timestamp;

public class Report {
    private int reportId;
    private int postId,commentId;
    @NotEmpty
    private String content,reason;
    private Timestamp time;
    public Report(){
        this.postId=-1;
        this.commentId=-1;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public @NotEmpty String getContent() {
        return content;
    }

    public void setContent(@NotEmpty String content) {
        this.content = content;
    }

    public @NotEmpty String getReason() {
        return reason;
    }

    public void setReason(@NotEmpty String reason) {
        this.reason = reason;
    }

    public String getTime() {
        String ans=time.toString();
        ans=ans.substring(0,ans.length()-5);
        int hour= Integer.parseInt(ans.substring(11,13));
        if(hour <= 12) ans=ans+" AM";
        else ans=ans+" PM";
        return ans;
    }

    public void setTime(@NotEmpty Timestamp time) {
        this.time = time;
    }
}
