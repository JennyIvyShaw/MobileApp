package com.example.finalproject;

public class NewsList {
    String title;
    String description;
    String date;
    String url;
    long id;

    public NewsList(String title, String url, long id){
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public NewsList(String title, String description, String url, long id){
        this.title = title;
        this.description = description;
        this.url = url;
        this.id = id;
    }

    public NewsList(String title, String description, String date, String url, long id){
        this.title = title;
        this.description = description;
        this.date = date;
        this.url = url;
        this.id = id;
    }


    //Chaining constructor
    public NewsList(String title, String url){
        this(title, url, 0);
    }

    public NewsList(String title, String url, String description){
        this(title, url, description, 0);
    }

    //Getter Methods
    public String getTitle(){return title;}
    public String getUrl(){return url;}
    public String getDescription(){return description;}
    public String getDate(){return date;}
    public long getId(){return id;}


}
