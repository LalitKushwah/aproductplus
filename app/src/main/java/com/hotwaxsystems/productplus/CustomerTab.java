package com.hotwaxsystems.productplus;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hotwaxsystems.productplus.adapter.NearByPlacesList;
import com.hotwaxsystems.productplus.network.RecommendartionCalls;
import com.hotwaxsystems.productplus.pojo.Recommendation.RecommendationResponse;
import com.hotwaxsystems.productplus.pojo.restaurantResponse.Cuisine;
import com.hotwaxsystems.productplus.pojo.restaurantResponse.CuisineResponse;
import com.hotwaxsystems.productplus.pojo.restaurantResponse.Restaurant;
import com.hotwaxsystems.productplus.pojo.searchResponse.Restaurant_;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerTab extends Fragment {


    static ArrayList<String> image = new ArrayList<>();
    TextView name, billingNumber,address,avgPrice,cuisinesDetails,ratingValue;
    CircularImageView restaurantImage;
    CarouselView carouselView;
    Restaurant details;
    Restaurant_ searchResponse;
    com.hotwaxsystems.productplus.pojo.Recommendation.Restaurant details2;
    public List<Cuisine> cuisineResponses;
  public ArrayList<com.hotwaxsystems.productplus.pojo.Recommendation.Restaurant> recommendedRestaurants= new ArrayList<>();




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_tab, container, false);
        carouselView = (CarouselView) view.findViewById(R.id.linear);
        carouselView.setViewListener(viewListener);

        getCuisines();
        address = (TextView) view.findViewById(R.id.address);
        avgPrice = (TextView) view.findViewById(R.id.avgPrice);
        cuisinesDetails = (TextView) view.findViewById(R.id.cuisinesDetails);
        billingNumber = (TextView) view.findViewById(R.id.home_contact);
        ratingValue = (TextView) view.findViewById(R.id.ratingValue);
        restaurantImage = (CircularImageView) view.findViewById(R.id.restaurantImage);
        name = (TextView) view.findViewById(R.id.customer_name);


        if(MainActivity.flag.equals("2")) {
            details = NearByPlacesList.dataset.get(new MainActivity().pos).getRestaurant();

            Picasso.with(getActivity())
                    .load(NearByPlacesList.dataset.get(new MainActivity().pos).getRestaurant().getFeaturedImage())
                    .resize(75, 75)
                    .placeholder(R.drawable.notfound)
                    .into(restaurantImage);


            name.setText(details.getName());
            billingNumber.setText("NA");
            address.setText(details.getLocation().getAddress());
            avgPrice.setText(String.valueOf("Average cost for two: " + details.getAverageCostForTwo()));
            cuisinesDetails.setText(details.getCuisines());
            ratingValue.setText(details.getUserRating().getAggregateRating());
        }
        else if(MainActivity.flag.equals("3")){

            searchResponse = NearByPlacesActivity.searchResults.get(String.valueOf(new MainActivity().pos));
            name.setText(searchResponse.getName());
            billingNumber.setText("NA");
            address.setText(searchResponse.getLocation().getAddress());
            avgPrice.setText(String.valueOf("Average cost for two: " + searchResponse.getAverageCostForTwo()));
            cuisinesDetails.setText(searchResponse.getCuisines());
            ratingValue.setText(searchResponse.getUserRating().getAggregateRating());


        }


        return view;
    }

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(final int position) {
            View customView = getLayoutInflater(null).inflate(R.layout.related_product_list_item, null);
            CircularImageView img = (CircularImageView) customView.findViewById(R.id.related_product_image);
            TextView recomProductName = (TextView) customView.findViewById(R.id.related_product_name);
            TextView recomProductPrice = (TextView) customView.findViewById(R.id.related_product_cost);
           if (recommendedRestaurants.get(position).getRestaurant().getFeaturedImage()=="") {
                img.setImageResource(R.drawable.notfound);
            }
            else {

               Log.d("imagePath",recommendedRestaurants.get(position).getRestaurant().getFeaturedImage());
                Picasso.with(getActivity())
                        .load(recommendedRestaurants.get(position).getRestaurant().getThumb())
                        .resize(75, 75)
                       // .placeholder(R.drawable.notfound)
                        .into(img);
            }
            recomProductName.setText(recommendedRestaurants.get(position).getRestaurant().getName());
            recomProductPrice.setText(recommendedRestaurants.get(position).getRestaurant().getLocation().getAddress());
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    //recommendedRestaurants.get(position)
                    details2 = recommendedRestaurants.get(position);
                    name.setText(details2.getRestaurant().getName());
                    billingNumber.setText("NA");
                    address.setText(details2.getRestaurant().getLocation().getAddress());
                    avgPrice.setText(String.valueOf("Average cost for two: "+details2.getRestaurant().getAverageCostForTwo()));
                    cuisinesDetails.setText(details2.getRestaurant().getCuisines());
                    ratingValue.setText(details2.getRestaurant().getUserRating().getAggregateRating());
                    Picasso.with(getActivity())
                            .load(details2.getRestaurant().getFeaturedImage())
                            .resize(75, 75)
                            .placeholder(R.drawable.notfound)
                            .into(restaurantImage);
                }
            });
            return customView;
        }
    };



    public void getCuisines() {
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developers.zomato.com/")
                .client(MainActivity.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecommendartionCalls service=retrofit.create(RecommendartionCalls.class);
        Call<CuisineResponse> cuisineResponseCall= service.getCuisines("c2f9d3dbde8018ffda6f41c0bcae76d0",new NearByPlacesActivity().latitude,new NearByPlacesActivity().longitude,14);
        cuisineResponseCall.enqueue(new Callback<CuisineResponse>() {
            @Override
            public void onResponse(Call<CuisineResponse> call, Response<CuisineResponse> response) {
                //if(response.isSuccessful()){
                    cuisineResponses = response.body().getCuisines();
                    getRecommendations();
               // }
            }

            @Override
            public void onFailure(Call<CuisineResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public String getCuisineId(String name){
        String id="55";
        String[] cuisines = name.split(",");
        if(cuisines.length>1) {
            for (int i = 0; i < cuisineResponses.size(); i++) {
                if (cuisines[1].trim().equals(cuisineResponses.get(i).getCuisine().getCuisineName())) {
                    return String.valueOf(cuisineResponses.get(i).getCuisine().getCuisineId());
                }
            }
        }
        else{

            for (int i = 0; i < cuisineResponses.size(); i++) {
                if (cuisines[0].equals(cuisineResponses.get(i).getCuisine().getCuisineName())) {
                    return String.valueOf(cuisineResponses.get(i).getCuisine().getCuisineId());
                }
            }
        }


       return id;
    }


    public void getRecommendations(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developers.zomato.com/")
                .client(MainActivity.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecommendartionCalls service= retrofit.create(RecommendartionCalls.class);
        Call<RecommendationResponse> recommendationResponseCall=service.recommendedRestaurants
                ("c2f9d3dbde8018ffda6f41c0bcae76d0",
                        new MainActivity().latitude,
                        new MainActivity().longitude,
                        1000,getCuisineId(cuisinesDetails.getText().toString())
                ,"14");

        recommendationResponseCall.enqueue(new Callback<RecommendationResponse>() {
            @Override
            public void onResponse(Call<RecommendationResponse> call, Response<RecommendationResponse> response) {
                if(response!=null) {
                    if(response.body().getRestaurants().size()>0) {
                        recommendedRestaurants.clear();
                        recommendedRestaurants.addAll(response.body().getRestaurants());
                        for(int i=0;i<response.body().getRestaurants().size()-1;i++) {
                            image.add(response.body().getRestaurants().get(i).getRestaurant().getFeaturedImage());
                        }
                        carouselView.setPageCount(response.body().getRestaurants().size()-1);
                    }
                }
            }
            @Override
            public void onFailure(Call<RecommendationResponse> call, Throwable t) {
            }
        });
    }
}





