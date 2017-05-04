package com.hotwaxsystems.productplus.adapter;

/**
 * Created by Tanmay on 29/12/16.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hotwaxsystems.productplus.CustomerTab;
import com.hotwaxsystems.productplus.ProductTab;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CustomerTab tab1 = new CustomerTab();
                return tab1;
            case 1:
                ProductTab tab2 = new ProductTab();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
