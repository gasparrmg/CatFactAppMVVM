package com.android.catfactappmvvm.di;

import android.app.Application;

import com.android.catfactappmvvm.api.CatFactApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {
    private static String BASE_URL = "https://cat-fact.herokuapp.com/";

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    CatFactApi provideCatFactApi(Retrofit retrofit) {
        return retrofit.create(CatFactApi.class);
    }
}
