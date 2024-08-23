package org.icgeb.irsvpred_2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullScreenImageActivityAlgo5b extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_algo5b);



        PhotoView photoView = (PhotoView) findViewById(R.id.fullScreenImageViewAlgo5b);

        Intent callingActivityIntent6 = getIntent();
        if(callingActivityIntent6 != null) {
            Uri imageUri = callingActivityIntent6.getData();
            if(imageUri != null && photoView !=null) {
                Glide.with(this)
                        .load(imageUri)
                        .into(photoView);
            }
        }




    }

}




