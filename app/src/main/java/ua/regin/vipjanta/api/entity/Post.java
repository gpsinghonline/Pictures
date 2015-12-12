package ua.regin.vipjanta.api.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Post implements Serializable {

    @DatabaseField(id = true)
    private int id;

    @DatabaseField
    private String slug;

    @DatabaseField
    private String url;

    @DatabaseField
    private String title;

    @DatabaseField
    private String content;

    @DatabaseField
    private String imageUrl;

    @DatabaseField
    private String localPath;

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

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
