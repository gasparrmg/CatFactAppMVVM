package com.android.catfactappmvvm.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.catfactappmvvm.data.local.models.PersonalFact;

import java.util.List;

@Dao
public interface PersonalFactDao {

    @Insert
    void insert(PersonalFact personalFact);

    @Update
    void update(PersonalFact personalFact);

    @Delete
    void delete(PersonalFact personalFact);

    @Query("DELETE FROM personal_fact_table")
    void deleteAll();

    @Query("SELECT * FROM personal_fact_table ORDER BY id DESC")
    LiveData<List<PersonalFact>> getAll();
}
