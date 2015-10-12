package com.example.company.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Выводит картинку в черно-белых цветах
 */
public class AfterImageFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content_fragment, viewGroup, false);
        App app = (App) getActivity().getApplication();
        if (app.getSelectedImgUri() != null){
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(app.getSelectedImgUri()), null, options);
                int width = options.outWidth;
                int height = options.outHeight;
                Bitmap grayBitmap = MainActivity.grayBitmap(bitmap, width, height);
                imageView.setImageBitmap(grayBitmap);
            }catch (Exception e){
                Log.d("LogTag", e.toString());
            }
        }
        Button button = (Button) view.findViewById(R.id.bt_open_image);
        button.setVisibility(View.GONE);
        return view;
    }
}
