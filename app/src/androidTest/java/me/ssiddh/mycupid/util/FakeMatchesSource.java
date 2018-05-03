package me.ssiddh.mycupid.util;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import me.ssiddh.mycupid.data.model.MatchPerson;

public class FakeMatchesSource {

    private static FakeMatchesSource INSTANCE;

    private FakeMatchesSource() {}

    public static FakeMatchesSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeMatchesSource();
        }
        return INSTANCE;
    }

    public static List<MatchPerson> getMatchedList(int size) {
        List<MatchPerson> matchPeople = new ArrayList<MatchPerson>();
        for(int i = 1; i < size; i++){
            MatchPerson.Photo photo = new MatchPerson.Photo("tempBasePath");
            MatchPerson person = new MatchPerson(i, "testId", "testUserName",27,i*10, "NY", "testCity", i%3 == 0, photo);
            matchPeople.add(person);
        }
        return matchPeople;
    }
}
