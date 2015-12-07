package ua.regin.pictures.manager.impl;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.regin.pictures.api.ApiManager;
import ua.regin.pictures.api.entity.PostInfo;
import ua.regin.pictures.manager.IPostManager;

@EBean(scope = EBean.Scope.Singleton)
public class PostManager implements IPostManager {

    @Bean
    protected ApiManager apiManager;

    private API api;

    @AfterInject
    protected void afterInject() {
        api = apiManager.getRestAdapter().create(API.class);
    }

    @Override
    public Observable<PostInfo> loadRecentPosts() {
        return api.loadRecentPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PostInfo> loadPostsBySlug(String slug) {
        return api.loadPostsBySlug(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private interface API {

        @GET("/get_recent_posts/")
        Observable<PostInfo> loadRecentPosts();

        @GET("/get_category_posts/")
        Observable<PostInfo> loadPostsBySlug(@Query("slug") String slug);
    }
}