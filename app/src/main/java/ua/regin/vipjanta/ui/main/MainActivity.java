package ua.regin.vipjanta.ui.main;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import ua.regin.vipjanta.R;
import ua.regin.vipjanta.ui.BaseActivity;
import ua.regin.vipjanta.ui.downloads.DownloadsActivity_;
import ua.regin.vipjanta.ui.picture.PictureListFragment_;
import ua.regin.vipjanta.ui.search.SearchActivity_;

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
            switchFragment(PictureListFragment_.builder().slug("Vip").title(getResources().getString(R.string.app_name)).withLogo(true).build());
        }
    }

    @AfterViews
    protected void afterViews() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            uncheckAllMenus();
            menuItem.setChecked(true);
            Resources resources = getResources();
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.drawer_vip:
                    fragment = PictureListFragment_.builder().slug("Vip").title(getResources().getString(R.string.app_name)).withLogo(true).build();
                    break;
                case R.id.drawer_amazing_graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_amazing_graphics)).build();
                    break;
                case R.id.drawer_Birthday_Graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Birthday_Graphics)).build();
                    break;
                case R.id.drawer_Desi_graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Desi_pictures)).build();
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
                case R.id.drawer_Punjabi_couples:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Punjabi_couples)).build();
                    break;
                case R.id.drawer_Sad_Graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Sad_Graphics)).build();
                    break;
                case R.id.drawer_Singh_soorme:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Singh_soorme)).build();
                    break;
                case R.id.drawer_Sat_sri_akal:
                    fragment = PictureListFragment_.builder().title("Sat Shri Akal").slug("Sat sri akal").build();
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
                case R.id.drawer_Whatsapp_special:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Whatsapp_special)).build();
                    break;
                case R.id.drawer_Diwali_Scraps:
                    fragment = PictureListFragment_.builder().title("Diwali Graphics").slug(resources.getString(R.string.drawer_Diwali_Scraps)).build();
                    break;
                case R.id.drawer_Get_Well_Soon:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Get_Well_Soon)).build();
                    break;
                case R.id.drawer_Gurudwara_Sahib:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Gurudwara_Sahib)).build();
                    break;
                case R.id.drawer_Happy_New_Year:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Happy_New_Year)).build();
                    break;
                case R.id.drawer_Ik_Onkar:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Ik_Onkar)).build();
                    break;
                case R.id.drawer_Hindi_Graphics:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Hindi_Graphics)).build();
                    break;
                case R.id.drawer_Kabaddi_Pictures:
                    fragment = PictureListFragment_.builder().title("Kabaddi").slug(resources.getString(R.string.drawer_Kabaddi_Pictures)).build();
                    break;
                case R.id.drawer_Khanda_Sahib:
                    fragment = PictureListFragment_.builder().title("Khanda").slug(resources.getString(R.string.drawer_Khanda_Sahib)).build();
                    break;
                case R.id.drawer_Paintings:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Paintings)).build();
                    break;
                case R.id.drawer_Punjabi_Celebrities:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Punjabi_Celebrities)).build();
                    break;
                case R.id.drawer_Satnam_Waheguru:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Satnam_Waheguru)).build();
                    break;
                case R.id.drawer_Sachian_Gallan:
                    fragment = PictureListFragment_.builder().slug(resources.getString(R.string.drawer_Sachian_Gallan)).build();
                    break;
                default:
                    throw new RuntimeException("Unknown fragment type");
            }

            switchFragment(fragment);
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void uncheckAllMenus() {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            menuItem.setChecked(false);
        }
    }

    @Click(R.id.home_button)
    protected void homeClicked() {
        switchFragment(PictureListFragment_.builder().slug(getResources().getString(R.string.drawer_vip)).withLogo(true).build());
    }

    @Click(R.id.contact_button)
    protected void contactClicked() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"vipjanta@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT, "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.downloads_button)
    protected void downloadsClicked() {
        DownloadsActivity_.intent(getContext()).start();
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
