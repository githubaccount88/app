package com.example.company.myapplication;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Контроллер для фрагментов т.е. создает и выдает нужный фрагмент для активной фкладки
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    final int numOfTabs = 3;

    public PagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0 : return new OriginImageFragment();
            case 1 : return new BeforeAndAfterImgFragment();
            case 2 : return new AfterImageFragment();
            default: return null;
        }
    }

    @Override
    public int getCount(){
        return numOfTabs;
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
