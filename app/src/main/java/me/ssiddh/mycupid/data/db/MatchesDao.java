package me.ssiddh.mycupid.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import me.ssiddh.mycupid.data.model.MatchPerson;

/*Dao for DB operations*/
@Dao
public interface MatchesDao {

    //Get all the records from matches table
    @Query("SELECT * FROM matches")
    LiveData<List<MatchPerson>> getAll();

    //Insert all records(a list of MatchPerson) into the matches table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MatchPerson> matches);

    //Delete a record from matches person
    @Delete
    void delete(MatchPerson match);

    //Get Top 6 records with liked true, ordered by match ordered by descending order
    @Query("SELECT * FROM matches WHERE liked ORDER BY `match` DESC LIMIT 6")
    LiveData<List<MatchPerson>> getTopMatched();

    //Get count of all records from matches table
    @Query("SELECT COUNT(*) FROM matches")
    int getCount();

    //Update a MatchPerson record in the matches table
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLiked(MatchPerson person);

}
