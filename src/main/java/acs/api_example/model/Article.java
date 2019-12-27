package acs.api_example.model;

public class Article {

    private String title;
    private String author;
    private String link;

    public Article(String title, String author, String link) {
        this.title = title;
        this.author = author;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLink() {
        return link;
    }


}
