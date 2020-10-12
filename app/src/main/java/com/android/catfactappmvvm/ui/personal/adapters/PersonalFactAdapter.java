package com.android.catfactappmvvm.ui.personal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.catfactappmvvm.R;
import com.android.catfactappmvvm.data.local.models.PersonalFact;

import java.util.ArrayList;
import java.util.List;

public class PersonalFactAdapter extends RecyclerView.Adapter<PersonalFactAdapter.PersonalFactHolder>{
    List<PersonalFact> personalFacts = new ArrayList<>();

    @NonNull
    @Override
    public PersonalFactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fact, parent, false);
        return new PersonalFactAdapter.PersonalFactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalFactHolder holder, int position) {
        PersonalFact personalFact = personalFacts.get(position);

        holder.textViewFact.setText(personalFact.getText());
    }

    public void setPersonalFacts(List<PersonalFact> personalFacts) {
        this.personalFacts = personalFacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return personalFacts.size();
    }

    public class PersonalFactHolder extends RecyclerView.ViewHolder {
        private TextView textViewFact;

        public PersonalFactHolder (View itemView) {
            super(itemView);
            this.textViewFact = itemView.findViewById(R.id.text_view_fact);
        }
    }
}
