package ua.regin.pictures.manager;

import java.util.List;

import rx.Observable;
import ua.regin.pictures.api.entity.Post;

public interface IPostManager {

    Observable<List<Post>> loadRecentPosts();

    Observable<List<Post>> loadPostsBySlug(String slug);

    Observable<List<Post>> search(String search);

}
