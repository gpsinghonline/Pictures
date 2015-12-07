package ua.regin.pictures.ui.details;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import ua.regin.pictures.R;
import ua.regin.pictures.api.entity.Post;
import ua.regin.pictures.ui.BaseActivity;

@EActivity(R.layout.activity_details)
@OptionsMenu(R.menu.menu_details)
public class DetailsActivity extends BaseActivity {

    public static final String IMAGE_TRANSACTION_NAME = "image:transaction";

    @Extra
    protected Post post;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected ImageView imageView;

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
    }

    @OptionsItem(R.id.action_share)
    protected void shareClicked() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Amazing image: " + post.getUrl();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Amazing image");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @OptionsItem(R.id.action_download)
    protected void downloadClicked() {
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
