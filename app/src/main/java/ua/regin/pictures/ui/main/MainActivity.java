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
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import ua.regin.pictures.R;
import ua.regin.pictures.ui.BaseActivity;
import ua.regin.pictures.ui.picture.PictureListFragment_;
import ua.regin.pictures.ui.search.SearchActivity_;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends BaseActivity {

    @ViewById
    protected NavigationView navigationView;

    @ViewById
    protected DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            switchFragment(PictureListFragment_.builder().slug(getResources().getString(R.string.drawer_vip)).withLogo(true).build());
        }
    }

    @AfterViews
    protected void afterViews() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            Resources resources = getResources();
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.drawer_vip:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_vip)).withLogo(true).build();
                    break;
                case R.id.drawer_amazing:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_amazing)).build();
                    break;
                case R.id.drawer_Birthday_Graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Birthday_Graphics)).build();
                    break;
                case R.id.drawer_Best_of_Luck:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Best_of_Luck)).build();
                    break;
                case R.id.drawer_Call_Me:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Call_Me)).build();
                    break;
                case R.id.drawer_Facebook_graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Facebook_graphics)).build();
                    break;
                case R.id.drawer_Flowers:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Flowers)).build();
                    break;
                case R.id.drawer_Funny_graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Funny_graphics)).build();
                    break;
                case R.id.drawer_Friendship_Day:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Friendship_Day)).build();
                    break;
                case R.id.drawer_Golden_Temple:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Golden_Temple)).build();
                    break;
                case R.id.drawer_Good_morning:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Good_morning)).build();
                    break;
                case R.id.drawer_Good_night:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Good_night)).build();
                    break;
                case R.id.drawer_Hello:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Hello)).build();
                    break;
                case R.id.drawer_Heart_Graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Heart_Graphics)).build();
                    break;
                case R.id.drawer_Hinduism:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Hinduism)).build();
                    break;
                case R.id.drawer_Love_you:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Love_you)).build();
                    break;
                case R.id.drawer_Love_graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Love_graphics)).build();
                    break;
                case R.id.drawer_Miss_you:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Miss_you)).build();
                    break;
                case R.id.drawer_Old_Punjab:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Old_Punjab)).build();
                    break;
                case R.id.drawer_Punjabi_Culture:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Punjabi_Culture)).build();
                    break;
                case R.id.drawer_Punjabi_graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Punjabi_graphics)).build();
                    break;
                case R.id.drawer_Punjabi_couple:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Punjabi_couple)).build();
                    break;
                case R.id.drawer_Sad_Graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Sad_Graphics)).build();
                    break;
                case R.id.drawer_Singh_soorme:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Singh_soorme)).build();
                    break;
                case R.id.drawer_Sat_sri_akal:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Sat_sri_akal)).build();
                    break;
                case R.id.drawer_Sikh_Guru:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Sikh_Guru)).build();
                    break;
                case R.id.drawer_Sikhism:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Sikhism)).build();
                    break;
                case R.id.drawer_Sorry:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Sorry)).build();
                    break;
                case R.id.drawer_Turban_special:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Turban_special)).build();
                    break;
                case R.id.drawer_Thank_You:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Thank_You)).build();
                    break;
                case R.id.drawer_Valentines_Day:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Valentines_Day)).build();
                    break;
                case R.id.drawer_Whatsapp_sepecial:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Whatsapp_sepecial)).build();
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

    @OptionsItem(R.id.action_search)
    protected void searchClicked() {
        SearchActivity_.intent(this).start();
    }

    public void setToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(ignored -> drawerLayout.openDrawer(GravityCompat.START));

        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            super.onBackPressed();
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
