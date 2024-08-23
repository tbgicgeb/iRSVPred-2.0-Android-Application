package org.icgeb.irsvpred_2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullScreenImageActivityAlgo3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_algo3);



        PhotoView photoView = (PhotoView) findViewById(R.id.fullScreenImageViewAlgo3);

        Intent callingActivityIntent3 = getIntent();
        if(callingActivityIntent3 != null) {
            Uri imageUri = callingActivityIntent3.getData();
            if(imageUri != null && photoView !=null) {
                Glide.with(this)
                        .load(imageUri)
                        .into(photoView);
            }
        }




    }

}




