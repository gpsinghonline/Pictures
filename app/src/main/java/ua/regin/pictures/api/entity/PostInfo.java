package ua.regin.pictures.api.entity;

import java.io.Serializable;
import java.util.List;

public class PostInfo implements Serializable {

    private String status;
    private List<Post> posts;

    public String getStatus() {
        return status;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
