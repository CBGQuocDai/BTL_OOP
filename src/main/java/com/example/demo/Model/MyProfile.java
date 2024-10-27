/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Model;

import jakarta.persistence.Id;

/**
 *
 * @author Admin
 */
public class MyProfile {

    @Id
    private int UserID;

    private String UserName;
    private String Password;
    private String Email;
    private String password;
    private String Avatar;
    private String Gender;

    public MyProfile() {

    }

    public MyProfile(int UserID, String UserName, String Password, String Email, String password, String Avatar, String Gender) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.password = password;
        this.Avatar = Avatar;
        this.Gender = Gender;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

}
