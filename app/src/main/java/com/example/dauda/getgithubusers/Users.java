package com.example.dauda.getgithubusers;

/**
 * Created by Dauda on 8/30/2017.
 */

public class Users {
    //a users should
    private String userName;
    private String avatar_url;
    private String url;


    public String getUserName() {
        return userName;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getHtml_url() {
        return url;
    }


    public Users(String userName, String gitUrl, String avatar_url) {
        this.userName = userName;
        this.avatar_url = avatar_url;
        this.url = gitUrl;

    }

}
