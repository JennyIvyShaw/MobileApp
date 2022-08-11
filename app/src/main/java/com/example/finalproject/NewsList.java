package com.example.finalproject;

/**
 *This class create amd constructs basic information about a news article
 *
 * @auther jennyShaw
 */
public class NewsList {
    private String title;
    private String url;
    private long id;

    /**
     * Constructs and initializes the title, url, and id of the news article
     *
     * @param title The title of news article
     * @param url The url of news article
     * @param id The id for database
     */
    public NewsList(String title, String url, long id){
        this.title = title;
        this.url = url;
        this.id = id;
    }

    /**
     * Constructs and initializes the title and url of the news article
     *
     * @param title The title of news article
     * @param url The url of news article
     */
    public NewsList(String title, String url){
        this(title, url, 0);
    }


    /**
     * Method returns news title
     *
     * @return News title
     */
    public String getTitle(){return title;}

    /**
     * Method returns news url
     *
     * @return News url
     */
    public String getUrl(){return url;}

    /**
     * Method returns database id
     *
     * @return database id
     */
    public long getId(){return id;}


}
