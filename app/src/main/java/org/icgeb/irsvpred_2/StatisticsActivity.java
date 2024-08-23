package org.icgeb.irsvpred_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_statistics);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_st);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView img1 = (ImageView) findViewById(R.id.statimage);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, FullScreenImageActivityStatistics1.class);
                startActivity(intent);
            }
        });
    }
}
