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

@Dao
public interface MatchesDao {

    @Query("SELECT * FROM matches")
    LiveData<List<MatchPerson>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MatchPerson> matches);

    @Delete
    void delete(MatchPerson match);

    @Query("SELECT * FROM matches WHERE liked ORDER BY `match` DESC LIMIT 6")
    LiveData<List<MatchPerson>> getTopMatched();

    @Query("SELECT COUNT(*) FROM matches")
    int getCount();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLiked(MatchPerson person);

}
