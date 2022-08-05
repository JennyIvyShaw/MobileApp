package com.example.finalproject;

public class NewsList {
    String title;
    String url;
    long id;

    public NewsList(String title, String url, long id){
        this.title = title;
        this.url = url;
        this.id = id;
    }

    //Chaining constructor
    public NewsList(String title, String url){
        this(title, url, 0);
    }

    //Get Methods
    public String getTitle(){return title;}
    public String getUrl(){return url;}
    public long getId(){return id;}


}
