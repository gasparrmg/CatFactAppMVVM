package com.android.catfactappmvvm.ui.personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.catfactappmvvm.R;
import com.android.catfactappmvvm.data.local.models.PersonalFact;
import com.android.catfactappmvvm.ui.personal.adapters.PersonalFactAdapter;
import com.android.catfactappmvvm.ui.personal.viewmodel.PersonalActivityViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PersonalActivity extends AppCompatActivity {

    private EditText editTextPersonalFact;
    private Button buttonSubmit;
    private RecyclerView recyclerViewPersonalFacts;

    private PersonalFactAdapter adapter;

    @Inject
    PersonalActivityViewModel personalActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        editTextPersonalFact = findViewById(R.id.edit_text_personal_fact);
        buttonSubmit = findViewById(R.id.button_submit_personal_fact);
        recyclerViewPersonalFacts = findViewById(R.id.recycler_view_personal_facts);

        recyclerViewPersonalFacts.setHasFixedSize(true);
        recyclerViewPersonalFacts.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonalFactAdapter();
        recyclerViewPersonalFacts.setAdapter(adapter);

        getPersonalFactList();

        setOnClickListeners();
    }

    private void getPersonalFactList() {
        personalActivityViewModel.getAllPersonalFacts().observe(this, new Observer<List<PersonalFact>>() {
            @Override
            public void onChanged(List<PersonalFact> personalFacts) {
                adapter.setPersonalFacts(personalFacts);
            }
        });
    }

    private void setOnClickListeners() {
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newFact = editTextPersonalFact.getText().toString();

                personalActivityViewModel.insert(new PersonalFact(newFact));
            }
        });
    }
}