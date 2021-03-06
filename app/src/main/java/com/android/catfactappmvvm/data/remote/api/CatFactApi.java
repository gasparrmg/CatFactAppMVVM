package com.android.catfactappmvvm.data.remote.api;

import com.android.catfactappmvvm.data.remote.models.Fact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatFactApi {
    @GET("facts/random")
    Call<List<Fact>> getFacts(
            @Query("animal_type") String animalType,
            @Query("amount") int amount
    );
}
