package com.hotwaxsystems.productplus;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hotwaxsystems.productplus.adapter.NearByPlacesList;
import com.hotwaxsystems.productplus.network.NearByLocation;
import com.hotwaxsystems.productplus.pojo.nearByLocation.NearByLocationResponse;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.DefaultProviderConfiguration;
import com.yayandroid.locationmanager.configuration.GooglePlayServicesConfiguration;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.configuration.PermissionConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProviderType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearByPlacesActivity extends LocationBaseActivity {
    String type="cafe   ";
    public static String defaultLogoFlag="cafe" ;
    RecyclerView recyclerView;
    LinearLayoutManager lm;
    public static Double latitude=22.725221,longitude=75.871339;
    NearByPlacesList adapter;
    ArrayList<String> images=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> distance=new ArrayList<>();
    ArrayList<Double> destinationLat=new ArrayList<>();
    ArrayList<Double> destinationLng=new ArrayList<>();
    Context context;
    TextView title;

    @Override
    public LocationConfiguration getLocationConfiguration() {

//        return new LocationConfiguration()
//                .keepTracking(true)
//                .askForGooglePlayServices(true)
//                .setMinAccuracy(200.0f)
//                .setWaitPeriod(ProviderType.GOOGLE_PLAY_SERVICES, 5 * 1000)
//                .setWaitPeriod(ProviderType.GPS, 10 * 1000)
//                .setWaitPeriod(ProviderType.NETWORK, 5 * 1000)
//                .setGPSMessage("Please enable GPS")
//                .setRationalMessage("Allow the app to use GPS");
//        return null;
      return  new LocationConfiguration.Builder()
                .keepTracking(false)
                .askForPermission(new PermissionConfiguration.Builder()
                        .requiredPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION })
                        .build())
                .useGooglePlayServices(new GooglePlayServicesConfiguration.Builder()
                        .askForGooglePlayServices(false)
                        .askForSettingsApi(true)
                        .failOnConnectionSuspended(true)
                        .failOnSettingsApiSuspended(false)
                        .ignoreLastKnowLocation(false)
                        .setWaitPeriod(20 * 1000)
                        .build())
                .useDefaultProviders(new DefaultProviderConfiguration.Builder()
                        .requiredTimeInterval(5 * 60 * 1000)
                        .requiredDistanceInterval(0)
                        .acceptableAccuracy(5.0f)
                        .acceptableTimePeriod(5 * 60 * 1000)
                        .gpsMessage("Turn on GPS?")
                        .setWaitPeriod(ProviderType.GPS, 20 * 1000)
                        .setWaitPeriod(ProviderType.NETWORK, 20 * 1000)
                        .build())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_places);

        context=NearByPlacesActivity.this;

        final Dialog dialog=new Dialog(NearByPlacesActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_dialog_item);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;


        dialog.show();

        Button applyFilter=(Button)dialog.findViewById(R.id.applyFilterBtn);
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView=(RecyclerView)findViewById(R.id.nearByPlacerecyclerView);
                lm=new LinearLayoutManager(NearByPlacesActivity.this);
                recyclerView.setLayoutManager(lm);
                title=(TextView)findViewById(R.id.title);
                adapter=new NearByPlacesList(name,images,distance,destinationLat,destinationLng,context);
                recyclerView.setAdapter(adapter);

                defaultLogoFlag=type;
                title.setText("Cafe");
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://maps.googleapis.com/")
                        .client(MainActivity.getUnsafeOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                NearByLocation service=retrofit.create(NearByLocation.class);
                Call<NearByLocationResponse> nearByLocationResponseCall=service.serachNearByLocation(NearByPlacesActivity.latitude+","+NearByPlacesActivity.longitude,500000,"cafe","AIzaSyBMD05Jxc4JmbVNQSzIn9UC-6HcrbnO6F0");
                nearByLocationResponseCall.enqueue(new Callback<NearByLocationResponse>() {
                    @Override
                    public void onResponse(Call<NearByLocationResponse> call, Response<NearByLocationResponse> response) {
                        name.clear();
                        images.clear();
                        destinationLat.clear();
                        destinationLng.clear();


                        Log.d("size",String.valueOf(response.body().getResults().size()));
                        for(int i=0;i<response.body().getResults().size()-1;i++) {
                            dialog.dismiss();
                          //  CustomerTab.customerCard.setVisibility(View.VISIBLE);
                            name.add(response.body().getResults().get(i).getName());
                            distance.add(String.valueOf(distFrom(NearByPlacesActivity.latitude,NearByPlacesActivity.longitude,response.body().getResults().get(i).getGeometry().getLocation().getLat(),response.body().getResults().get(i).getGeometry().getLocation().getLng())));
                            destinationLat.add(response.body().getResults().get(i).getGeometry().getLocation().getLat());
                            destinationLng.add(response.body().getResults().get(i).getGeometry().getLocation().getLng());
                            if(response.body().getResults().get(i).getPhotos()==null) {
                                images.add("CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU");
                            }
                            else {
                                images.add(response.body().getResults().get(i).getPhotos().get(0).getPhotoReference());
                            }
                        }
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<NearByLocationResponse> call, Throwable t) {
                        Log.d("failure",t.getMessage());
                    }
                });
            }
        });






    }

    public  Double distFrom(Double lat1, Double lng1, Double lat2, Double lng2) {
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
        latitude=location.getLatitude();
        longitude=location.getLongitude();
    }

    @Override
    public void onLocationFailed(int failType) {
        switch (failType) {
            case FailType.PERMISSION_DENIED: {
                //locationText.setText("Couldn't get location, because user didn't give permission!");
                break;
            }

            case FailType.NETWORK_NOT_AVAILABLE: {
                //locationText.setText("Couldn't get location, because network is not accessible!");
                break;
            }
            case FailType.TIMEOUT: {
                //locationText.setText("Couldn't get location, and timeout!");
                break;
            }

        }
    }
}
