package com.android.catfactappmvvm.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.catfactappmvvm.data.local.dao.PersonalFactDao;
import com.android.catfactappmvvm.data.local.models.PersonalFact;

@Database(entities = PersonalFact.class, version = 1)
public abstract class PersonalFactDatabase extends RoomDatabase {
    public abstract PersonalFactDao personalFactDao();
}
