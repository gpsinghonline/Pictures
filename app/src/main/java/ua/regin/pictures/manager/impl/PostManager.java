package ua.regin.pictures.manager.impl;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.regin.pictures.api.ApiManager;
import ua.regin.pictures.api.entity.Post;
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
    public Observable<List<Post>> loadRecentPosts() {
        return api.loadRecentPosts()
                .map(PostInfo::getPosts)
                .map(postList -> Stream.of(postList).map(post -> {
                    Document document = Jsoup.parse(post.getContent());
                    Element image = document.select("img").first();
                    if (image != null) {
                        String url = image.absUrl("src");
                        post.setImageUrl(url);
                    }
                    return post;
                }).collect(Collectors.toList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Post>> loadPostsBySlug(String slug) {
        return api.loadPostsBySlug(slug)
                .map(PostInfo::getPosts)
                .map(postList -> Stream.of(postList).map(post -> {
                    Document document = Jsoup.parse(post.getContent());
                    Element image = document.select("img").first();
                    if (image != null) {
                        String url = image.absUrl("src");
                        post.setImageUrl(url);
                    }
                    return post;
                }).collect(Collectors.toList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Post>> search(String search) {
        return api.search(search)
                .map(PostInfo::getPosts)
                .map(postList -> Stream.of(postList).map(post -> {
                    Document document = Jsoup.parse(post.getContent());
                    Element image = document.select("img").first();
                    if (image != null) {
                        String url = image.absUrl("src");
                        post.setImageUrl(url);
                    }
                    return post;
                }).collect(Collectors.toList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private interface API {

        @GET("/get_recent_posts/")
        Observable<PostInfo> loadRecentPosts();

        @GET("/get_category_posts/")
        Observable<PostInfo> loadPostsBySlug(@Query("slug") String slug);

        @GET("/get_search_results/")
        Observable<PostInfo> search(@Query("search") String search);
    }
}