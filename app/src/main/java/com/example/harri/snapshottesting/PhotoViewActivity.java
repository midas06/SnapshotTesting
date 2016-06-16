package com.example.harri.snapshottesting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoViewActivity extends Activity {

    ImageView imageView;
    TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        this.headerText = (TextView)findViewById(R.id.tview_Header);

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
            this.setHeader(position);

        } else {
            Picasso.with(PhotoViewActivity.this)
                    .load(R.drawable.obama)
                    .noFade()
                    .resize(800, 800)
                    .centerCrop()
                    .into(imageView);
        }
    }

    private void setHeader(int position) {
        this.headerText.setText(LayoutActivity.descriptionArray[position]);
    }
}
