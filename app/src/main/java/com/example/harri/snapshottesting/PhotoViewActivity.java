package com.example.harri.snapshottesting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoViewActivity extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        int width, height;

        this.imageView = (ImageView)findViewById(R.id.imageView);
        int position = getIntent().getIntExtra("position", -1);
        if (position != -1) {
            Picasso.with(PhotoViewActivity.this)
                    .load(LayoutActivity.urlArray[position])
                    .placeholder(R.drawable.logo)
                    .noFade()
                    .fit().centerCrop()
                    .error(R.drawable.obama)
                    .into(imageView);

        } else {
            Picasso.with(PhotoViewActivity.this)
                    .load(R.drawable.obama)
                    .noFade()
                    .resize(800, 800)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
