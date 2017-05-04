package com.hotwaxsystems.productplus.network;

import com.hotwaxsystems.productplus.pojo.Recommendation.RecommendationResponse;
import com.hotwaxsystems.productplus.pojo.restaurantResponse.CuisineResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by abc123 on 13/4/17.
 */

public interface RecommendartionCalls {

    @GET("api/v2.1/search")
    Call<RecommendationResponse> recommendedRestaurants(@Header("user-key") String key, @Query("lat") Double lat, @Query("lon") Double lon, @Query("radius") int radius, @Query("cuisines") String cuisines, @Query("entity_id") String locationId);


    @GET("api/v2.1/cuisines")
    Call<CuisineResponse> getCuisines(@Header("user-key") String key, @Query("lat") Double lat, @Query("lon") Double lon, @Query("city_id") int cityId);
}
