package com.android.catfactappmvvm.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.catfactappmvvm.R;
import com.android.catfactappmvvm.data.Fact;

import java.util.ArrayList;
import java.util.List;

public class FactAdapter extends RecyclerView.Adapter<FactAdapter.FactHolder> {
    private List<Fact> facts = new ArrayList<>();

    @NonNull
    @Override
    public FactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fact, parent, false);
        return new FactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FactHolder holder, int position) {
        Fact currentFact = facts.get(position);

        holder.textViewFact.setText(currentFact.getText());
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
        notifyDataSetChanged();
    }

    class FactHolder extends RecyclerView.ViewHolder {
        TextView textViewFact;

        public FactHolder(View itemView){
            super(itemView);
            this.textViewFact = itemView.findViewById(R.id.text_view_fact);
        }
    }
}
