package com.example.company.myapplication;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Выводит картинку у которой левая часть цветная, а правая черно белая
 */
public class BeforeAndAfterImgFragment extends Fragment {
    ImageView ivImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content_fragment, viewGroup, false);
        ivImg = (ImageView) view.findViewById(R.id.iv_image);
        App app = (App) getActivity().getApplication();
        if (app.getSelectedImgUri() != null) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(app.getSelectedImgUri()), null, options);
                int width = options.outWidth;
                int height = options.outHeight;
                Bitmap halfBefore = Bitmap.createBitmap(bitmap, 0, 0, width / 2, height);
                Bitmap halfAfter = Bitmap.createBitmap(bitmap, width / 2, 0, width / 2, height);
                halfAfter = MainActivity.grayBitmap(halfAfter, width / 2, height);
                Bitmap beforeAndAfter = MainActivity.mergeBitmaps(halfBefore, halfAfter);
                ivImg.setImageBitmap(beforeAndAfter);
            }catch (Exception e){
                Log.d("LogTag", e.toString());
            }
        }
        Button btOpenImg = (Button) view.findViewById(R.id.bt_open_image);
        btOpenImg.setVisibility(View.GONE);
        return view;
    }


}
