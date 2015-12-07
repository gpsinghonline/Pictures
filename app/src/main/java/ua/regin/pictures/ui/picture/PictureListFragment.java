package ua.regin.pictures.ui.picture;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import ua.regin.pictures.R;
import ua.regin.pictures.api.entity.PostInfo;
import ua.regin.pictures.manager.IPostManager;
import ua.regin.pictures.manager.impl.PostManager;
import ua.regin.pictures.ui.BaseFragment;
import ua.regin.pictures.ui.main.MainActivity;
import ua.regin.pictures.ui.picture.adapter.PictureAdapter;

@EFragment(R.layout.fragment_picture_list)
public class PictureListFragment extends BaseFragment {

    @FragmentArg
    protected String slug;

    @Bean(PostManager.class)
    protected IPostManager postManager;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected RecyclerView recyclerView;

    @ViewById
    protected ProgressBar progressBar;

    @AfterViews
    protected void afterViews() {
        MainActivity activity = (MainActivity) getActivity();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        String title;
        if (slug != null) {
            title = slug;
            postManager.loadPostsBySlug(slug).compose(bindToLifecycle()).subscribe(this::updateData);
        } else {
            title = getString(R.string.drawer_recent);
            postManager.loadRecentPosts().compose(bindToLifecycle()).subscribe(this::updateData);
        }
        activity.setToolbar(toolbar, title);
    }

    private void updateData(PostInfo postInfo) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        PictureAdapter adapter = new PictureAdapter(getContext(), postInfo.getPosts());
        recyclerView.setAdapter(adapter);
    }
}
