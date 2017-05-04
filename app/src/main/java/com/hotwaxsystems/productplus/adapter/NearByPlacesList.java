package com.hotwaxsystems.productplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hotwaxsystems.productplus.MainActivity;
import com.hotwaxsystems.productplus.NearByPlacesActivity;
import com.hotwaxsystems.productplus.R;
import com.hotwaxsystems.productplus.pojo.restaurantResponse.NearbyRestaurant;
import com.hotwaxsystems.productplus.pojo.searchResponse.Restaurant_;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abc123 on 10/3/17.
 */

public class NearByPlacesList extends RecyclerView.Adapter<NearByPlacesList.TextViews> {

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> images=new ArrayList<>();
    ArrayList<String> distance=new ArrayList<>();
    ArrayList<String> destinationLatitude=new ArrayList<>();
    ArrayList<String> destinationLongitude=new ArrayList<>();
    Double latitude, longitude;
    int flag=0;
    public static ArrayList<NearbyRestaurant> dataset = new ArrayList<>();
    public static ArrayList<Restaurant_> dataset2 = new ArrayList<>();
    Context context;

    public NearByPlacesList(ArrayList<String> resultArrayList, ArrayList<String> images, ArrayList<String> distance, ArrayList<String> destinationLatitude, ArrayList<String> destinationLongitude, Context context) {
        this.name=resultArrayList;
        this.images=images;
        this.context=context;
        this.distance=distance;
        this.destinationLatitude=destinationLatitude;
        this.destinationLongitude=destinationLongitude;
        flag = 1;
    }


    public NearByPlacesList(ArrayList<NearbyRestaurant> dataset, Context context, Double latitude, Double longitude){

        this.dataset = dataset;
        this.context = context;
        this.latitude = latitude;
        this.longitude = longitude;
        flag = 2;

    }

    public NearByPlacesList(ArrayList<Restaurant_> dataset, Context context){
        this.dataset2 = dataset;
        this.context = context;
        flag = 3;
    }



    @Override
    public TextViews onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_by_places_list_item, parent, false);
        TextViews vh = new TextViews(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NearByPlacesList.TextViews holder, final int position) {

        if(flag == 2) {
            holder.getName().setText(dataset.get(position).getRestaurant().getName());
            holder.getDistance().setText(String.valueOf(NearByPlacesActivity.distFrom(latitude, longitude, Double.parseDouble(dataset.get(position).getRestaurant().getLocation().getLatitude()), Double.parseDouble(dataset.get(position).getRestaurant().getLocation().getLongitude())) + " K.M."));
            holder.getNavigate().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr="+ latitude+","+ longitude+"&daddr="+dataset.get(position).getRestaurant().getLocation().getLatitude()+","+dataset.get(position).getRestaurant().getLocation().getLongitude()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER );
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    context.startActivity(intent);
                }
            });
        }
        else if(flag == 3){
            holder.getName().setText(dataset2.get(position).getName());
//            holder.getDistance().setText(String.valueOf(NearByPlacesActivity.distFrom(latitude, longitude, Double.parseDouble(dataset2.get(position).getLocation().getLatitude()), Double.parseDouble(dataset2.get(position).getLocation().getLongitude())) + " K.M."));
//            holder.getNavigate().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("http://maps.google.com/maps?saddr="+ latitude+","+ longitude+"&daddr="+dataset2.get(position).getLocation().getLatitude()+","+dataset2.get(position).getLocation().getLongitude()));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addCategory(Intent.CATEGORY_LAUNCHER );
//                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//                    context.startActivity(intent);
//                }
//            });
        }



if(flag==2) {
    holder.getCardView().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, MainActivity.class);
            i.putExtra("position", position);
            i.putExtra("latitude", latitude);
            i.putExtra("longitude", longitude);
            i.putExtra("flag","2");
//                i.putExtra("name",name.get(position));
//                i.putExtra("image", images.get(position));
//                i.putExtra("distance", distance.get(position));
            context.startActivity(i);

        }
    });

}
        else if(flag==3){

    holder.getCardView().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, MainActivity.class);
            i.putExtra("position", position);
            i.putExtra("latitude", latitude);
            i.putExtra("longitude", longitude);
            i.putExtra("flag","3");

//                i.putExtra("name",name.get(position));
//                i.putExtra("image", images.get(position));
//                i.putExtra("distance", distance.get(position));
            context.startActivity(i);
        }
    });




}

        int r=R.mipmap.image_not_found_robo;

        if(flag==2) {
            Picasso.with(context)
                    .load(dataset.get(position).getRestaurant().getFeaturedImage())
                    //.placeholder(r)
                    .resize(200, 200)
                    .into(holder.getImageView());
        }
        else if(flag == 3 && dataset2.get(position).getThumb()!=""){
            Picasso.with(context)
                    .load(dataset2.get(position).getThumb())
                    //.placeholder(r)
                    .resize(200, 200)
                    .into(holder.getImageView());

        }
        else{
            Picasso.with(context)
                    .load(r)
                    //.placeholder(r)
                    .resize(200, 200)
                    .into(holder.getImageView());
        }

    }

    @Override
    public int getItemCount() {
        if(flag==2)return dataset.size();
        else if(flag==3) return dataset2.size();
        else  return 0;
    }

    public class TextViews extends RecyclerView.ViewHolder{
        TextView name;



        TextView distance;
        CircularImageView imageView;
        CardView cardView;
        Button navigate;

        public TextViews(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
            imageView=(CircularImageView)itemView.findViewById(R.id.imageView);
            distance=(TextView)itemView.findViewById(R.id.distance);
            navigate = (Button)itemView.findViewById(R.id.navigate);
        }
        public TextView getName(){return name;}
        public CardView getCardView() {
            return cardView;
        }
        public CircularImageView getImageView() {
            return imageView;
        }
        public Button getNavigate(){return  navigate;}
        public TextView getDistance() {
            return distance;
        }


    }
}
