package ua.regin.pictures.ui.main;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ua.regin.pictures.R;
import ua.regin.pictures.ui.BaseActivity;
import ua.regin.pictures.ui.picture.PictureListFragment_;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById
    protected NavigationView navigationView;

    @ViewById
    protected DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            switchFragment(PictureListFragment_.builder().build());
        }
    }

    @AfterViews
    protected void afterViews() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            Resources resources = getResources();
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.drawer_recent:
                    fragment = PictureListFragment_.builder().build();
                    break;
                case R.id.drawer_alone:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_alone)).build();
                    break;
                case R.id.drawer_best_friends:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_best_friends)).build();
                    break;
                case R.id.drawer_boys_and_girls:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_boys_and_girls)).build();
                    break;
                case R.id.drawer_chocolate_day:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_chocolate_day)).build();
                    break;
                case R.id.drawer_hi:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_hi)).build();
                    break;
                default:
                    throw new RuntimeException("Unknown fragment type");
            }

            switchFragment(fragment);
            drawerLayout.closeDrawers();
            return true;
        });
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void setToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(ignored -> drawerLayout.openDrawer(GravityCompat.START));

        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
    }
}
