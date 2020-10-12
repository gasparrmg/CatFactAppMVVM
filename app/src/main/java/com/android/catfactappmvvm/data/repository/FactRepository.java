package com.android.catfactappmvvm.data.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.catfactappmvvm.data.remote.api.CatFactApi;
import com.android.catfactappmvvm.data.local.dao.PersonalFactDao;
import com.android.catfactappmvvm.data.remote.models.Fact;
import com.android.catfactappmvvm.data.local.models.PersonalFact;
import com.android.catfactappmvvm.data.local.database.PersonalFactDatabase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class FactRepository {
    private CatFactApi catFactApi;
    private PersonalFactDatabase personalFactDatabase;
    private PersonalFactDao personalFactDao;

    private MutableLiveData<List<Fact>> facts = new MutableLiveData<>();
    private LiveData<List<PersonalFact>> personalFacts;

    @Inject
    public FactRepository(
            CatFactApi catFactApi,
            PersonalFactDatabase personalFactDatabase,
            PersonalFactDao personalFactDao
    ) {
        this.catFactApi = catFactApi;
        this.personalFactDatabase = personalFactDatabase;
        this.personalFactDao = personalFactDao;

        personalFacts = personalFactDao.getAll();
    }

    //--------------- API OPERATIONS ---------------

    public MutableLiveData<List<Fact>> getMutableLiveData(String animalType, int amount) {
        Call<List<Fact>> call = catFactApi.getFacts(animalType, amount);

        call.enqueue(new Callback<List<Fact>>() {
            @Override
            public void onResponse(Call<List<Fact>> call, Response<List<Fact>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                facts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {
                Log.e("FACT_APP: VIEWMODEL", "There was an error: " + t.getMessage());
            }
        });

        return facts;
    }

    //--------------- ROOM OPERATIONS ---------------

    public void insertPersonalFact(PersonalFact personalFact) {
        new InsertPersonalFactAsyncTask(personalFactDao).execute(personalFact);
    }

    public void updatePersonalFact(PersonalFact personalFact) {
        new UpdatePersonalFactAsyncTask(personalFactDao).execute(personalFact);
    }

    public void deletePersonalFact(PersonalFact personalFact) {
        new DeletePersonalFactAsyncTask(personalFactDao).execute(personalFact);
    }

    public void deleteAllPersonalFacts() {
        new DeleteAllPersonalFactsAsyncTask(personalFactDao).execute();
    }

    public LiveData<List<PersonalFact>> getAllPersonalFacts() {
        return personalFacts;
    }

    private static class InsertPersonalFactAsyncTask extends AsyncTask<PersonalFact, Void, Void> {
        private PersonalFactDao personalFactDao;

        private InsertPersonalFactAsyncTask (PersonalFactDao personalFactDao) {
            this.personalFactDao = personalFactDao;
        }

        @Override
        protected Void doInBackground(PersonalFact... facts) {
            personalFactDao.insert(facts[0]);
            return null;
        }
    }

    private static class UpdatePersonalFactAsyncTask extends AsyncTask<PersonalFact, Void, Void> {
        private PersonalFactDao personalFactDao;

        private UpdatePersonalFactAsyncTask (PersonalFactDao personalFactDao) {
            this.personalFactDao = personalFactDao;
        }

        @Override
        protected Void doInBackground(PersonalFact... facts) {
            personalFactDao.update(facts[0]);
            return null;
        }
    }

    private static class DeletePersonalFactAsyncTask extends AsyncTask<PersonalFact, Void, Void> {
        private PersonalFactDao personalFactDao;

        private DeletePersonalFactAsyncTask (PersonalFactDao personalFactDao) {
            this.personalFactDao = personalFactDao;
        }

        @Override
        protected Void doInBackground(PersonalFact... facts) {
            personalFactDao.delete(facts[0]);
            return null;
        }
    }

    private static class DeleteAllPersonalFactsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonalFactDao personalFactDao;

        private DeleteAllPersonalFactsAsyncTask (PersonalFactDao personalFactDao) {
            this.personalFactDao = personalFactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personalFactDao.deleteAll();
            return null;
        }
    }
}
