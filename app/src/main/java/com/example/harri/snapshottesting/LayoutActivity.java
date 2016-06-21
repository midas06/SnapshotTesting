package com.example.harri.snapshottesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class LayoutActivity extends Activity {

    GridView gridView;
    PhotoAdapter photoAdapter;
    ImageView iview_Logo;
    ImageView btnHome, btnTimelapse, btnBack;

    public static Integer[] photoArray = {R.drawable.frame001, R.drawable.frame002, R.drawable.frame003,
            R.drawable.frame004, R.drawable.frame005, R.drawable.frame006,
            R.drawable.frame007, R.drawable.frame008, R.drawable.frame009};
    public static String[] urlArray = {
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5756b78088eb5145d114ebe3/1465300884264",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754f4095f21e023bae4d0d5/1465185336373",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754ee8a5f21e023bae4c374/1465183889792",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754ee0f5f21e023bae4c29e/1465183767410",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/575296c774e8d647193dc292/1465030359862",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5752968974e8d647193dc1fa/1465030296589",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754f4095f21e023bae4d0d5/1465185336373",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754ee8a5f21e023bae4c374/1465183889792",
            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754ee0f5f21e023bae4c29e/1465183767410"
    };
    public static String[] descriptionArray = {
            "Hooker Valley, 12/06/2016",
            "Tasman Glacier, 11/06/2016",
            "Tasman Glacier, 22/05/2016",
            "Mt Cook, 16/05/2016",
            "Mueller Glacier, 06/05/2016",
            "Hooker Valley, 05/06/2016",
            "Hooker Valley, 05/06/2016",
            "Mueller Glacier, 05/06/2016",
            "Banks Peninsula, 05/06/2016"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        gridView = (GridView) findViewById(R.id.photoGridview);
        iview_Logo = (ImageView)findViewById(R.id.iview_Logo);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnHome = (ImageView) findViewById(R.id.btnHome);
        btnTimelapse = (ImageView) findViewById(R.id.btnTimelapse);
        

        photoAdapter = new PhotoAdapter(this);
        photoAdapter.setUrlArray(this.urlArray);

        gridView.setAdapter(photoAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LayoutActivity.this, PhotoViewActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
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

    public void setAdapter(String[] newPhotos) {
        this.urlArray = newPhotos;
    }

    public PhotoAdapter getAdapter() {
        return this.photoAdapter;
    }


    public void onClick_Home() {
        Intent intent = new Intent(LayoutActivity.this, LayoutActivity.class);
        startActivity(intent);
    }

    public void onClick_Timelapse() {
        Intent intent = new Intent(LayoutActivity.this, VideoViewActivity.class);
        startActivity(intent);
    }

    public void onClick_Back() {
        finish();
    }

}