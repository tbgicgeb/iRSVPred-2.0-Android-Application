package org.icgeb.irsvpred_2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullScreenImageActivityAlgo1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_algo1);

        PhotoView photoView = (PhotoView) findViewById(R.id.fullScreenImageViewAlgo1);

        Intent callingActivityIntent1 = getIntent();
        if(callingActivityIntent1 != null) {
            Uri imageUri = callingActivityIntent1.getData();
            if(imageUri != null && photoView !=null) {
                Glide.with(this)
                        .load(imageUri)
                        .into(photoView);
            }
        }



    }

}




