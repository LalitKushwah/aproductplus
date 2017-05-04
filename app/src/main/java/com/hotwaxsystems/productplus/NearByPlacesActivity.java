package com.hotwaxsystems.productplus;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hotwaxsystems.productplus.adapter.NearByPlacesList;
import com.hotwaxsystems.productplus.network.NearByLocation;
import com.hotwaxsystems.productplus.pojo.restaurantResponse.NearbyRestaurant;
import com.hotwaxsystems.productplus.pojo.restaurantResponse.RestaurantResponse;
import com.hotwaxsystems.productplus.pojo.searchResponse.Restaurant_;
import com.hotwaxsystems.productplus.pojo.searchResponse.SearchResponse;
import com.yayandroid.locationmanager.LocationManager;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.DefaultProviderConfiguration;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.configuration.PermissionConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.listener.LocationListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearByPlacesActivity extends LocationBaseActivity {

    RecyclerView recyclerView;
    LocationConfiguration locationConfig;
    LinearLayoutManager lm;
    ProgressDialog loading;
    public  Double latitude=22.725221,longitude=75.871339;
    NearByPlacesList adapter;
    EditText searchBar;
   public static HashMap<String, NearbyRestaurant> details = new HashMap<>();
    public static HashMap<String, Restaurant_> searchResults = new HashMap<>();
    Context context;
    public static Retrofit retrofit;
    TextView useremail;
    ListView drawer;

    @Override
    public LocationConfiguration getLocationConfiguration() {


      locationConfig =  new LocationConfiguration.Builder()
                .keepTracking(false)
                .askForPermission(new PermissionConfiguration.Builder()
                        .requiredPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION })
                        .build())
                .useDefaultProviders(new DefaultProviderConfiguration.Builder()
                        .requiredTimeInterval(5 * 60 * 1000)
                        .requiredDistanceInterval(0)
                        .acceptableAccuracy(5.0f)
                        .acceptableTimePeriod(5 * 60 * 1000)
                        .gpsMessage("Turn on GPS?")
                       // .setWaitPeriod(ProviderType.GPS, 10 * 1000)
                       // .setWaitPeriod(ProviderType.NETWORK, 20 * 1000)
                        .build())
                .build();

        return  locationConfig;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_near_by_places);
        loading = new ProgressDialog(this);
        loading.setMessage("Getting Location and Searching...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        context=NearByPlacesActivity.this;
                recyclerView=(RecyclerView)findViewById(R.id.nearByPlacerecyclerView);
                lm=new LinearLayoutManager(NearByPlacesActivity.this);
                recyclerView.setLayoutManager(lm);

        drawer = (ListView)findViewById(R.id.drawer_list);

       // listView = (ListView)findViewById(R.id.drawer_list);

        searchBar = (EditText)findViewById(R.id.searchBar);
        searchBar.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        useremail = (TextView)findViewById(R.id.useremail);
        String email = getIntent().getStringExtra("email");
        useremail.setText(email);

        drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch(position){

                   case 0 : {//Dismiss Drawer
                       Toast.makeText(context, "Swipe the drawer to left", Toast.LENGTH_SHORT).show();
                       break;
                        }
                   case 1 : { //About US
                            startActivity(new Intent(NearByPlacesActivity.this, AboutUsActivity.class));
                       break;
                   }
                   case 2 : { // Contact
                       startActivity(new Intent(NearByPlacesActivity.this, ContactUsActivity.class));
                       break;
                   }
                   case 3 : { // Signout
                       new SharedPrefUtil(NearByPlacesActivity.this).addString("username", "");
                       startActivity(new Intent(NearByPlacesActivity.this, LoginActivity.class));
                       break;
                   }



               }

            }
        });

       searchBar.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER)) {
                   loading.setMessage("Searching Restaurant...");
                   loading.setCanceledOnTouchOutside(false);
                   loading.show();
                    getSearchResults(searchBar.getText().toString());
                   return true;
               }


               return false;
           }
       });


        LocationManager awesomeLocationManager = new LocationManager.Builder(getApplicationContext())
                .activity(NearByPlacesActivity.this) // Only required to ask permission and/or GoogleApi - SettingsApi
             //   .fragment(fragmentInstance) // Only required to ask permission and/or GoogleApi - SettingsApi
                .configuration(locationConfig)
               // .locationProvider(new YourCustomLocationProvider())
                .notify(new LocationListener() {
                    @Override
                    public void onProcessTypeChanged(int processType) {

                    }

                    @Override
                    public void onLocationChanged(Location location) {

                    }

                    @Override
                    public void onLocationFailed(int type) {

                    }

                    @Override
                    public void onPermissionGranted(boolean alreadyHadPermission) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                })
                .build();

        getLocation();
        awesomeLocationManager.get();
         //       getRestaurants();

            }

    public  static Double distFrom(Double lat1, Double lng1, Double lat2, Double lng2) {
        double earthRadius = 6371; //km
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double dist = (Double) (earthRadius * c);
        Double truncatedDouble = BigDecimal.valueOf(dist)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        return truncatedDouble;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("Location", "onLocationChanged: Getting Locaiton...." );
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        getRestaurants(location);
    }

    @Override
    public void onLocationFailed(int failType) {
        switch (failType) {
            case FailType.PERMISSION_DENIED: {
                Toast.makeText(context, "Couldn't get location, because user didn't give permission!", Toast.LENGTH_SHORT).show();

                break;
            }

            case FailType.NETWORK_NOT_AVAILABLE: {
                Toast.makeText(context, "Couldn't get location, because network is not accessible", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.TIMEOUT: {
                Toast.makeText(context, "Couldn't get location, and timeout..Trying Again", Toast.LENGTH_SHORT).show();
                getLocation();
                break;
            }

        }
    }


    public void  getRestaurants(final Location location ){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://developers.zomato.com/")
                .client(MainActivity.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NearByLocation service=retrofit.create(NearByLocation.class);
        Call<RestaurantResponse> nearByLocationResponseCall=service.serachNearByLocation("c2f9d3dbde8018ffda6f41c0bcae76d0",location.getLatitude(),location.getLongitude());
        nearByLocationResponseCall.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                for(int i=0;i<response.body().getNearbyRestaurants().size();i++) {
                    details.put(String.valueOf(i), response.body().getNearbyRestaurants().get(i));
                    adapter = new NearByPlacesList(new ArrayList<NearbyRestaurant>(details.values()), NearByPlacesActivity.this, location.getLatitude(), location.getLongitude());
                    recyclerView.setAdapter(adapter);
                    loading.dismiss();
                }
            }
            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("failure",t.getMessage());
            }
        });
    }


    public void applyFilters(String filter) {
ArrayList<NearbyRestaurant> dataset =  new ArrayList<>(details.values());



        switch (filter) {

            case "Cost"  : {
                for(int i=0;i<dataset.size();i++){
                    for(int j=0;j<dataset.size();j++){

                        if(dataset.get(i).getRestaurant().getAverageCostForTwo()<dataset.get(j).getRestaurant().getAverageCostForTwo()){
                            //swap 'em
                            NearbyRestaurant temp = dataset.get(j);
                            dataset.set(j,dataset.get(i));
                            dataset.set(i,temp);
                        }
                    }
                }
                break;}

            case "Distance" : {
                break;}

            case "Ratings" : {
                for(int i=0;i<dataset.size();i++){
                    for(int j=0;j<dataset.size();j++){

                        if(Double.parseDouble(dataset.get(i).getRestaurant().getUserRating().getAggregateRating())  >  Double.parseDouble(dataset.get(j).getRestaurant().getUserRating().getAggregateRating())){
                            //swap 'em
                            NearbyRestaurant temp = dataset.get(j);
                            dataset.set(j,dataset.get(i));
                            dataset.set(i,temp);
                        }
                    }
                }
                break;}

        }
        adapter = new NearByPlacesList(dataset, NearByPlacesActivity.this, latitude, longitude);
        recyclerView.removeAllViews();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    public void filter(View view) {

        if(view.getId() == R.id.cost)
        applyFilters("Cost");
        if(view.getId() == R.id.ratings)
            applyFilters("Ratings");
    }


    public void getSearchResults(String query){

       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developers.zomato.com/")
                .client(MainActivity.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NearByLocation service=retrofit.create(NearByLocation.class);
        Call<SearchResponse> nearByLocationResponseCall=service.serachRestaurant("c2f9d3dbde8018ffda6f41c0bcae76d0",query,"14","city");
        nearByLocationResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
            loading.dismiss();
                int count = response.body().getResultsFound();

                for(int i=0;i<count;i++)
                {
                    searchResults.put(String.valueOf(i), response.body().getRestaurants().get(i).getRestaurant());

                }
                if(count>0) {

                    adapter = new NearByPlacesList(new ArrayList<Restaurant_>(searchResults.values()), NearByPlacesActivity.this);
                    recyclerView.removeAllViews();
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(context, "No Results Found....", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(context, "Restaurant Not Found...", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                Log.d("failure",t.getMessage());
            }
        });


    }


}
