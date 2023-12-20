package com.company.laeventa;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.company.laeventa.fragments.EditOverviewFragment;
import com.company.laeventa.fragments.EditPoliciesFragment;
import com.company.laeventa.fragments.EditVenueDetailsFragment;
import com.company.laeventa.fragments.WebPageFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.laeventa.fragments.AreasFragment;
import com.company.laeventa.fragments.StatisticsFragment;
import com.company.laeventa.fragments.VenueDetailFragment;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    protected Toolbar toolbar;
    protected FragmentManager fragmentManager;
    protected Menu menu;

    protected ImageView notificationIcon;

    Animation rotation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MyApplication.setIsAdmin(true);

        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setting fragment manager in global variable
        fragmentManager = getSupportFragmentManager();

        rotation = AnimationUtils.loadAnimation(getApplication(), R.anim.shaking);

        setDefaultPageStatistics(navigationView);


    }

    private void setDefaultPageStatistics(NavigationView navigationView) {
        navigationView.getMenu().getItem(0).setChecked(true);
        toolbar.setTitle("Profile Statistics");
        openStatisticsFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);

        menu.findItem(R.id.action_notifications).setActionView(R.layout.notification_layout);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notifications) {
            Toast.makeText(this, "Hello there!", Toast.LENGTH_SHORT).show();
           //  item.getActionView().setAnimation(rotation);
          //  item.setIcon(getResources().getDrawable(R.drawable.ic_notifications_active_test));
          //   item.getActionView().setAnimation(rotation);
          //  item.setActionView(R.layout.notification_layout);

            Log.i("testing", "id :   " + Integer.toString(R.id.action_notifications));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        fragmentManager.popBackStack();

        if (id == R.id.nav_statistics) {

            toolbar.setTitle("Profile Statistics");
            openStatisticsFragment();

        } else if (id == R.id.nav_preview) {

            toolbar.setTitle("Venue Preview");
            openVenuePreviewFragment();

        } else if (id == R.id.nav_venue) {

            toolbar.setTitle("Venue Details");
            openVenueDetailsFragment();

        } else if (id == R.id.nav_overview) {

            toolbar.setTitle("Overview Details");
            openEditOverFragment();


        } else if (id == R.id.nav_area) {

            toolbar.setTitle("Areas Details");
            openAreaFragment();


        } else if (id == R.id.nav_policies) {

            toolbar.setTitle("Venue Policies");
            openEditPoliciesFragment();


        } else if (id == R.id.nav_contact_us) {

            toolbar.setTitle("Contact Us");
            openContactUsFragment();


        } else if (id == R.id.nav_about_us) {

            toolbar.setTitle("About Us");
            openAboutUsFragment();

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void openVenuePreviewFragment() {
        VenueDetailFragment venueDetailFragment;
        venueDetailFragment = (VenueDetailFragment) fragmentManager.findFragmentByTag("venue_preview");
        if (venueDetailFragment == null) {
            venueDetailFragment = new VenueDetailFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, venueDetailFragment, "venue_preview").commit();
        } else {
            venueDetailFragment = new VenueDetailFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, venueDetailFragment, "venue_preview").commit();
        }
    }

    private void openEditOverFragment() {
        EditOverviewFragment editOverviewFragment;
        editOverviewFragment = (EditOverviewFragment) fragmentManager.findFragmentByTag("edit_overview");
        if (editOverviewFragment == null) {
            editOverviewFragment = new EditOverviewFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, editOverviewFragment, "edit_overview").commit();
        } else {
            editOverviewFragment = new EditOverviewFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, editOverviewFragment, "edit_overview").commit();
        }

    }

    private void openEditPoliciesFragment() {
        EditPoliciesFragment editPoliciesFragment;
        editPoliciesFragment = (EditPoliciesFragment) fragmentManager.findFragmentByTag("edit_policies");
        if (editPoliciesFragment == null) {
            editPoliciesFragment = new EditPoliciesFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, editPoliciesFragment, "edit_policies").commit();
        } else {
            editPoliciesFragment = new EditPoliciesFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, editPoliciesFragment, "edit_policies").commit();
        }
    }

    private void openStatisticsFragment() {
        StatisticsFragment statisticsFragment;
        statisticsFragment = (StatisticsFragment) fragmentManager.findFragmentByTag("statistics");
        if (statisticsFragment == null) {
            statisticsFragment = new StatisticsFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, statisticsFragment, "statistics").commit();
        } else {
            statisticsFragment = new StatisticsFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, statisticsFragment, "statistics").commit();
        }
    }

    private void openVenueDetailsFragment() {
        EditVenueDetailsFragment editVenueDetailsFragment;
        editVenueDetailsFragment = (EditVenueDetailsFragment) fragmentManager.findFragmentByTag("venue_details");
        if (editVenueDetailsFragment == null) {
            editVenueDetailsFragment = new EditVenueDetailsFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, editVenueDetailsFragment, "venue_details").commit();
        } else {
            editVenueDetailsFragment = new EditVenueDetailsFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, editVenueDetailsFragment, "venue_details").commit();
        }
    }

    private void openAreaFragment() {
        AreasFragment areasFragment;
        areasFragment = (AreasFragment) fragmentManager.findFragmentByTag("areas_details");
        if (areasFragment == null) {
            areasFragment = new AreasFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, areasFragment, "areas_details").commit();
        } else {
            areasFragment = new AreasFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, areasFragment, "areas_details").commit();
        }
    }


    private void openContactUsFragment() {
        WebPageFragment webPageFragment;

        webPageFragment = (WebPageFragment) fragmentManager.findFragmentByTag("web_page");
        if (webPageFragment == null) {
            webPageFragment = new WebPageFragment();
            webPageFragment.setUrl("http://www.primechaintech.com/contactus.php");
            fragmentManager.beginTransaction().replace(R.id.fragment_container, webPageFragment, "web_page").commit();
        } else {
            //  webPageFragment = new WebPageFragment();
            webPageFragment.setUrl("http://www.primechaintech.com/contactus.php");
            fragmentManager.beginTransaction().replace(R.id.fragment_container, webPageFragment, "web_page").commit();
        }


    }


    private void openAboutUsFragment() {
        WebPageFragment webPageFragment;

        webPageFragment = (WebPageFragment) fragmentManager.findFragmentByTag("web_page");
        if (webPageFragment == null) {
            webPageFragment = new WebPageFragment();
            webPageFragment.setUrl("http://www.primechaintech.com/about.php");
            fragmentManager.beginTransaction().replace(R.id.fragment_container, webPageFragment, "web_page").commit();
        } else {
            //  webPageFragment = new WebPageFragment();
            webPageFragment.setUrl("http://www.primechaintech.com/about.php");
            fragmentManager.beginTransaction().replace(R.id.fragment_container, webPageFragment, "web_page").commit();
        }


    }
}
