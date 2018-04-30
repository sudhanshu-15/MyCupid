package me.ssiddh.mycupid.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import me.ssiddh.mycupid.data.model.MatchPerson;

@Database(entities = {MatchPerson.class}, version = 1)
public abstract class MyCupidDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "match";

    private static final Object LOCK = new Object();
    private static volatile MyCupidDatabase sInstance;

    public static MyCupidDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MyCupidDatabase.class, MyCupidDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}
