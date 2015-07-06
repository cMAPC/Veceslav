package com.example.devcolibri.taskv;

public class User {
    public String name;
    public String description;
    public String url;

    public User(String name, String description, String url){
        this.name = name;
        this.description = description;
        this.url = url;
    }
    public User(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

}

