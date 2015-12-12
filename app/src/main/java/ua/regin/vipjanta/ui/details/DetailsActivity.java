package ua.regin.vipjanta.ui.details;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.j256.ormlite.dao.Dao;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.sql.SQLException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.regin.vipjanta.R;
import ua.regin.vipjanta.api.entity.Post;
import ua.regin.vipjanta.db.DatabaseHelper;
import ua.regin.vipjanta.ui.BaseActivity;
import ua.regin.vipjanta.utils.ProgressDialogHelper;

@EActivity(R.layout.activity_details)
@OptionsMenu(R.menu.menu_details)
public class DetailsActivity extends BaseActivity {

    public static final String IMAGE_TRANSACTION_NAME = "image:transaction";

    @Bean
    protected ProgressDialogHelper progressDialogHelper;

    @Extra
    protected Post post;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected ImageView imageView;

    @OrmLiteDao(helper = DatabaseHelper.class)
    protected Dao<Post, Integer> postDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.postponeEnterTransition(this);
    }

    @AfterViews
    protected void afterViews() {
        ViewCompat.setTransitionName(imageView, IMAGE_TRANSACTION_NAME);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(ignored -> onBackPressed());
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle(post.getTitle());
        }
        Picasso.with(getContext()).load(post.getImageUrl()).fit().centerInside().into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                ActivityCompat.startPostponedEnterTransition(DetailsActivity.this);
            }

            @Override
            public void onError() {

            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @OptionsItem(R.id.action_share)
    protected void shareClicked() {
        progressDialogHelper.showProgressDialog();
        Observable.just(null)
                .subscribeOn(Schedulers.io())
                .map(v -> {
                    try {
                        return Picasso.with(this).load(post.getImageUrl()).get();
                    } catch (IOException e) {
                        throw new RuntimeException("Unknown image");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bitmap -> {
                    String bitmapUri = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
                    Uri bmpUri = Uri.parse(bitmapUri);
                    final Intent emailIntent1 = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    emailIntent1.setType("image/png");
                    startActivity(Intent.createChooser(emailIntent1, "Share via"));
                }).subscribe(v -> progressDialogHelper.dismissDialog(), this::handleError);
    }

    @Override
    public void handleError(Throwable e) {
        super.handleError(e);
        progressDialogHelper.dismissDialog();
    }

    @OptionsItem(R.id.action_download)
    protected void downloadClicked() {

        String path = Environment.getExternalStorageDirectory() + "/Images/" + post.getTitle() + ".jpg";

        post.setLocalPath(path);
        try {
            postDao.createOrUpdate(post);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(post.getImageUrl());
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
                | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Downloading image")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir("/Images", post.getTitle() + ".jpg");

        mgr.enqueue(request);
    }
}
