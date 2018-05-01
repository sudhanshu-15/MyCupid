package me.ssiddh.mycupid.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import me.ssiddh.mycupid.data.model.MatchPerson;

@Database(entities = {MatchPerson.class}, version = 1)
public abstract class MyCupidDatabase extends RoomDatabase {

    abstract public MatchesDao matchesDao();

}
