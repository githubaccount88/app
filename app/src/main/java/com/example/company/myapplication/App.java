package com.example.company.myapplication;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;

public class App extends Application {
    Uri selectedImgUri;

    Bitmap before;

    Bitmap beforeAfter;

    Bitmap after;

    public Uri getSelectedImgUri() {
        return selectedImgUri;
    }


    public void setSelectedImgUri(Uri selectedImgUri) {
        this.selectedImgUri = selectedImgUri;
    }

    public Bitmap getBefore() {
        return before;
    }

    public void setBefore(Bitmap before) {
        this.before = before;
    }

    public Bitmap getBeforeAfter() {
        return beforeAfter;
    }

    public void setBeforeAfter(Bitmap beforeAfter) {
        this.beforeAfter = beforeAfter;
    }

    public Bitmap getAfter() {
        return after;
    }

    public void setAfter(Bitmap after) {
        this.after = after;
    }
}
