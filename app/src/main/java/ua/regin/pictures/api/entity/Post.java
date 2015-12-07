package ua.regin.pictures.api.entity;

import java.io.Serializable;

public class Post implements Serializable {

    private int id;
    private String slug;
    private String url;
    private String title;
    private String content;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
