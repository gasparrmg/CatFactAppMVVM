package com.android.catfactappmvvm.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.catfactappmvvm.api.CatFactApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class FactRepository {
    private CatFactApi catFactApi;

    private MutableLiveData<List<Fact>> mutableLiveData = new MutableLiveData<>();

    @Inject
    public FactRepository(CatFactApi catFactApi) {
        this.catFactApi = catFactApi;
    }

    public MutableLiveData<List<Fact>> getMutableLiveData(String animalType, int amount) {
        Call<List<Fact>> call = catFactApi.getFacts(animalType, amount);

        call.enqueue(new Callback<List<Fact>>() {
            @Override
            public void onResponse(Call<List<Fact>> call, Response<List<Fact>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {
                Log.e("FACT_APP: VIEWMODEL", "There was an error: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
