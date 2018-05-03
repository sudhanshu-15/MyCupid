package me.ssiddh.mycupid.util;

import java.util.ArrayList;
import java.util.List;

import me.ssiddh.mycupid.data.model.Data;
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

    public static MatchPerson getPerson(int i) {
        MatchPerson.Photo photo = new MatchPerson.Photo("tempBasePath");
        MatchPerson person = new MatchPerson(i, "testId", "testUserName",27,i*10, "NY", "testCity", i%3 == 0, photo);
        return person;
    }

    public static Data getMatchData(List<MatchPerson> personList) {
        return new Data(personList);
    }
}