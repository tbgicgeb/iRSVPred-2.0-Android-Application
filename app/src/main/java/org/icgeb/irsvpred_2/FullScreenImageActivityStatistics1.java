package org.icgeb.irsvpred_2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullScreenImageActivityStatistics1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_stat_1);

        PhotoView photoView = (PhotoView) findViewById(R.id.stat_image_1);

        Intent callingActivityIntent10 = getIntent();
        if(callingActivityIntent10 != null) {
            Uri imageUri = callingActivityIntent10.getData();
            if(imageUri != null && photoView !=null) {
                Glide.with(this)
                        .load(imageUri)
                        .into(photoView);
            }
        }
    }

}




