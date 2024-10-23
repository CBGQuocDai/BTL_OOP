package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


public class User {
    static int cnt=0;
    @Id
    private int userId,gender;
    @NotEmpty
    private String username, password,email;
    private String avatar,role;

    //thông tin bổ xung
    private int countFollow, countPost;

    public int getCountFollow() {
        return countFollow;
    }

    public void setCountFollow(int countFollow) {
        this.countFollow = countFollow;
    }

    public int getCountPost() {
        return countPost;
    }

    public void setCountPost(int countPost) {
        this.countPost = countPost;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(){
        this.userId =++cnt;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
