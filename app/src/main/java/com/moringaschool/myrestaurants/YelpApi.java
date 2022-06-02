package com.moringaschool.myrestaurants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {

    @GET("business/search")
    Call<YelpBusinessesSearchResponse> getRestaurants(
            @Query("location") String Location,
            @Query("term")String term
    );
}
