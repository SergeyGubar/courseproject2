package com.example.sergey.courseproject.entities;

/**
 * Created by sergey on 11/9/17.
 */

public class User {
    private String mEmail;
    private String mPassword;
    private String mRole;


    public User(String email, String password, String role) {
        this.mEmail = email;
        this.mPassword = password;
        this.mRole = role;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getRole() {
        return mRole;
    }
}
