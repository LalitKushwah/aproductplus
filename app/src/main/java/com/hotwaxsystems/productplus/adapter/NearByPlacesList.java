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
import android.widget.ImageView;
import android.widget.TextView;

import com.hotwaxsystems.productplus.MainActivity;
import com.hotwaxsystems.productplus.NearByPlacesActivity;
import com.hotwaxsystems.productplus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abc123 on 26/3/17.
 */


/**
 * Created by abc123 on 10/3/17.
 */

public class NearByPlacesList extends RecyclerView.Adapter<NearByPlacesList.TextViews> {

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> images=new ArrayList<>();
    ArrayList<String> distance=new ArrayList<>();
    ArrayList<Double> destinationLatitude=new ArrayList<>();
    ArrayList<Double> destinationLongitude=new ArrayList<>();
    Context context;

    public NearByPlacesList(ArrayList<String> resultArrayList, ArrayList<String> images, ArrayList<String> distance, ArrayList<Double> destinationLatitude, ArrayList<Double> destinationLongitude, Context context) {
        this.name=resultArrayList;
        this.images=images;
        this.context=context;
        this.distance=distance;
        this.destinationLatitude=destinationLatitude;
        this.destinationLongitude=destinationLongitude;
    }
    @Override
    public TextViews onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_by_places_list_item, parent, false);
        TextViews vh = new TextViews(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NearByPlacesList.TextViews holder, final int position) {
        holder.getName().setText(name.get(position).toString());
        holder.getDistance().setText(distance.get(position));

        holder.getNavigate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+ NearByPlacesActivity.latitude+","+NearByPlacesActivity.longitude+"&daddr="+destinationLatitude.get(position)+","+destinationLongitude.get(position)));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER );
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                context.startActivity(intent);
                context.startActivity(intent);
            }
        });

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("name",name.get(position));
                i.putExtra("image", images.get(position));
                i.putExtra("distance", distance.get(position));
                context.startActivity(i);

            }
        });


        int r=R.mipmap.image_not_found_robo;

        Picasso.with(context)
                .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+images.get(position)+"&key=AIzaSyBMD05Jxc4JmbVNQSzIn9UC-6HcrbnO6F0")
                .placeholder(r)
                .resize(200,200)
                .into(holder.getImageView());

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class TextViews extends RecyclerView.ViewHolder{
        TextView name;



        TextView distance;
        ImageView imageView;
        CardView cardView;
        Button navigate;

        public TextViews(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            distance=(TextView)itemView.findViewById(R.id.distance);
            navigate = (Button)itemView.findViewById(R.id.navigate);
        }
        public TextView getName(){return name;}
        public CardView getCardView() {
            return cardView;
        }
        public ImageView getImageView() {
            return imageView;
        }
        public Button getNavigate(){return  navigate;}
        public TextView getDistance() {
            return distance;
        }


    }
}
