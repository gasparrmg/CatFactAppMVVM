package com.android.catfactappmvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.catfactappmvvm.R;
import com.android.catfactappmvvm.data.Fact;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    //Views
    private TextView textViewTitle;
    private EditText editTextAmount;
    private Button buttonSubmit;
    private RecyclerView recyclerView;

    private FactAdapter adapter;

    @Inject
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitle = findViewById(R.id.text_view_title);
        editTextAmount = findViewById(R.id.edit_text_amount);
        buttonSubmit = findViewById(R.id.button_submit);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FactAdapter();
        recyclerView.setAdapter(adapter);

        setOnClickListeners();
    }

    private void getFactList(int amount) {
        mainActivityViewModel.getAllFacts("cat", amount).observe(this, new Observer<List<Fact>>() {
            @Override
            public void onChanged(List<Fact> facts) {
                adapter.setFacts(facts);
            }
        });
    }

    private void setOnClickListeners() {
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(editTextAmount.getText().toString());
                getFactList(amount);
            }
        });
    }
}