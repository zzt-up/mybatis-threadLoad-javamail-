package com.zzt.demo.model;

public class Author {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String bio;

    private String favouriteSection;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    public String getFavouriteSection() {
        return favouriteSection;
    }

    public void setFavouriteSection(String favouriteSection) {
        this.favouriteSection = favouriteSection == null ? null : favouriteSection.trim();
    }
}