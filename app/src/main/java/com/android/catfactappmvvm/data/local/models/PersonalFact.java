package com.android.catfactappmvvm.data.local.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personal_fact_table")
public class PersonalFact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String text;

    public PersonalFact(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }
}
