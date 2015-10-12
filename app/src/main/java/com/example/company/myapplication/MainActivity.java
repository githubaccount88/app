package com.example.company.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * Основное и единственное активити приложения
 */
public class MainActivity extends AppCompatActivity implements OriginImageFragment.IChangeUriListener{
    App app;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (App) getApplication();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.before));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.before_after));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.after));
        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab){

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }

        });
    }

    @Override
    public void onChangeUri(Uri uri){
        app.setSelectedImgUri(uri);
        viewPager.getAdapter().notifyDataSetChanged();
    }

    /**
     * Создает на основе origin bitmap с черно белыми цветами
     */
    public static Bitmap grayBitmap(Bitmap origin, int width, int height){
        Bitmap gray = Bitmap.createBitmap(width, height, origin.getConfig());
        Canvas canvas = new Canvas(gray);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(filter);
        canvas.drawBitmap(origin, 0, 0, paint);
        return gray;
    }

    /**
     * Объединяет 2 bitmap-а в 1, и на мести их соединения добавляет белую линию
     */
    public static Bitmap mergeBitmaps(Bitmap bitmap1, Bitmap bitmap2){
        Bitmap bitmap = Bitmap.createBitmap(bitmap1.getWidth() + bitmap2.getWidth() + 1, bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmap1, 0f, 0f, null);
        canvas.drawLine(bitmap1.getWidth() + 1, 0, bitmap1.getWidth() + 1, bitmap.getHeight(), paint);
        canvas.drawBitmap(bitmap2, bitmap1.getWidth() + 2, 0f, null);
        return bitmap;
    }

}
