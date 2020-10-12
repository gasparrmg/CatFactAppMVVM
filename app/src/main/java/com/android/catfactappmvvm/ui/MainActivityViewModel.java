package com.android.catfactappmvvm.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.catfactappmvvm.data.Fact;
import com.android.catfactappmvvm.data.FactRepository;

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
