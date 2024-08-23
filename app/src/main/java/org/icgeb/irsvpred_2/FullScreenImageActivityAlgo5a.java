package org.icgeb.irsvpred_2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullScreenImageActivityAlgo5a extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_algo5a);



        PhotoView photoView = (PhotoView) findViewById(R.id.fullScreenImageViewAlgo5a);

        Intent callingActivityIntent5 = getIntent();
        if(callingActivityIntent5 != null) {
            Uri imageUri = callingActivityIntent5.getData();
            if(imageUri != null && photoView !=null) {
                Glide.with(this)
                        .load(imageUri)
                        .into(photoView);
            }
        }

    }

}




