package com.hotwaxsystems.productplus.network;

import com.hotwaxsystems.productplus.pojo.restaurantResponse.RestaurantResponse;
import com.hotwaxsystems.productplus.pojo.searchResponse.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by abc123 on 24/3/17.
 */

public interface NearByLocation {

        @GET("api/v2.1/geocode")
        Call<RestaurantResponse> serachNearByLocation(@Header("user-key") String key, @Query("lat") Double lat, @Query("lon") Double lon);

        @GET("api/v2.1/search")
        Call<SearchResponse> serachRestaurant(@Header("user-key") String key, @Query("q") String query, @Query("entity_id") String eid, @Query("entity_type") String etype);

}
