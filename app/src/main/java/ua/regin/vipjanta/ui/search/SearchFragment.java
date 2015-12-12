package ua.regin.vipjanta.ui.search;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import rx.Subscription;
import ua.regin.vipjanta.R;
import ua.regin.vipjanta.api.entity.Post;
import ua.regin.vipjanta.manager.IPostManager;
import ua.regin.vipjanta.manager.impl.PostManager;
import ua.regin.vipjanta.ui.BaseFragment;
import ua.regin.vipjanta.ui.details.DetailsActivity;
import ua.regin.vipjanta.ui.details.DetailsActivity_;
import ua.regin.vipjanta.ui.picture.adapter.PictureAdapter;

@EFragment(R.layout.fragment_search)
public class SearchFragment extends BaseFragment implements PictureAdapter.OnItemClickListener {

    private PictureAdapter adapter;

    @Bean(PostManager.class)
    protected IPostManager postManager;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected RecyclerView recyclerView;

    @ViewById
    protected ProgressBar progressBar;

    @ViewById
    protected EditText searchView;

    @AfterViews
    protected void afterViews() {
        SearchActivity activity = (SearchActivity) getActivity();
        activity.setToolbar(toolbar, "Search");
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PictureAdapter(getContext(), false, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void handleError(Throwable e) {
        progressBar.setVisibility(View.GONE);
        super.handleError(e);
    }

    private void updateData(List<Post> postList) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setPostList(postList);
    }

    private Subscription subscription;

    @AfterTextChange(R.id.searchView)
    protected void afterSearchView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        String search = searchView.getText().toString();
        if (!search.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            subscription = postManager.search(searchView.getText().toString()).subscribe(this::updateData, this::handleError);
        }
    }


    @Override
    public void onItemClick(View view, Post post) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                view, DetailsActivity.IMAGE_TRANSACTION_NAME);

        DetailsActivity_.intent(getContext()).post(post).withOptions(options.toBundle()).start();
    }
}
