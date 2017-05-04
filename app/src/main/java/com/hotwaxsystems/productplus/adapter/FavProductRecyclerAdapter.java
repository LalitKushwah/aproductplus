package com.hotwaxsystems.productplus.adapter;

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
class FavProductRecyclerAdapter extends RecyclerView.Adapter <FavProductRecyclerAdapter.TextViews> {
    ArrayList<FavoriteProduct> dataSet = new ArrayList<>();

    public FavProductRecyclerAdapter (ArrayList<FavoriteProduct> dataSet) {
        this.dataSet=dataSet;
    }

    @Override
    public FavProductRecyclerAdapter.TextViews onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_list_item, parent, false);
        FavProductRecyclerAdapter.TextViews vh = new FavProductRecyclerAdapter.TextViews(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(FavProductRecyclerAdapter.TextViews holder, int position) {
        holder.getProductNameView().setText(dataSet.get(position).getProductName().toString());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class TextViews extends RecyclerView.ViewHolder {
        TextView productName;

        public TextViews (View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.item_name);
        }

        public TextView getProductNameView (){
            return productName;
        }
    }
}
