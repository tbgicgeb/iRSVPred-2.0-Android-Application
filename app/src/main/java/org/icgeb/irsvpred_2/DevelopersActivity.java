package org.icgeb.irsvpred_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class DevelopersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_developers);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dv);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Developers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
