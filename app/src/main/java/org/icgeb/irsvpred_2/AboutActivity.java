package org.icgeb.irsvpred_2;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    ViewPager viewPager;
    AdapterAbout adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        bottomNav.setSelectedItemId(R.id.nav_about);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        models = new ArrayList<>();
        models.add(new Model(R.drawable.rvs_1,"Basmati: a brief introduction", "“Basmati” derives its roots in two Sanskrit words “Vas”(vasay) meaning “aroma” and “Mati” (mayup) meaning ingrained from the origin, common usage could have resulted in change of vas to bas " +
                "resulting in Basmati. Basmati is grown in the specific geographical area, at the Himalayan foot hills of Indian sub-continent, blessed with characteristics extra- long slender grains that elongate at least twice of their original size with a characteristics soft and " +
                "fluffy texture upon cooking, delicious taste, superior aroma and distinct flavor. Aroma of this scented pearl, is due to significant(0.09 parts per million) presence of 2-acetyl-1-pyrroline which gives Basmati its distinctive spicy fragrance. India accounts for over " +
                "70% of the world's basmati rice production. Basmati rice constitutes a small portion of the total rice produced in India. By volume, the share of basmati rice is around 6% in 2014-15, even as by value, basmati rice exports account for 57% in 2014-15, of India's total " +
                "rice exports. Basmati rice is now a registered GI product in India, with effect from February 5, 2016.", "", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_2, "Variety-Checker", "According to a survey conducted by MoEF&CC, published in report ”Report on Identity Preservation of Basmati Rice at Various Stages in the Rice Supply Chain”, in Haryana, 80-85% farmers were able to identify the" +
                " seed variety whereas, a large number of farmers (almost 20%) were still depending on the tag on seed bag they purchased whereas in Aligarh market area of Uttar Pradesh 100% farmers were depending on the tag on seed bag.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_3, "Primary Basmati Quality Characteristics", "As per the provisions of the Seed Act, 1966 and the recommendations of the Central Sub Committee on Crops, Standards, Notification and Release of Varieties for Agricultural Crops constituted" +
                " by the Central Seed Committee established under Section 3 of the Seeds Act, 1966, following standards have been laid down to determine and delineate the eligibility of a rice variety as Basmati :", "SLIDE RIGHT", ""));

        adapter = new AdapterAbout(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(0,0,0,0);



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_rice_variety:
                startActivity(new Intent(getApplicationContext(),RiceVarietyActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_stat:
                startActivity(new Intent(getApplicationContext(),StatisticsActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_developers:
                startActivity(new Intent(getApplicationContext(),DevelopersActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_contact_us:
                startActivity(new Intent(getApplicationContext(),ContactUsActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_help:
                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Happy to Share our recently developed android application, which will assist you in identification of Basmati Seeds" +
                        ". Simply on a click, please download and explore the application at " +
                        "https://play.google.com/store/apps/details?id=org.icgeb.irsvpred_2 Cheers!!!";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "iRSVPred 2.0");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {

                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.nav_about:
                    return true;

                case R.id.nav_algo:
                    startActivity(new Intent(getApplicationContext(),AlgoActivity.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;


        }
    };

    @Override
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        overridePendingTransition(0,0);

    }



}




