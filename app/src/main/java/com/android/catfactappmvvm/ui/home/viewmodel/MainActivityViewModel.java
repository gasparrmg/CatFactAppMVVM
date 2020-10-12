package com.android.catfactappmvvm.ui.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.catfactappmvvm.data.remote.models.Fact;
import com.android.catfactappmvvm.data.repository.FactRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainActivityViewModel extends ViewModel {
    private FactRepository factRepository;

    @Inject
    public MainActivityViewModel(FactRepository factRepository) {
        this.factRepository = factRepository;
    }

    public LiveData<List<Fact>> getAllFacts(String animalType, int amount) {
        return factRepository.getMutableLiveData(animalType, amount);
    }
}
