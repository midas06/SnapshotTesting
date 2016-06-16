package com.example.harri.snapshottesting;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class PhotoAdapter extends BaseAdapter {

    public Integer[] photoArray = {R.drawable.frame001, R.drawable.frame002, R.drawable.frame003,
            R.drawable.frame004, R.drawable.frame005, R.drawable.frame006,
            R.drawable.frame007, R.drawable.frame008, R.drawable.frame009};
//    public String[] urlArray = {"http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5756b78088eb5145d114ebe3/1465300884264",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754f4095f21e023bae4d0d5/1465185336373",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754ee8a5f21e023bae4c374/1465183889792",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5754ee0f5f21e023bae4c29e/1465183767410",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/575296c774e8d647193dc292/1465030359862",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5752968974e8d647193dc1fa/1465030296589",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5752968974e8d647193dc1fa/1465030296589",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5752968974e8d647193dc1fa/1465030296589",
//            "http://static1.squarespace.com/static/56ff8d7ff8baf310d576388a/t/5752968974e8d647193dc1fa/1465030296589"
//    };
    public String[] urlArray = new String[9];
    private Context mContext;
    private int width, height, photoWidth;


    public PhotoAdapter(Context c) {
        this.mContext = c;
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        height = metrics.heightPixels;
        width = metrics.widthPixels;
        set();
    }

    protected void set() {
        photoWidth = width / 3;
        photoWidth = photoWidth - (photoWidth / 8);
    }

    public int getCount() {
        return this.urlArray.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext)
                //.load(photoArray[position])
                .load(urlArray[position])
                .placeholder(R.drawable.logo)
                .error(R.drawable.obama)
                .noFade().resize(photoWidth, photoWidth)
                .centerCrop()
                .into(imageView);

        imageView.setPadding(8, 0, 8, 0);
        return imageView;
    }

    public void setPhotoArray(Integer[] newArray) {
        this.photoArray = newArray;
    }

    public void setUrlArray(String[] newArray) {
        this.urlArray = newArray;
    }

}
