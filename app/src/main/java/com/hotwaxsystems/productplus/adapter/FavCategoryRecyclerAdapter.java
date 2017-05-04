package com.hotwaxsystems.productplus.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotwaxsystems.productplus.R;

import java.util.ArrayList;

/**
 * Created by ps11 on 19/01/17.
 */
class FavCategoryRecyclerAdapter extends RecyclerView.Adapter <FavCategoryRecyclerAdapter.TextViews> {
    ArrayList<String> dataSet = new ArrayList<>();

    public FavCategoryRecyclerAdapter (ArrayList<String> dataSet) {
        this.dataSet=dataSet;
    }

    @Override
    public FavCategoryRecyclerAdapter.TextViews onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_list_item, parent, false);
        FavCategoryRecyclerAdapter.TextViews vh = new FavCategoryRecyclerAdapter.TextViews(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(FavCategoryRecyclerAdapter.TextViews holder, int position) {
        holder.getProductNameView().setText(dataSet.get(position));
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
