package com.example.company.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Выводит изначальный вид изображения
 */
public class OriginImageFragment extends Fragment {

    /**
     * Через этот интерфейс сообщаем что выбрали новую картинку
     */
    interface IChangeUriListener{
        void onChangeUri(Uri uri);
    }

    static final int SELECT_PICTURE = 1;
    App app;
    ImageView ivImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        app = (App) getActivity().getApplication();
        View view = inflater.inflate(R.layout.content_fragment, viewGroup, false);
        ivImg = (ImageView) view.findViewById(R.id.iv_image);
        if (app.getSelectedImgUri() != null){
            ivImg.setImageURI(app.getSelectedImgUri());
        }
        Button btOpenImg = (Button) view.findViewById(R.id.bt_open_image);
        btOpenImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.select_pic)), SELECT_PICTURE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == SELECT_PICTURE){
                Uri selectedImgUri = data.getData();
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImgUri), null, options);
                    ivImg.setImageBitmap(bitmap);
                }catch (Exception e){
                    Log.d("LogTag", e.toString());
                }
                if (getActivity() instanceof IChangeUriListener){
                    ((IChangeUriListener)getActivity()).onChangeUri(selectedImgUri);
                }
            }
        }
    }

}
