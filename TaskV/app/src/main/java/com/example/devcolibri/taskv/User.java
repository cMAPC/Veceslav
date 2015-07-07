package com.example.devcolibri.taskv;

public class User {
    public String name;
    public String description;
    public String html_url;
    public String fork;

    public User(String name, String description, String html_url){
        this.name = name;
        this.description = description;
        this.html_url = html_url;
    }
    public User(){}

    public String getFork() {
        return fork;
    }

    public void setFork(String fork) {
        this.fork = fork;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}

