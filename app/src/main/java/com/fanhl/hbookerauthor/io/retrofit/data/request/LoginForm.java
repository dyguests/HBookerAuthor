package com.fanhl.hbookerauthor.io.retrofit.data.request;

/**
 * Created by fanhl on 2017/4/7.
 */

public class LoginForm {

    private final String email;
    private final String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
