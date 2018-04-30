package me.ssiddh.mycupid.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.ssiddh.mycupid.data.model.MatchPerson;

@Dao
public interface MatchesDao {

    @Query("SELECT * FROM matches")
    List<MatchPerson> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MatchPerson... matches);

    @Delete
    void delete(MatchPerson match);

    @Query("SELECT * FROM matches WHERE liked ORDER BY `match` LIMIT 6")
    List<MatchPerson> getTopMatched();

}
