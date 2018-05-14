package me.ssiddh.mycupid.util;

import java.util.ArrayList;
import java.util.List;

import me.ssiddh.mycupid.data.model.MatchPerson;

public class TestUtil {

    public static MatchPerson createPerson(String userName, Boolean liked) {
        MatchPerson.Photo photo = new MatchPerson.Photo("tempBasePath");
        MatchPerson person = new MatchPerson(1, "testId", userName,27,80, "NY", "testCity", liked, photo);
        return person;
    }

    public static List<MatchPerson> getMatchedList(int size) {
        List<MatchPerson> matchPeople = new ArrayList<MatchPerson>();
        for(int i = 1; i < size; i++){
            MatchPerson.Photo photo = new MatchPerson.Photo("tempBasePath");
            MatchPerson person = new MatchPerson(i, "testId"+i, "testUserName"+i,27,i*10, "NY", "testCity", i%3 == 0, photo);
            matchPeople.add(person);
        }
        return matchPeople;
    }
}
