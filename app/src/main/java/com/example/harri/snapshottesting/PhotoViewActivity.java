package com.example.harri.snapshottesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoViewActivity extends Activity {

    ImageView imageView;
    TextView headerText;
    ImageView btnHome, btnTimelapse, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        this.headerText = (TextView)findViewById(R.id.tview_Header);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnHome = (ImageView) findViewById(R.id.btnHome);
        btnTimelapse = (ImageView) findViewById(R.id.btnTimelapse);

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

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_Home();
            }
        });

        btnTimelapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_Timelapse();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setHeader(int position) {
        this.headerText.setText(LayoutActivity.descriptionArray[position]);
    }


    public void onClick_Home() {
        Intent intent = new Intent(PhotoViewActivity.this, LayoutActivity.class);
        startActivity(intent);
    }

    public void onClick_Timelapse() {
        Intent intent = new Intent(PhotoViewActivity.this, VideoViewActivity.class);
        startActivity(intent);
    }

    public void onClick_Back() {
        finish();
    }

}
