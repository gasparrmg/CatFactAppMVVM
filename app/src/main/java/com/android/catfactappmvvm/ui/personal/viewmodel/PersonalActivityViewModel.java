package com.android.catfactappmvvm.ui.personal.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.catfactappmvvm.data.repository.FactRepository;
import com.android.catfactappmvvm.data.local.models.PersonalFact;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersonalActivityViewModel extends ViewModel {
    private FactRepository factRepository;

    @Inject
    public PersonalActivityViewModel (FactRepository factRepository) {
        this.factRepository = factRepository;
    }

    public LiveData<List<PersonalFact>> getAllPersonalFacts() {
        return factRepository.getAllPersonalFacts();
    }

    public void insert(PersonalFact personalFact) {
        factRepository.insertPersonalFact(personalFact);
    }
}
