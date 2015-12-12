package ua.regin.pictures.ui.downloads;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.sql.SQLException;

import ua.regin.pictures.R;
import ua.regin.pictures.api.entity.Post;
import ua.regin.pictures.db.DatabaseHelper;
import ua.regin.pictures.ui.BaseActivity;
import ua.regin.pictures.ui.picture.adapter.PictureAdapter;

@EActivity(R.layout.activity_downloads)
public class DownloadsActivity extends BaseActivity implements PictureAdapter.OnItemClickListener {

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected RecyclerView recyclerView;

    @OrmLiteDao(helper = DatabaseHelper.class)
    protected Dao<Post, Integer> postDao;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(ignored -> onBackPressed());
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle("Downloads");
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        PictureAdapter adapter = new PictureAdapter(getContext(), false, this);
        recyclerView.setAdapter(adapter);

        try {
            adapter.setPostList(postDao.queryForAll());
        } catch (SQLException e) {
            //ignored
        }
    }

    @Override
    public void onItemClick(View view, Post post) {
        File file = new File(post.getLocalPath());
        Intent intent = new Intent();
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        intent.setAction(Intent.ACTION_VIEW);

        startActivity(intent);
    }
}
