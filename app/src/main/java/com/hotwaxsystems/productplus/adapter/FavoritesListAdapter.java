package com.hotwaxsystems.productplus.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotwaxsystems.productplus.R;
import com.hotwaxsystems.productplus.pojo.customerDetails.FavoriteProduct;

import java.util.ArrayList;

/**
 * Created by ps11 on 19/01/17.
 */
public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.TextViews> {

    Context context;
    ArrayList<FavoriteProduct> favoriteProductArrayList = new ArrayList<>();
    ArrayList<String> favCategoryArrayList = new ArrayList<>();

    public FavoritesListAdapter(Context context, ArrayList<FavoriteProduct> favoriteProductArrayList, ArrayList<String> favCategoryArrayList) {
        this.context = context;
        this.favCategoryArrayList = favCategoryArrayList;
        this.favoriteProductArrayList = favoriteProductArrayList;
    }

    @Override
    public FavoritesListAdapter.TextViews onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_list, parent, false);
        FavoritesListAdapter.TextViews vh = new FavoritesListAdapter.TextViews(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FavoritesListAdapter.TextViews holder, int position) {
        if (position == 0) {
            holder.getProductNameView().setText("Products");
            RecyclerView favProductRecyclerView = holder.getItmList();
            LinearLayoutManager favProductLayoutManager = new LinearLayoutManager(context);
            favProductRecyclerView.setLayoutManager(favProductLayoutManager);
            FavProductRecyclerAdapter favCategoryAdapter = new FavProductRecyclerAdapter(favoriteProductArrayList);
            favProductRecyclerView.setAdapter(favCategoryAdapter);
        } else if (position == 1) {
            holder.getProductNameView().setText("Categories");
            RecyclerView favCategoryRecyclerView = holder.getItmList();
            LinearLayoutManager favCategoryLayoutManager = new LinearLayoutManager(context);
            favCategoryRecyclerView.setLayoutManager(favCategoryLayoutManager);
            FavCategoryRecyclerAdapter favCategoryAdapter = new FavCategoryRecyclerAdapter(favCategoryArrayList);
            favCategoryRecyclerView.setAdapter(favCategoryAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class TextViews extends RecyclerView.ViewHolder {
        TextView listName;
        RecyclerView itmList;

        public TextViews (View itemView) {
            super(itemView);
            listName = (TextView) itemView.findViewById(R.id.list_name);
            itmList = (RecyclerView) itemView.findViewById(R.id.itm_list);
        }

        public TextView getProductNameView (){
            return listName;
        }

        public RecyclerView getItmList () {return itmList;}
    }
}
