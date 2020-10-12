package com.android.catfactappmvvm.di;

import android.content.Context;

import androidx.room.Room;

import com.android.catfactappmvvm.data.remote.api.CatFactApi;
import com.android.catfactappmvvm.data.local.dao.PersonalFactDao;
import com.android.catfactappmvvm.data.local.database.PersonalFactDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {
    private static String BASE_URL = "https://cat-fact.herokuapp.com/";

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
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

    @Provides
    @Singleton
    PersonalFactDatabase providePersonalFactDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                PersonalFactDatabase.class,
                "personal_fact_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    PersonalFactDao providePersonalFactDao(PersonalFactDatabase personalFactDatabase) {
        return personalFactDatabase.personalFactDao();
    }
}
