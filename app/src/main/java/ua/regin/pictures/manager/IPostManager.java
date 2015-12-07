package ua.regin.pictures.manager;

import rx.Observable;
import ua.regin.pictures.api.entity.PostInfo;

public interface IPostManager {

    Observable<PostInfo> loadRecentPosts();

    Observable<PostInfo> loadPostsBySlug(String slug);

}
