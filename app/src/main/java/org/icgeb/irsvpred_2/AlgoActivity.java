package org.icgeb.irsvpred_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class AlgoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_algo);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        bottomNav.setSelectedItemId(R.id.nav_algo);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        ImageView img1 = (ImageView) findViewById(R.id.algo_fig1);
        img1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo1.class);
                startActivity(intent);
            }
        });

        ImageView img2 = (ImageView) findViewById(R.id.algo_fig2);
        img2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo2.class);
                startActivity(intent);
            }
        });


        ImageView img3 = (ImageView) findViewById(R.id.algo_fig3);
        img3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo3.class);
                startActivity(intent);
            }
        });



        ImageView img4 = (ImageView) findViewById(R.id.algo_fig4);
        img4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo4.class);
                startActivity(intent);
            }
        });


        ImageView img5a = (ImageView) findViewById(R.id.algo_fig5a);
        img5a.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo5a.class);
                startActivity(intent);
            }
        });



        ImageView img5b = (ImageView) findViewById(R.id.algo_fig5b);
        img5b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo5b.class);
                startActivity(intent);
            }
        });



        ImageView img5c = (ImageView) findViewById(R.id.algo_fig5c);
        img5c.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo5c.class);
                startActivity(intent);
            }
        });


        ImageView img6 = (ImageView) findViewById(R.id.algo_fig6);
        img6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo6.class);
                startActivity(intent);
            }
        });



//        ImageView img7 = (ImageView) findViewById(R.id.algo_fig7);
//        img7.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(AlgoActivity.this, FullScreenImageActivityAlgo7.class);
//                startActivity(intent);
//            }
//        });



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
                    startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.nav_algo:
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




