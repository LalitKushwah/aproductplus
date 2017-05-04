package com.hotwaxsystems.productplus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

import com.hotwaxsystems.productplus.adapter.NearByPlacesList;


/**
 * Created by Tanmay on 29/12/16.
 */

public class ProductTab extends Fragment {
    WebView menu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View view = inflater.inflate(R.layout.product_tab, container, false);
        menu = (WebView)view.findViewById(R.id.menu);
        menu.getSettings().setJavaScriptEnabled(true);
        menu.loadUrl(NearByPlacesList.dataset.get(new MainActivity().pos).getRestaurant().getMenuUrl());
        return view;
    }
}